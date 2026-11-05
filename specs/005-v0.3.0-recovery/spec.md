# Spec 005 — Recovery of the lost v0.3.0-alpha source

## Problem statement

The v0.3.0-alpha build of CipherKey (the most advanced version of the app,
shipped as `CipherKey-v0.3.0-alpha-release.apk`) was produced without its
Kotlin source ever being committed. All other repository history is intact,
but everything between `main` HEAD (`v0.2.2-alpha`, commit `1f2b501`) and the
v0.3.0 release APK exists **only inside that APK**. The source must be
recovered — or, where recovery is impossible, re-implemented — before any
further work, otherwise every future change silently forks away from the
shipped app.

## What was actually lost (established by decompilation, not guessed)

A full jadx 1.5.2 decompilation of the signed release APK (8,835 classes, 39
minor errors, app package fully recovered) compared file-by-file against
`main` shows the delta is **five feature areas**:

1. **Floating assistant** (`app/assistant/`) — a foreground service with a
   draggable overlay bubble offering quick Encrypt/Decrypt-clipboard actions.
   Introduces four new manifest permissions: `SYSTEM_ALERT_WINDOW`,
   `FOREGROUND_SERVICE`, `FOREGROUND_SERVICE_SPECIAL_USE`, `POST_NOTIFICATIONS`.
2. **Fingerprint unlock** (`app/security/`) — `BiometricKeyStore`,
   `AndroidBiometricKeyStore`, `BiometricPromptHelper`, `BiometricPromptHost`
   (Compose), `BiometricPromptRequest`; wrapped keys are invalidated when a
   contact's key era changes.
3. **Tools screen** (`ToolsViewModel`, `ToolsUiState`) — manual
   encrypt/decrypt of typed or pasted text.
4. **Expanded Settings** (`SettingsViewModel`, `SettingsEvent`) — toggles for
   the assistant and fingerprint unlock, KDF-strength picker for new key
   eras, "stay unlocked for" timeout picker.
5. **Security-module additions** — `security/policies/KeyPolicy`,
   `security/policies/Argon2Profile`, `security/clipboard/*`
   (ClipboardParser, ClipSnapshot, ClipboardRead, ClipboardModule).

Everything else in the APK matches `main` at `v0.2.2-alpha`.

## Requirements

- **R1 — Preserve the evidence.** The decompiled v0.3.0 code must be stored
  in-repo, clearly labeled as decompiled reference (not buildable source), on
  a dedicated branch: `recovered/v0.3.0-alpha-from-apk`.
- **R2 — No silent history loss again.** Every future release tag must point
  at a commit that contains the exact source it was built from; the recovery
  README documents the checklist that makes v0.3.0 reproducible on `main`.
- **R3 — Re-implementation, not copy-paste.** The five feature areas must be
  re-written in Kotlin on top of `main` (the decompiled Java is a reference,
  not a source file donor), keeping the module boundaries, Hilt wiring and
  test conventions of the existing codebase.
- **R4 — Version continuity.** The re-implementation lands as
  `versionCode 5` / `versionName "0.3.0-alpha"` so the recovered app is a
  strict upgrade path from the shipped APK (package `org.cipherkey`).

## Out of scope

- Changing the crypto core (`core/crypto`) — v0.3.0 did not touch it beyond
  what `main` already has.
- The dormant `:keyboard` module.
- Re-signing or re-publishing the lost APK.

## Deliverables

- Branch `recovered/v0.3.0-alpha-from-apk` with the jadx output under
  `recovered/v0.3.0-alpha/` plus a provenance README (done, see
  `recovered/v0.3.0-alpha/README.md`).
- A tasks file (this spec) tracking the Kotlin re-implementation of the five
  feature areas.
- `main` receiving the re-implementation, ending in the v0.3.0-alpha version
  bump and a `v0.3.0-alpha` tag.
