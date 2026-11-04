# Changelog

All notable changes to CipherKey are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.1-alpha] - 2026-11-04 (device-testing pre-release, tag `v0.2.1-alpha`)

Samsung companion fixes (spec 004), from the maintainer's Galaxy A54 5G
(One UI) report against v0.2.0-alpha. Same signing key as v0.2.0 —
installs as an in-place update, no uninstall needed.

### Fixed

- **False "the clipboard is empty" on Android 10+** (spec 004 RC-1):
  clipboard launches read the clipboard during `onCreate`, before the
  window had focus — an unfocused window is denied clipboard access
  since Android 10. Clipboard workflows now defer the read to
  `onWindowFocusChanged(hasFocus=true)`, with an idempotence guard, a
  transient "Reading the clipboard…" state, and a **Try again** button
  on clipboard notices.
- **Clipboard failure taxonomy** (spec 004 FR-2): genuinely empty,
  non-text (e.g. an image), and OEM-blocked (`SecurityException`)
  outcomes now produce distinct, honest notices instead of all
  collapsing into "empty".
- **`EXTRA_PROCESS_TEXT_READONLY` ignored on encrypt** (spec 004 RC-3):
  read-only selections no longer offer "Replace in field", which the
  calling app would have ignored.

### Investigated, no code change

- Selection-toolbar entries not visible on One UI (spec 004 RC-2): the
  shipped manifest was verified correct (`aapt2 dump xmltree`); on
  modern Android/One UI third-party `PROCESS_TEXT` handlers live in the
  toolbar's ⋮ overflow, and some apps draw custom selection UIs that
  never surface them. The clipboard ladder is the designed fallback;
  see `specs/004-samsung-companion-fixes/DEVICE_TEST.md` for exactly
  where to look.

### Tests

- 187 unique tests / 18 classes / 0 failures (+27 over v0.2.0's 160):
  19 `ClipboardParser` tests (Persian, emoji, multiline LF/CRLF,
  non-text mimes, blocked-vs-empty) and 8 companion workflow round-trip
  tests over the real crypto stack.

## [0.2.0-alpha] - 2026-11-04 (device-testing pre-release, tag `v0.2.0-alpha`)

Companion architecture (Option E, spec 003): CipherKey becomes a
permission-free companion app instead of a keyboard. You keep Samsung
Keyboard — or any keyboard — and encryption moves to OS text mechanisms.
No backend, no accounts, no network code, zero Android system permissions.

### Added

- "Encrypt (CipherKey)" selection-toolbar entry (`ACTION_PROCESS_TEXT`):
  select a draft in any app, confirm, and the selection is replaced in
  place by the `CK1:` envelope. Sticky last-used contact; passphrase
  prompt only when the session is locked.
- "Decrypt (CipherKey)" selection-toolbar entry: select a received
  `CK1:` message (read-only views supported), preview the plaintext
  explicitly, copy it — or insert it directly in editable fields.
- "Decrypt with CipherKey" sharesheet target (`ACTION_SEND text/plain`)
  for messengers where sharing is easier than selecting.
- Clipboard fallback ladder on the Home screen ("Encrypt clipboard" /
  "Decrypt clipboard") for apps whose selection toolbar is unavailable.
- Hardening seam (`ProcessTextContract`) with adversarial unit tests:
  null/blank/oversized/malformed inputs produce graceful notices, never
  crashes or silent truncation.
- Sticky-contact preference (single additive DataStore key).

### Changed

- The `:keyboard` IME module is no longer part of the release APK. The
  shipped app registers no input method at all, which removes the entire
  Compose-in-IME crash class observed on device with v0.1.0. The module
  remains in-repo and keeps compiling in CI.
- Home screen reworked to the companion workflow (IME setup card removed
  with the module).

### Security

- Still zero Android permissions. Clipboard is read only on explicit
  user action in the foreground through the single `ClipboardAccess`
  chokepoint. Decrypted plaintext is previewed and never written to disk.

### Known issues

- Selection-toolbar entries depend on the target app supporting standard
  text processing; per-app variance is real (fallback ladder exists).
- Release builds are signed with a NEW v0.2 key: the v0.1.0 keystore was
  lost with a build-environment recycle. Uninstall v0.1.0 before
  installing v0.2.0 (v0.1.0 never launched on device anyway).

## [0.1.0] - 2026-11-04

First alpha. Local-first end-to-end encryption layered on top of any Android
messaging app via the keyboard (IME). No backend, no accounts, no network
code, zero Android system permissions.

### Added

- 15-module Gradle project: `app` (management UI), `keyboard` (the IME —
  contains no cryptographic code), `core` (crypto, messages, keys,
  sessions), `data` (Room, repositories, preferences), `security`
  (Keystore protector, clipboard chokepoint, Argon2id policy profiles),
  `plugins` (public-key plugin API + registry).
- Mode A encryption: Argon2id key derivation (RFC 9106 known-answer
  vector verified byte-identical) + AES-256-GCM AEAD; the entire CK1
  protocol header is bound as associated data.
- `CK1:` versioned wire envelope (strict canonical base64url, golden
  vector pinned in tests) and `CKS1:` invite codes for contact pairing.
- Compose-based keyboard with QWERTY + symbols layouts, crypto toolbar,
  passphrase unlock dialog, draft replacement via InputConnection (with
  clipboard fallback), explicit clipboard decryption with preview-before-
  insert, and sensitive-field (password) suppression.
- Contact and key-era management backed by Room; DataStore settings;
  Android Keystore wrapping for at-rest key material; session manager
  with idle timeout, cross-contact isolation, and wipe-on-lock.
- 140 unique unit tests across 15 classes (0 failures), including
  two-device roundtrip simulation, wrong-passphrase rejection (verifier
  and AEAD paths), ciphertext/header tamper detection, Persian/Unicode
  roundtrips, and zero-clipboard-read checks on password fields.
- Documentation: `docs/PROTOCOL.md`, `docs/ARCHITECTURE.md`,
  `docs/THREAT_MODEL.md`, `docs/PLUGINS.md`, engineering report.
- GitHub Spec Kit integration: project Constitution (security-first,
  honesty non-negotiables) and specifications under `specs/`.
- CI: unit tests, debug build, Gradle wrapper validation, and secret
  scanning on every push and pull request.

### Security

- Zero Android permissions (only the AndroidX self-owned
  `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` marker); `allowBackup=false`;
  no INTERNET permission; no Accessibility service; clipboard is read
  only immediately after an explicit user action.

### Known limitations (v0.1)

- No on-device verification has been performed yet (no emulator/device in
  the build environment) — treat as alpha software.
- No forward secrecy in mode A (static shared passphrase per key era).
- Messages encrypted under superseded key eras cannot be decrypted.
- Latin QWERTY layout only (the crypto stack is fully Persian-capable).
- Metadata (sizes, timing, participants) remains visible to the transport.
- Release builds ship unminified for this alpha (documented rationale in
  the release notes).
