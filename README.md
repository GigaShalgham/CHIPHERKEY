# CipherKey

[![CI](https://github.com/GigaShalgham/CHIPHERKEY/actions/workflows/android-ci.yml/badge.svg)](https://github.com/GigaShalgham/CHIPHERKEY/actions/workflows/android-ci.yml)
[![License: Apache-2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)

**A local-first encryption layer that lives inside your Android keyboard.**

CipherKey adds end-to-end message encryption to the messaging apps you already
use — SMS, Telegram, WhatsApp, anything with a text field — without any
server, account, or network access. It works by acting as a standard input
method (IME): you type normally, tap **Encrypt**, and your draft is replaced
with a `CK1:...` ciphertext you send as an ordinary message. On the receiving
side, copy the ciphertext, tap **Decrypt**, read the plaintext preview, and
insert it.

```
Send:    type → select contact → Encrypt → ciphertext replaces the draft → send
Receive: copy CK1:... → tap Decrypt → passphrase → preview → Insert
```

## Status

| Capability | Status |
|---|---|
| Build (debug APK) | ✅ builds from source (`./gradlew assembleDebug`) |
| IME integration (Compose UI, QWERTY + symbols) | ✅ implemented, unit-tested (logic) |
| Mode A crypto: Argon2id + AES-256-GCM | ✅ implemented, 140 unique unit tests, RFC 9106 vector verified |
| CK1 wire protocol + CKS1 invites | ✅ implemented, golden vector pinned |
| Contact / key-era management (Room) | ✅ implemented |
| Android Keystore key wrapping | ✅ implemented (needs on-device verification) |
| Session manager (timeout, isolation, wipe) | ✅ implemented, unit-tested |
| On-device smoke test (real phone) | ⚠️ not performed in this environment (no emulator/device) |
| Mode B: public-key (Signal et al.) | ❌ interface only — evaluated and deferred, see docs/PLUGINS.md |
| Old-key-era message decryption | ❌ v0.1 limitation (documented) |
| Non-Latin keyboard layouts | ❌ v0.1 ships en-QWERTY only |

See `ENGINEERING_REPORT.md` for the full engineering status, test inventory
and known limitations.

## Building

Requirements: JDK 17+ (built and tested with JDK 21), Android SDK with
platform 36 and build-tools 36.0.0 (or let Gradle's SDK tooling handle it).

```bash
./gradlew assembleDebug          # debug APK  -> app/build/outputs/apk/debug/
./gradlew test                   # full unit-test suite (140 unique tests)
```

The application requests **no Android system permissions** and contains **no
network code** — the only entry `aapt2 dump permissions` shows is the
`DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` marker that AndroidX generates
(a permission the app owns itself; it grants no capabilities). Verify it
yourself: `aapt2 dump permissions app/build/outputs/apk/debug/app-debug.apk`.

## How it works

1. **Pair once.** Alice creates a contact "Bob" in the app; CipherKey
   generates a key era (public `keyId` + `salt` + Argon2id parameters) and
   shows a `CKS1:...` invite code. Alice sends the code to Bob over any
   channel. Bob creates a contact "Alice", pastes the code. Separately —
   ideally in person — both agree on a shared passphrase.
2. **Unlock per session.** The passphrase is never stored. Deriving the
   AES-256 key takes ~hundreds of milliseconds (Argon2id, 64 MiB / 3 passes
   by default). Keys live in memory only and are wiped on lock, timeout or
   field switch. After the first successful unlock on a device, a
   Keystore-wrapped verifier lets future wrong passphrases be rejected at
   unlock time.
3. **Encrypt/decrypt in any app.** The keyboard's toolbar offers
   *Encrypt draft* and *Decrypt clipboard*. Password fields are detected and
   the crypto UI is disabled there.

## Security properties

- AES-256-GCM (JCA) with a 96-bit random nonce and 128-bit tag; the entire
  CK1 header is bound as associated data.
- Argon2id (RFC 9106) via BouncyCastle's lightweight API; no JCA provider is
  inserted.
- Derived keys are never persisted raw; at rest only a Keystore-wrapped,
  device-bound blob exists.
- Strict, canonical wire format; unknown versions/algorithms/modes are
  rejected before any cryptographic work.
- `allowBackup=false`; no `INTERNET` permission; zero-permission manifest.

Limitations and the honest threat model are in `docs/THREAT_MODEL.md`.

## Repository layout

```
app/            management UI (onboarding, contacts, settings)
keyboard/       the IME itself (Compose UI + controller; NO crypto code)
core/           pure-Kotlin domain: crypto, messages, keys, sessions
data/           Room database, repositories (MessageCipher facade), settings
security/       Keystore protector, clipboard chokepoint, parameter policies
plugins/        public-key protocol plugin API + registry (empty in v0.1)
docs/           PROTOCOL, ARCHITECTURE, THREAT_MODEL, PLUGINS
```

The keyboard module contains **no cryptographic implementation**; it consumes
the `MessageCipher` facade from `:data:repository`. Module dependency
directions are acyclic and documented in `docs/ARCHITECTURE.md`.

## Releases

Prebuilt APKs live under
[GitHub Releases](https://github.com/GigaShalgham/CHIPHERKEY/releases).
Every release page states the build type, the signing certificate SHA-256
fingerprint, and the APK's SHA-256 checksum — verify before installing:

```bash
sha256sum CipherKey-v0.1.0-alpha-release.apk
apksigner verify --print-certs CipherKey-v0.1.0-alpha-release.apk
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
