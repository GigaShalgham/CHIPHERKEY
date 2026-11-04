# Tasks: Architecture Reassessment and Integration Feasibility Study

**Input**: Design documents from `/specs/002-architecture-reassessment/`

**Prerequisites**: plan.md (required), spec.md (required)

**Tests**: research-only spec; verification = evidence presence in the
report (sources, labels, table completeness), not code execution.

## Phase 1 — Crash forensics

- [x] T001 Restore working context after sandbox recycle: verify repo
      state, branch, tags; note lost tooling (Android SDK, specify CLI,
      GitHub PAT) and their impact on this spec
- [x] T002 Forensically inspect the released APK: manifest as built
      (identity, SDK floors, components, permissions, signatures),
      DEX inventory (23,343 classes; Hilt components and all module
      classes present), native libs (4 ABIs, ELF 16 KiB-aligned)
- [x] T003 Trace the app-launch path (Application → MainActivity →
      ViewModels → Hilt → Room/DataStore) and record crash candidates
- [x] T004 Trace the IME path (service → KeyboardLifecycleOwner →
      ComposeView → ImeController) and compare with the documented
      ComposeView-in-IME pattern and its known failure modes
- [x] T005 Research documented crash classes for this stack
      (Compose-in-IME lifecycle crash and its known signature)
- [x] T006 Write the root-cause section with three labeled evidence
      buckets and the maintainer-side log-capture procedure

## Phase 2 — Option A: Samsung Keyboard integration

- [x] T007 Verify Samsung Keyboard source availability
      (opensource.samsung.com inventory, Samsung GitHub org)
- [x] T008 Verify the existence of any official Samsung keyboard
      extension/plugin SDK (developer.samsung.com catalog)
- [x] T009 Determine what a third party can legally build on or around
      Samsung Keyboard; state consequences explicitly

## Phase 3 — Options B, C, E mechanisms

- [x] T010 ACTION_PROCESS_TEXT: mechanism, result replacement,
      availability per API level, password/read-only behavior, app-level
      inconsistencies, One UI behavior
- [x] T011 Sharesheet: ACTION_SEND text targets and result flows
- [x] T012 Clipboard: focus rules, Android 12+ toast, Android 13+
      auto-clear, implications per workflow step
- [x] T013 Overlay: SYSTEM_ALERT_WINDOW grant flow, Android 11+
      restrictions, no underlying-text access, battery constraints,
      One UI behavior; scope to optional shortcut
- [x] T014 Quick Settings tile: TileService lifecycle and permission
      cost
- [x] T015 Synthesize Option E composition (primary path, fallbacks,
      optional accelerators)

## Phase 4 — Option D: open-source keyboards

- [x] T016 Evaluate FlorisBoard (status, Apache-2.0, Android floor,
      Persian support, extension architecture, integration complexity)
- [x] T017 Evaluate HeliBoard (status, GPL-3.0-only, AOSP lineage,
      Persian layout/dictionary state)
- [x] T018 Evaluate OpenBoard (maintenance status), AOSP LatinIME
      (productization gap), AnySoftKeyboard (Persian pack)
- [x] T019 Record Option D's conflict with the "keep Samsung Keyboard"
      constraint

## Phase 5 — Comparison, reuse, workflows

- [x] T020 Build the fourteen-criteria comparison table (A–E) with
      stated rating bases and fact/assumption labels
- [x] T021 Map all fifteen Gradle modules to keep / extend /
      decouple / retire with rationale
- [x] T022 Specify outgoing and incoming workflows with the six
      required documentation fields each
- [x] T023 Security and privacy analysis of the recommended option
      (permission inventory, per-mechanism data flow, residual risks)

## Phase 6 — Report and delivery

- [x] T024 Define the smallest validating prototypes with measurable
      acceptance criteria and emulator/real-device separation
- [x] T025 Write the roadmap, complexity estimate, and major risks
- [x] T026 Compile outstanding questions and unresolved uncertainties
- [x] T027 Write `docs/ARCHITECTURE_REASSESSMENT_REPORT.md` (canonical,
      English, fourteen sections + one recommendation)
- [x] T028 Render a review copy of the report into `download/`
- [x] T029 Commit everything on `002-architecture-reassessment`;
      `main` untouched; no release, no tag

## Deferred (require maintainer action — outside this spec)

- [ ] T030 Maintainer captures logcat from the failing device (or
      reproduces with the diagnostic procedure in the report) to close
      the open crash-cause question
- [ ] T031 Maintainer re-provides the GitHub PAT so the branch can be
      pushed; until then the work lives locally only
- [ ] T032 Maintainer reviews the recommendation and opens the
      implementation spec for the chosen architecture (spec 003)
