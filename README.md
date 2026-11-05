# CipherKey

[![CI](https://github.com/GigaShalgham/CHIPHERKEY/actions/workflows/android-ci.yml/badge.svg)](https://github.com/GigaShalgham/CHIPHERKEY/actions/workflows/android-ci.yml)
[![License: Apache-2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)

**A local-first, permission-free encryption companion for any Android
messenger.**

CipherKey adds end-to-end message encryption to the messaging apps you already
use — SMS, Telegram, WhatsApp, anything that supports standard text selection
or sharing — without any server, account, or network access. Since v0.2 the
shipped app is a **companion**, not a keyboard: you keep the keyboard you
already use, select the text you want to protect, and tap **Encrypt
(CipherKey)** in the selection toolbar. The draft is replaced with a
`CK1:...` ciphertext you send as an ordinary message. On the receiving side,
select the message (or share it to CipherKey), tap **Decrypt (CipherKey)**,
read the plaintext preview, copy or insert it.

```
Send:    type with any keyboard → select the draft → “Encrypt (CipherKey)” → ciphertext replaces it → send
Receive: select/share the CK1:... message → “Decrypt (CipherKey)” → passphrase → preview → Copy
```

When an app's selection toolbar is unavailable, the Home screen offers an
**Encrypt clipboard / Decrypt clipboard** fallback ladder.

## Status

| Capability | Status |
|---|---|
| Build (debug APK) | ✅ builds from source (`./gradlew assembleDebug`) |
| Companion workflows (selection toolbar, sharesheet, clipboard fallback) | ✅ implemented, unit-tested (logic) |
| Mode A crypto: Argon2id + AES-256-GCM | ✅ implemented, 197 unique unit tests, RFC 9106 vector verified |
| CK1 wire protocol + CKS1 invites | ✅ implemented, golden vector pinned |
| Contact / key-era management (Room) | ✅ implemented |
| Android Keystore key wrapping (verifier is a digest, never the raw key) | ✅ implemented (needs on-device verification) |
| Session manager (timeout, isolation, wipe, periodic sweep) | ✅ implemented, unit-tested |
| Old-key-era message decryption | ✅ v0.4.0 — superseded eras unlock with `unlockEra` into a bounded historical-session cache (decrypt-only, never the sending key); the envelope's era is resolved from its keyId |
| Passphrase advisor | ✅ v0.4.0 — live strength meter under every passphrase field + recommended-passphrase generator in the new-contact dialog (7 words + 2 digits, 69 bits guaranteed) |
| On-device smoke test (real phone) | ⚠️ maintainer device gate in progress (see `specs/004…/DEVICE_TEST.md`) |
| Mode B: public-key (Signal et al.) | ❌ interface only — evaluated and deferred, see docs/PLUGINS.md |
| Built-in keyboard (IME) | ❌ removed from the APK in v0.2 — the `:keyboard` module stays in-repo, compiled and tested as a dormant library |
| v0.3.0-alpha (floating assistant, fingerprint unlock, in-app tools) | ✅ re-implemented on `main` (spec 005, recovered from the signed release APK; see CHANGELOG) |

See `ENGINEERING_REPORT.md` (v0.1 history), `docs/DEEP-SCAN-2026-11-05.md`
(the full audit) and `CHANGELOG.md` for engineering status and known
limitations.

## Building

Requirements: JDK 17+ (built and tested with JDK 21), Android SDK with
platform 36 and build-tools 36.0.0 (or let Gradle's SDK tooling handle it).

```bash
./gradlew assembleDebug          # debug APK  -> app/build/outputs/apk/debug/
./gradlew test                   # full unit-test suite (90 tests / 0 failures, v0.3.0)
```

The application contains **no network code**. Its manifest is zero-permission
by default: the two v0.3.0 features that need Android permissions — the
floating assistant (`SYSTEM_ALERT_WINDOW`, foreground-service and
notification permissions) and fingerprint unlock (`USE_BIOMETRIC`) — are
**opt-in and off by default**, and with both disabled the app behaves
exactly like v0.2. The only always-present entry `aapt2 dump permissions`
shows is the `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` marker that AndroidX
generates (a permission the app owns itself; it grants no capabilities).
Verify it yourself: `aapt2 dump permissions
app/build/outputs/apk/debug/app-debug.apk`.

## How it works

1. **Pair once.** Alice creates a contact "Bob" in the app; CipherKey
   generates a key era (public `keyId` + `salt` + Argon2id parameters) and
   shows a `CKS1:...` invite code. Alice sends the code to Bob over any
   channel. Bob creates a contact "Alice", pastes the code. Separately —
   ideally in person — both agree on a shared passphrase.
2. **Unlock per session.** The passphrase is never stored. Deriving the
   AES-256 key takes ~hundreds of milliseconds (Argon2id, 64 MiB / 3 passes
   by default). Keys live in memory only and are wiped on lock, timeout or
   replacement (plus a periodic sweep of expired sessions). After the first
   successful unlock on a device, a Keystore-wrapped **digest** verifier lets
   future wrong passphrases be rejected at unlock time — the raw key is never
   persisted, even wrapped.
3. **Encrypt/decrypt in any app.** The selection toolbar and the sharesheet
   carry text in and out; the clipboard fallback covers the rest. Screens
   that display decrypted plaintext set `FLAG_SECURE` (no screenshots or
   screen recording), and copied plaintext is marked sensitive on
   Android 13+.

## Security properties

- AES-256-GCM (JCA) with a 96-bit random nonce and 128-bit tag; the entire
  CK1 header is bound as associated data.
- Argon2id (RFC 9106) via BouncyCastle's lightweight API; no JCA provider is
  inserted.
- Derived keys are never persisted raw; at rest only a Keystore-wrapped,
  device-bound **digest** of the key exists (full-key verifiers from ≤ 0.2.1
  are migrated transparently on next unlock).
- Strict, canonical wire format; unknown versions/algorithms/modes are
  rejected before any cryptographic work.
- `allowBackup=false`; no `INTERNET` permission; zero-permission default
  (v0.3.0 opt-in features add their own, documented in the CHANGELOG).

Limitations and the honest threat model are in `docs/THREAT_MODEL.md`.

## Repository layout

```
app/            companion UI: home, contacts, settings + PROCESS_TEXT/SEND activities
keyboard/       the IME — dormant library since v0.2 (NOT in the APK; no crypto code)
core/           pure-Kotlin domain: crypto, messages, keys, sessions
data/           Room database, repositories (MessageCipher facade), settings
security/       Keystore protector, clipboard chokepoint, parameter policies
plugins/        public-key protocol plugin API + registry (empty in v0.2)
docs/           PROTOCOL, ARCHITECTURE, THREAT_MODEL, PLUGINS, DEEP-SCAN audits
specs/          GitHub Spec Kit feature specifications
```

The `:keyboard` module contains **no cryptographic implementation**; when it
ships it would consume the `MessageCipher` facade from `:data:repository`.
Module dependency directions are acyclic and documented in
`docs/ARCHITECTURE.md`.

## Releases

Prebuilt APKs live under
[GitHub Releases](https://github.com/GigaShalgham/CHIPHERKEY/releases).
Every release page states the build type, the signing certificate SHA-256
fingerprint, and the APK's SHA-256 checksum — verify before installing:

```bash
sha256sum CipherKey-v0.2.1-alpha-release.apk
apksigner verify --print-certs CipherKey-v0.2.1-alpha-release.apk
```

CI artifacts (`cipherkey-debug-apk`) are unsigned debug builds for
experimentation; only GitHub Releases carry signed builds.

## Community & workflow

- [Contributing](CONTRIBUTING.md) · [Code of Conduct](CODE_OF_CONDUCT.md) · [Security policy](SECURITY.md) · [Changelog](CHANGELOG.md)
- Engineering follows [GitHub Spec Kit](https://github.com/github/spec-kit):
  the project Constitution and feature specifications live under
  [`specs/`](specs/) (Constitution: [`.specify/memory/constitution.md`](.specify/memory/constitution.md)).

## License

Apache-2.0 — see `LICENSE`. BouncyCastle (MIT-like) and AndroidX/Compose
(Apache-2.0) are used as dependencies; the RFC 9106 test vector cross-check
used the RustCrypto known-answer suite.
