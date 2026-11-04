# Implementation Plan: Architecture Reassessment and Integration Feasibility Study

**Feature Branch**: `002-architecture-reassessment`

**Created**: 2026-11-04

**Status**: Research plan — no code changes under this plan

**Input**: `specs/002-architecture-reassessment/spec.md`

## Approach

This is an investigation-first plan. Every activity produces evidence or
a document; none produces application code. The plan is ordered so that
the crash forensics and the option research feed the same comparison
model, and the recommendation is written only after all five options have
been evaluated on that model.

## Research method and tooling

| Activity | Method | Tools |
| --- | --- | --- |
| APK forensics | manifest as built, DEX class inventory, signature scheme inventory, native-library ELF alignment | `androguard` (Python), zip/ELF parsing script, `zipfile` |
| Launch-path tracing | read both entry chains (app activity, IME service) end-to-end, list every crash candidate with trigger conditions | code reading, module graph from `settings.gradle.kts` |
| Platform API research | per-API verification against Android documentation and primary sources, dated | web search + fetch of developer.android.com, project pages |
| Keyboard ecosystem research | maintenance status, license, Persian support, release cadence of each candidate | project repositories, F-Droid metadata, release pages |
| Report writing | English engineering documents in the repo; canonical markdown | markdown in `docs/` and `specs/002-architecture-reassessment/` |

## Phase 1 — Crash forensics (no device available)

1. Reconstruct the toolchain state of the delivered artifact: versions
   (AGP 8.13.2, Kotlin 2.2.21, compileSdk/targetSdk 36, Compose BOM
   2025.12.01, Hilt 2.56.2, Room 2.8.5) from `gradle/libs.versions.toml`.
2. Inspect `CipherKey-v0.1.0-alpha-release.apk`: package/version,
   SDK floors, application class, launcher activity, IME service
   declaration, permissions, signature schemes, DEX inventory (Hilt
   generated components, all module classes), native libraries and their
   ELF page alignment.
3. Trace the app-launch path: `CipherKeyApplication` → `MainActivity` →
   ViewModels → Hilt graph → Room/DataStore initialization. Record any
   main-thread blocking, eager work, or version-sensitive call.
4. Trace the IME path: `CipherKeyboardService` → `KeyboardLifecycleOwner`
   → `ComposeView` hosting → `ImeController`. Compare against the
   documented ComposeView-in-IME pattern and its known failure modes.
5. Research documented crash classes for this stack
   (Compose-in-IME lifecycle crashes, per-OEM IME behavior).
6. State conclusions in three labeled buckets: machine-verified artifact
   facts, static code findings, unconfirmed hypotheses. Define the
   maintainer-side log-capture procedure that would confirm each
   hypothesis.

## Phase 2 — Option A: Samsung Keyboard integration

1. Verify whether Samsung Keyboard source is published anywhere
   (opensource.samsung.com inventory, GitHub Samsung org).
2. Verify whether any official Samsung keyboard extension/plugin SDK
   exists (developer.samsung.com SDK catalog).
3. Establish what a third party may legally do with/on Samsung Keyboard.
4. Consequence analysis: if integration is impossible, say so and
   explain what remains (OS-level mechanisms that do not require the
   keyboard's cooperation).

## Phase 3 — Options B, C, E: companion mechanisms

1. ACTION_PROCESS_TEXT: mechanism (intent filter, EXTRA_PROCESS_TEXT,
   result replacement), platform availability per API level, where it
   appears (selection toolbar), password-field and read-only behavior,
   known app-level inconsistencies, One UI behavior.
2. Sharesheet: ACTION_SEND text targets, returning results, share-sheet
   availability in messaging apps (for incoming messages).
3. Clipboard: focus rules (Android 10+ background restriction),
   transparency toast (Android 12+), auto-clear (Android 13+),
   implications for each workflow step.
4. Overlay (Option C): SYSTEM_ALERT_WINDOW grant flow, Android 11+
   restrictions, inability to read other apps' text, battery/background
   constraints, One UI behavior; treat as optional shortcut only.
5. Quick Settings tile: TileService availability, lifecycle, click →
   activity launch, permission cost.
6. Synthesize Option E as the composition: which mechanism is primary,
   which are fallbacks, which are optional accelerators.

## Phase 4 — Option D: open-source keyboard foundations

1. Candidate list: FlorisBoard, AOSP LatinIME, OpenBoard, HeliBoard,
   AnySoftKeyboard (and others surfaced during research).
2. For each: maintenance status (latest release, cadence), license and
   copyleft consequences for an Apache-2.0 project, Android
   compatibility floor, stability reputation, Persian support (layout
   and dictionary), architecture/extensibility, integration complexity,
   known limitations.
3. Judge each against the maintainer's constraint that the user keeps
   Samsung Keyboard as the default (Option D contradicts it by
   definition — record that).

## Phase 5 — Comparison, reuse mapping, workflows

1. Build the fourteen-criteria comparison table (A–E). Rating basis is
   stated per cell; facts vs assumptions are labeled.
2. Map all fourteen Gradle modules to keep / extend / decouple /
   retire, with rationale tied to the module inventory.
3. Specify outgoing and incoming workflows with the required fields
   (exact actions, APIs, app-support dependency, failure/fallback,
   manual-copy, security implications). No universal compatibility
   claims.
4. Security and privacy analysis of the recommended option: permission
   inventory, data-in-flight analysis of each mechanism, comparison
   with the IME-era exposure, residual risks.

## Phase 6 — Prototypes, roadmap, and the decision report

1. Define the smallest validating prototypes and their measurable
   acceptance criteria (the maintainer's nine criteria, plus emulator
   vs. real-device separation).
2. Roadmap with phases, complexity estimate, and major risks.
3. Outstanding questions and unresolved uncertainties.
4. Write `docs/ARCHITECTURE_REASSESSMENT_REPORT.md` (canonical,
   English) with all fourteen sections and one recommendation.
5. Render a copy for maintainer review into `download/`.

## Risks / mitigations for this research plan

- **Research depth vs honesty**: platform behavior varies by OEM and
  firmware; anything not verifiable from public sources is labeled an
  open question with a device-test task rather than assumed.
- **No crash reproduction possible** (no emulator/device): mitigated by
  the three-bucket evidence labeling and the defined log-capture
  procedure.
- **Analysis bias toward preserving existing work**: mitigated by
  evaluating all five options against the same criteria before any
  recommendation is drafted, and by explicitly scoring "dependence on
  proprietary software" and "reliability" per option.
- **Lost GitHub PAT**: all work is local; pushing is deferred and noted
  in the final summary (FR-009 still satisfied — branch exists
  locally, `main` untouched).
