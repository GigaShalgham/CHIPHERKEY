# Recovered: v0.3.0-alpha (from APK)

> **What this is:** The v0.3.0-alpha source code was lost before it was ever
> committed. This directory preserves the *only surviving record* of that
> version: the app code **decompiled from the signed release APK**
> `CipherKey-v0.3.0-alpha-release.apk` with jadx 1.5.2.
>
> **What this is not:** Buildable Gradle source. The files below are readable
> Java recovered from DEX bytecode. Use them as a **reference** to re-implement
> the v0.3.0 features in Kotlin on top of `main` (v0.2.2-alpha).

## Contents

| Path | Description |
|---|---|
| `sources/org/cipherkey/` | Decompiled app code (252 Java files), organized by package |
| `AndroidManifest.xml` | Decoded manifest — shows new permissions and the assistant service |
| `strings.xml` | User-facing strings of v0.3.0 — evidence of new UI/features |

Decompilation stats: 8835 classes processed, 39 minor errors (unused/obscure
paths), app package fully recovered.

## What was new in v0.3.0-alpha (vs v0.2.2-alpha on `main`)

Recovered by comparing this decompilation against the `main` branch:

1. **Floating assistant** — `app/assistant/FloatingAssistantService`
   - A foreground service showing a draggable bubble over other apps with
     quick *Encrypt / Decrypt clipboard* actions.
   - Adds permissions: `SYSTEM_ALERT_WINDOW`, `FOREGROUND_SERVICE`,
     `FOREGROUND_SERVICE_SPECIAL_USE`, `POST_NOTIFICATIONS`.

2. **Fingerprint (biometric) unlock** — `app/security/*`
   - `BiometricKeyStore` / `AndroidBiometricKeyStore`: wraps unlocked session
     keys with a Keystore cipher bound to the fingerprint.
   - `BiometricPromptHelper`, `BiometricPromptHost` (Compose UI),
     `BiometricPromptRequest`.
   - Remembered keys are invalidated when a contact's key era changes
     (`notice_biometric_stale`).

3. **Tools screen** — `ToolsViewModel`, `ToolsUiState`
   - Manual encrypt/decrypt of typed or pasted text (`tools_input_hint`).

4. **Expanded Settings** — `SettingsViewModel`, `SettingsEvent`
   - Fingerprint unlock toggle, floating assistant toggle (with
     overlay-permission guidance), key-derivation strength for new contacts,
     "stay unlocked for" timeout picker.

5. **New security module classes** — `security/policies/KeyPolicy`,
   `security/policies/Argon2Profile`, `security/clipboard/*`
   (ClipboardParser, ClipSnapshot, ClipboardRead, ClipboardModule).

## Re-implementation checklist

- [ ] Port `app/assistant/FloatingAssistantService` to Kotlin + Hilt
- [ ] Port `app/security` biometric package (`androidx.biometric` dependency)
- [ ] Port `ToolsViewModel` / Tools screen UI
- [ ] Port Settings additions (assistant + biometric + KDF strength rows)
- [ ] Add the 4 new permissions to `app/src/main/AndroidManifest.xml`
- [ ] Re-add v0.3.0 strings to `strings.xml`
- [ ] Bump `versionCode` 4 → 5, `versionName` "0.2.2" → "0.3.0-alpha"

## Provenance

- APK: `CipherKey-v0.3.0-alpha-release.apk` (12,599,496 bytes,
  `versionName="0.3.0-alpha"`, package `org.cipherkey`)
- Tool: `jadx 1.5.2` (`jadx -d decompiled --show-bad-code`)
- Recovered: 2026-11-05
