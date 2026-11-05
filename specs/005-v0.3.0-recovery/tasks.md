# Tasks: Recovery of the lost v0.3.0-alpha source

## Phase 1 — Forensic recovery (read-only)

- [x] T001 Decompile `CipherKey-v0.3.0-alpha-release.apk` with jadx 1.5.2
       (8,835 classes processed; app package `org.cipherkey` fully recovered).
- [x] T002 Verify APK identity: `package="org.cipherkey"`,
       `versionName="0.3.0-alpha"`, 12,599,496 bytes.
- [x] T003 Diff decompiled packages against `main`: identify the five lost
       feature areas (assistant, biometric, tools, settings, security-module
       additions) and confirm `core/*` is unchanged.
- [x] T004 Extract v0.3.0 manifest + strings as feature evidence
       (4 new permissions; assistant, biometric, tools and settings strings).
- [x] T005 Publish the evidence on branch
       `recovered/v0.3.0-alpha-from-apk` under `recovered/v0.3.0-alpha/`
       with a provenance README and re-implementation checklist.
- [x] T006 Write spec 005 (this spec) recording what was lost and the
       recovery plan.

## Phase 2 — Kotlin re-implementation on `main`

> Reference: `recovered/v0.3.0-alpha/sources/org/cipherkey/` on the recovery
> branch. Re-write in Kotlin; do not copy decompiled Java into the build.

- [ ] T010 Port `app/assistant/`: `FloatingAssistantService` (foreground
       service, overlay bubble, drag handling, clipboard quick actions) +
       `AssistantReadiness`, with Hilt injection.
- [ ] T011 Port `app/security/`: `BiometricKeyStore` interface +
       `AndroidBiometricKeyStore` (Keystore-bound wrap/unwrap ciphers) +
       `BiometricPromptHelper` + `BiometricPromptHost` Compose host +
       `BiometricPromptRequest`; era-change invalidation.
- [ ] T012 Port `security/policies/`: `KeyPolicy`, `Argon2Profile`.
- [ ] T013 Port `security/clipboard/` additions: `ClipboardParser`,
       `ClipSnapshot`, `ClipboardRead`, `ClipboardModule` (merge with
       existing clipboard code on `main` where it overlaps).
- [ ] T014 Port Tools screen: `ToolsViewModel`, `ToolsUiState`,
       manual encrypt/decrypt UI (strings `tools_*`).
- [ ] T015 Port Settings additions: `SettingsViewModel` events,
       assistant toggle (+ overlay-permission guidance), fingerprint toggle
       (+ "no fingerprints enrolled" state), KDF-strength picker, timeout
       picker.
- [ ] T016 Manifest: add `SYSTEM_ALERT_WINDOW`, `FOREGROUND_SERVICE`,
       `FOREGROUND_SERVICE_SPECIAL_USE`, `POST_NOTIFICATIONS`; register the
       assistant service with its notification channel.
- [ ] T017 Strings: port all v0.3.0 user-facing strings
       (`assistant_*`, `biometric_*`, `notice_biometric_*`, `tools_*`,
       `settings_*` additions).

## Phase 3 — Tests and release

- [ ] T020 Unit tests: KeyPolicy / Argon2Profile mapping, ClipboardParser
       taxonomy additions, ToolsViewModel outcomes, SettingsViewModel events,
       era-change invalidation of wrapped keys.
- [ ] T021 Unit tests: `AndroidBiometricKeyStore` wrap/unwrap round-trip
       behind an abstraction (device-bound parts behind interfaces, as the
       codebase already does for Keystore).
- [ ] T022 CI: extend the Android CI workflow if new lint/test tasks are
       needed; keep the build green.
- [ ] T023 Version bump: `versionCode 5`, `versionName "0.3.0-alpha"`;
       update README status table and CHANGELOG.
- [ ] T024 Tag `v0.3.0-alpha` on `main`; GitHub release with release notes
       referencing this spec.
- [ ] T025 Device gate: on a real phone, verify assistant bubble,
       fingerprint unlock, tools screen and settings against the shipped
       v0.3.0 APK behavior.
