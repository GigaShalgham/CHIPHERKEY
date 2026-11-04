# Implementation Plan: Option E — Hybrid Companion Architecture

**Branch**: `003-option-e-companion` | **Date**: 2026-11-04 | **Spec**: `specs/003-option-e-companion/spec.md`

## Summary

Implement the Option E core (report §5): three exported companion
activities (encrypt, decrypt, share-decrypt) wired exclusively to the
existing `MessageCipher` facade, clipboard fallback actions on the Home
screen, a sticky last-used contact, and removal of `:keyboard` from the
release APK. No crypto, codec, or persistence changes beyond one additive
DataStore key.

## Technical Context

**Reused as-is**: `MessageCipher` (encryptDraft/decryptEnvelope/
unlockContact), `ContactRepository`, `SessionKeyRepository`,
`ClipboardAccess`, Hilt graph (`RepositoryModule` provides everything).

**New seams**:

- `companion/ProcessTextContract.kt` — pure JVM hardening: null/blank
  handling, UTF-8-aware 16 KiB cap, envelope detection. Unit-tested with
  adversarial inputs.
- `companion/CompanionViewModel.kt` — one state machine behind all three
  activities + the Home clipboard actions.
- Sticky contact: `SettingsRepository.lastContactId` (String?, default
  null) — additive key, clamped to "exists or null" on read.

**Android surface**: 3 exported activities, intent filters per FR-001,
`noHistory="true"` + `excludeFromRecents="true"` on processing activities
(utility flows; nothing sensitive kept in recents), Compose UI, Theme
unchanged.

**Test strategy**: contract unit tests (junk fuzz); existing 140 tests
must stay green; `assembleDebug` + signed `assembleRelease`; badging
inspection (no IME service, zero permissions).

## Execution Order

1. Spec docs (this file, spec.md, tasks.md).
2. `ProcessTextContract` + unit tests (the hardening seam first).
3. Sticky-contact key in `data:preferences`.
4. `CompanionViewModel` + shared UI composables.
5. Three activities + manifest filters + labels.
6. Home-screen rework (companion status, clipboard actions, how-to).
7. Remove `:keyboard` from `:app`; bump version; changelog.
8. Full test run, debug + signed release builds, badging verification.
9. Deliver APKs to `download/`; push branch; report.

## Risks & Mitigations

- **Selection toolbar variance per app** — inherent; fallback ladder
  shipped simultaneously; device test matrix is the maintainer's P1 gate.
- **PROCESS_TEXT result ignored by some fields** — fallback ladder +
  "Copy" always available in the result screen.
- **Keystore identity change (v0.1.0 keystore lost)** — disclosed in
  changelog; v0.1.0 never ran on device; uninstall-before-install noted.
- **Sticky contact pointing at a deleted contact** — clamped to null on
  read; contact picker always reachable.
- **Compose activity theming regressions** — reuse the app theme; the new
  activities are plain `ComponentActivity` + `setContent` (no IME
  lifecycle involvement).
