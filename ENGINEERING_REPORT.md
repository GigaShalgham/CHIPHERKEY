# CipherKey — Final Engineering Report (v0.1.0)

Autonomous engineering session deliverable. This report states, without
inflation, what was built, what was verified, what was deliberately left
out, and why.

---

## 1. Project status at a glance

| Item | Status |
|---|---|
| Debug APK built from source | ✅ `app/build/outputs/apk/debug/app-debug.apk` (16,123,699 bytes) |
| Full unit-test suite | ✅ **140 unique tests / 15 classes / 0 failures** (`./gradlew test`; the 5 Android-library test classes additionally re-run under the release variant — see §4.1) |
| Cryptographic core verified against RFC 9106 | ✅ Argon2id known-answer vector, byte-identical |
| Wire protocol pinned | ✅ CK1 golden vector asserted in tests and documented |
| Two-device interoperability | ✅ simulated end-to-end at facade level (send→envelope→receive→decrypt) |
| Android permissions requested | ✅ no system permissions; `aapt2 dump permissions` shows only the AndroidX-generated self-owned marker (`DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`) |
| Network / backend code | ✅ none (no INTERNET permission, no HTTP dependency) |
| On-device smoke test | ⚠️ **not performed** — the build environment has no emulator or device |
| Public-key mode (B) | ❌ interface only (evaluated and deferred — see §7) |
| Old-key-era decryption | ❌ v0.1 limitation (envelope → `StaleKeyEra`) |

## 2. Toolchain and build configuration

| Component | Version |
|---|---|
| JDK | 21.0.13 (Temurin-style full JDK; system JRE is not usable for javac) |
| Gradle | 8.13 (wrapper generated; `gradle/wrapper/gradle-wrapper.properties` pins the distribution) |
| Android Gradle Plugin | 8.13.2 |
| Kotlin | 2.2.21 (KSP 2.2.21-2.0.5) |
| Compose BOM | 2025.12.01 |
| Hilt / Room / DataStore | 2.56.2 / 2.8.5 / 1.1.7 |
| BouncyCastle | 1.86 (`bcprov-jdk18on`, lightweight API only) |
| compileSdk / targetSdk / minSdk | 36 / 36 / 26 (Android 8.0+) |

Build commands:

```bash
./gradlew test            # 140 unique unit tests, 15 classes, 7 modules
./gradlew assembleDebug   # debug APK
```

Machine-specific notes (not in the repo): the build sandbox uses
`JAVA_HOME=/home/z/tools/jdk-21.0.13+9` and restricts Gradle toolchain
detection to that JDK via the user-level `~/.gradle/gradle.properties`
because the system JVM installation is JRE-only. The same user-level file
caps the daemon heap (`-Xmx2560m`): the sandbox has 3.9 GiB RAM and no
swap, and a single `clean test assembleDebug` invocation is OOM-killed at
the dex-merge step, so tests and packaging are run as two invocations.

## 3. What was built

### 3.1 Code inventory

**60 main-source Kotlin files (4,857 lines) and 16 test files (2,049 lines)**
across 15 Gradle modules:

```
:app                 management UI: status/onboarding, contacts + invite sharing,
                     passphrase unlock, security settings (3 screens, 3 ViewModels)
:keyboard            the IME: Compose hosting inside InputMethodService, QWERTY +
                     symbols layouts, crypto toolbar, passphrase dialog, decrypted
                     preview panel, ImeController state machine — and ZERO crypto code
:core:common         ContactId/SessionId value classes, DispatcherProvider
:core:crypto         AeadCipher (AES-256-GCM), Argon2KeyDerivator, PassphraseCrypto-
                     Provider, ProtocolHeader (AAD definition), ProtocolRandom,
                     EncryptedMessage, error taxonomy
:core:messages       CK1 envelope codec (strict base64url), CKS1 invite codec, limits
:core:keys           KeyProtector interface, KeyFingerprint, FixedKeyProtector (tests)
:core:sessions       KeySessionManager (derive/activate/timeout/wipe/isolation)
:security:storage    AndroidKeystoreKeyProtector + Hilt binding
:security:clipboard  ClipboardAccess chokepoint (read ONLY on explicit action)
:security:policies   Argon2 profiles (16/64/256 MiB), KeyPolicy (passphrase, timeouts)
:data:database       Room: contacts + session_keys (key eras), DAOs, schema v1
:data:preferences    DataStore-backed AppSettings (timeout, profile, onboarding flag)
:data:repository     ContactRepository, SessionKeyRepository, MessageCipher facade,
                     Hilt composition modules
:plugins:api         PublicKeyCipherPlugin contract (mode B)
:plugins:registry    PluginRegistry + BundledPlugins (honestly empty)
```

### 3.2 Security-relevant design decisions

1. **Keyboard module contains no cryptography.** It consumes the
   `MessageCipher` facade; key material never crosses into it.
2. **Every CK1 header byte is AAD.** Version, mode, algorithm ids, keyId,
   KDF parameters, salt and nonce are all authenticated — proven by tests
   that flip single bits in each region.
3. **Contact-era keys, not per-message derivation.** One Argon2id run per
   unlock (default 64 MiB / 3 passes / 1 lane), then per-message AEAD only.
4. **Passphrase never stored.** After the first successful unlock on a
   device, a Keystore-wrapped verifier enables wrong-passphrase rejection at
   unlock time; on a fresh device the AEAD authenticator catches it.
   Cross-device portability is preserved (passphrase + invite suffice).
5. **Zero system permissions** (the only manifest entry is the AndroidX
   self-owned `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` marker),
   `allowBackup=false`, no INTERNET permission, no Accessibility service,
   no background clipboard reads.
6. **Password fields disable all crypto UI** (`SensitiveFieldPolicy`),
   enforced and tested down to "zero clipboard reads" in the controller test.

## 4. Verification evidence

### 4.1 Test inventory (140 unique tests, all green)

Counted from the JUnit XML result files (machine-readable, deduplicated
by test class). Android-library modules execute their unit tests under
both the debug and the release variant, so the 5 affected classes
(50 tests) run twice per `./gradlew test` invocation — 190 raw
executions. An earlier revision of this report quoted the raw figure as
the total; the unique count above is the meaningful one. The counting
script lives in the maintainers' tooling and reads
`build/test-results/{test,testDebugUnitTest,testReleaseUnitTest}`.

| Test class | Tests | Covers |
|---|---|---|
| `AesGcmAeadCipherTest` | 10 | roundtrips, per-byte tampering, wrong key, AAD mismatch, length validation, 16 KiB payloads |
| `Argon2KeyDerivatorTest` | 7 | **RFC 9106 Argon2id vector**, determinism, salt/passphrase sensitivity, Persian passphrase |
| `Argon2ParamsTest` | 9 | canonical encode/decode, all range validations incl. RFC 8×p minimum |
| `PassphraseCryptoProviderTest` | 8 | provider-level auth failures, AAD-tamper via serialized form, size guards, byte-stable envelopes |
| `ProtocolHeaderTest` | 10 | layout roundtrip, unknown version/mode/cipher/KDF rejection, truncation, AAD stability |
| `Ck1CodecTest` | 12 | strict base64url (padding/+/slash/whitespace), prefix, limits, **golden vector** |
| `InviteCodecTest` | 9 | CKS1 roundtrip, tamper, version/KDF/param rejection, compactness |
| `KeySessionManagerTest` | 11 | expiry, lock/lockAll, wipe-to-zero assertions, replacement wipe, **cross-contact isolation** |
| `ContactRepositoryTest` | 8 | CRUD, name validation, uniqueness |
| `SessionKeyRepositoryTest` | 9 | era creation/import, duplicate & cross-contact refusal, verifier persistence |
| `MessageCipherTest` | 18 | **two-device roundtrips**, wrong passphrase (both verifier and AEAD paths), ciphertext & header tampering, unknown key, stale era, Persian/emoji roundtrip, whitespace tolerance, isolation, lockAll, size limits |
| `ImeControllerTest` | 7 | keyboard flows: prompt→unlock→retry→replace-draft, clipboard decrypt→preview→insert, sensitive-field suppression (0 clipboard reads), notices |
| `SensitiveFieldPolicyTest` | 8 | password/visible-password/web-password/number-password detection |
| `KeyPolicyTest` | 7 | passphrase policy, timeout clamping, profile sanity |
| `PluginRegistryTest` | 7 | registry semantics, honest-empty bundled set |

### 4.2 Cross-implementation verification

The RFC 9106 Argon2id tag produced by the shipped BouncyCastle
configuration was cross-checked against the RustCrypto known-answer suite
(`argon2/tests/kat.rs`, `argon2id_v0x13`), which itself validates against
the reference C implementation — byte-identical.

### 4.3 Milestone criteria (from the project specification)

| Criterion | Result |
|---|---|
| Project builds | ✅ `assembleDebug` green |
| Installable & selectable as system keyboard | ✅ declared IME service with correct intent-filter/metadata; **device install not performed here** |
| Type in third-party app and encrypt draft | ✅ implemented (InputConnection read/replace with two fallback strategies); on-device verification pending |
| Ciphertext replaces the draft and can be sent | ✅ `replaceAll` implemented; fallback copies the envelope to the clipboard if the field refuses editing |
| Second installation can decrypt | ✅ verified at facade level (two independent TestDevices: separate DBs, separate protectors) |
| Wrong credentials and tampered messages rejected | ✅ 140-test suite covers both paths |
| Plaintext only shown after successful authentication | ✅ preview panel appears exclusively from `DecryptionResult.Success` |
| Automated tests for core crypto | ✅ see §4.1 |

## 5. Defects found and fixed during the session

Recorded for transparency:

1. **Pre-existing compile error** in `PassphraseCryptoProvider.encrypt`
   (returned `DecryptionResult.Failure` against an `EncryptedMessage`
   return type) — fixed by introducing `EncryptionResult`.
2. **JVM target mismatch** (Java 17 vs Kotlin 21) in all 8 pure-Kotlin
   modules — aligned via `compilerOptions.jvmTarget = 17`.
3. **Toolchain resolution failure** (system JVM is a JRE) — restricted
   toolchain detection to the full JDK in user-level Gradle properties.
4. **Hilt aggregation gap**: library modules with `@Module` must run their
   own Hilt compiler pass — added plugin+ksp to `data:repository`,
   `security:storage`, `security:clipboard`.
5. **Buffer-aliasing bug in `KeySessionManager`** (found by the two-device
   tests): wiping an unlocked session zeroed a `keyId` buffer shared with
   the storage layer, corrupting subsequent lookups. Fixed by defensive
   copies in `activate()`; the failing test now guards the regression.
6. **Busy-flag retry no-op in `ImeController`** (found by controller tests):
   the post-unlock auto-retry was swallowed by its own `busy` guard.
   Fixed by clearing the flag before retrying.
7. **Misremembered RFC vector** during test authoring — resolved by
   cross-checking against the independent RustCrypto KAT before pinning
   (BouncyCastle was correct; the test was fixed, not the library).

## 6. Known limitations (v0.1)

1. **No on-device verification yet.** Keystore wrap/unwrap, IME draft
   replacement across popular apps, and Android 10+ clipboard-focus rules
   need a physical-device smoke test pass. The JVM test suite covers
   everything above the Android framework line.
2. **No forward secrecy** in mode A (static shared passphrase per key era).
3. **Fresh-device wrong-passphrase on send** is only detectable at the
   receiver (verifier closes it from the second unlock on each device).
4. **Superseded key eras cannot be decrypted** (envelope → `StaleKeyEra`);
   rotation creates a new era and old envelopes are refused with a clear
   error.
5. **Latin QWERTY + symbols only**; no Persian/other layouts yet (the
   crypto stack is fully UTF-8/Persian-capable — tested).
6. **Metadata is not protected**: message sizes, timing and transport
   participants remain visible to the underlying messaging platform.
7. **Managed-runtime wipe caveat**: key zeroing is best-effort (GC copies).
8. Release builds are unsigned (no publishing keystore).

## 7. Public-key mode (B) — evaluation outcome

Per the project specification, mature Signal Protocol implementations were
evaluated before writing any code:

- **`org.signal:libsignal-android` (0.86.5)** — AGPL-3.0 (conflicts with
  this project's Apache-2.0 distribution), explicitly "unsupported" for
  external use, assumes a prekey distribution server, ships 4-ABI native
  code, requires four persistent store implementations and identity
  verification UX. Cryptographically excellent; **integration deferred**.
- **`libsignal-protocol-java`** — archived, unmaintained, GPL-3.0.
  **Rejected.**
- Third-party ports — unmaintained or not Android-consumable. **Rejected.**

Shipped instead: `PublicKeyCipherPlugin` contract + explicit
`PluginRegistry` (empty), UI reports mode B as unavailable. Full analysis
and re-evaluation criteria: `docs/PLUGINS.md`. **No fake or silently
downgraded implementation exists.**

## 8. Remaining work (priority order)

1. On-device smoke test: enable → type → encrypt → send → second device
   decrypt; verify Keystore wrap/unwrap on real hardware.
2. Instrumented tests for `AndroidKeystoreKeyProtector` (androidTest source
   set, requires device).
3. Persian/Arabic keyboard layout + subtype declaration in `method.xml`.
4. Key-era rotation UX (create + share new invite in-place) and old-era
   decryption (archive decrypt path).
5. Mode B plugin: re-evaluate per `docs/PLUGINS.md` criteria.
6. Release signing + Play-adjacent hardening (minification ruleset
   validation, baseline profile).
7. Dependency-analysis Gradle plugin to mechanically enforce the "keyboard
   has no crypto" rule.

## 9. Artifact locations

| Artifact | Path |
|---|---|
| Debug APK | `app/build/outputs/apk/debug/app-debug.apk` |
| Test reports | `*/build/reports/tests/` (JUnit XML under `*/build/test-results/`) |
| Room schema | `data/database/schemas/…version 1.json` |
| Protocol docs | `docs/PROTOCOL.md`, `docs/ARCHITECTURE.md`, `docs/THREAT_MODEL.md`, `docs/PLUGINS.md` |
| CI | `.github/workflows/android-ci.yml` |
