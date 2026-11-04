# Changelog

All notable changes to CipherKey are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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
