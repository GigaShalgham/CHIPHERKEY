# Tasks: Option E — Hybrid Companion Architecture (Implementation)

**Input**: Design documents from `/specs/003-option-e-companion/`

**Prerequisites**: plan.md (required), spec.md (required)

**Tests**: JVM contract tests + full existing suite + build/badging
verification (device acceptance is the maintainer's P1/P2 gate).

## Phase 1 — Spec and hardening seam

- [x] T001 Author spec.md, plan.md, tasks.md on branch
      `003-option-e-companion`
- [x] T002 Implement `ProcessTextContract` (pure JVM): sanitize selected
      text (null/blank → null), UTF-8-aware 16 KiB cap, envelope
      detection
- [x] T003 Unit-test the contract with adversarial inputs (null, blank,
      whitespace, oversized, malformed, emoji/RTL unicode, exactly-at-cap)
- [x] T004 Add sticky-contact key to `SettingsRepository` (additive,
      clamped to existing contact or null)

## Phase 2 — Companion core (P1)

- [x] T005 Implement `CompanionViewModel` state machine (contact
      selection, unlock, encrypt, decrypt, clipboard write-back,
      session-lock awareness)
- [x] T006 Implement shared companion UI composables (contact picker with
      unlock state, masked passphrase field, result preview, notices)
- [x] T007 Implement `EncryptActivity` (ACTION_PROCESS_TEXT, editable
      variant, envelope returned via setResult)
- [x] T008 Implement `DecryptActivity` (ACTION_PROCESS_TEXT incl.
      read-only variant; Copy always, Insert only when editable)
- [x] T009 Manifest: three exported activities with correct filters,
      labels, noHistory, excludeFromRecents

## Phase 3 — Fallback ladder and decoupling (P2)

- [x] T010 Implement `ShareDecryptActivity` (ACTION_SEND text/plain)
- [x] T011 Rework Home screen to the companion workflow (status, how-to,
      explicit clipboard encrypt/decrypt actions)
- [x] T012 Replace IME-status plumbing in MainViewModel with companion
      state (IME queries removed with the module)
- [x] T013 Remove `:keyboard` from `:app` dependencies; keep module in
      settings.gradle.kts; bump versionCode 2 / versionName 0.2.0
- [x] T014 CHANGELOG `[0.2.0-alpha]` entry (unreleased section, honest
      claims, keystore-identity disclosure)

## Phase 4 — Verification and delivery

- [x] T015 Full JVM test run green (140 existing + new contract tests)
      (done: 160 unique tests / 16 classes / 0 failures — +20
      ProcessTextContract tests; aggregated from JUnit XML, deduplicated
      by class)
- [x] T016 `assembleDebug` green
- [x] T017 Generate new v0.2 release keystore outside the repository;
      record SHA-256 fingerprint; signed `assembleRelease` green
      (cert SHA-256 d56c2a5f7696fefcf437205354667b43ee6e8b471b11eb0425fe5029ca66841b;
      APK SHA-256 b432fb90d16d37b088d7890afe48c1cfb34c09e12e7cdcadee010a3c9895c22d)
- [x] T018 Badging + manifest verification: no IME service, zero
      permissions, versionCode 2 / versionName 0.2.0; apksigner verify
      (done: only service in the merged manifest is AndroidX's internal
      MultiInstanceInvalidationService; permission list identical to
      v0.1.0 — the self-defined AndroidX receiver permission, zero system
      permissions; 4 activities: launcher + 3 companion)
- [x] T019 Deliver APKs + checksums to `download/`; update its README
- [x] T020 Commit on `003-option-e-companion`; push; verify remote SHA

## Deferred (maintainer device gate — outside this spec)

- [ ] T021 P1 device acceptance on the real Samsung device (report §10:
      toolbar entry visible, round-trip byte-exact, replacement works,
      fuzz on-device, Samsung Keyboard untouched)
- [ ] T022 P2 device acceptance in two messenger apps + fallback ladder
- [x] T023 v0.2.0 tag + GitHub release (only after T021/T022)
      (done ahead of the device gate on 2026-11-04 at the maintainer's
      request: tag `v0.2.0-alpha` + a clearly labeled device-testing
      GitHub release with both APKs are published; the stable `v0.2.0`
      tag still waits for T021/T022)
- [ ] T024 Quick Settings tile (report phase 4, evidence-gated)
