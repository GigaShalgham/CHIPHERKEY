# Feature Specification: Architecture Reassessment and Integration Feasibility Study

**Feature Branch**: `002-architecture-reassessment`

**Created**: 2026-11-04

**Status**: Approved — research phase in progress (no implementation)

**Input**: Maintainer directive: the first delivered CipherKey APK
(`v0.1.0`) failed immediately on the user's Samsung device. Re-evaluate the
entire interaction architecture — including whether a custom keyboard should
remain part of the product at all — and identify the most reliable way to
deliver CipherKey encryption while the user keeps their existing Samsung
Keyboard. Research and evaluation only.

## Constraint: this spec is intentionally non-destructive

- No new architecture may be implemented under this spec.
- No existing module or code may be removed.
- No rewrite of the application.
- No new release may be published from this spec.
- The output of this spec is: evidence, a comparison of options, and one
  clear recommendation for the maintainer to review before any
  implementation spec is opened.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - The failure is understood before any fix is chosen (Priority: P1)

As the maintainer, I want a root-cause analysis of the on-device failure
based on evidence (code inspection, APK inspection, documented platform
behavior), so that the architecture decision is not built on a guess.

**Why this priority**: every other decision in this spec depends on
understanding what actually failed; choosing an architecture to "fix" an
unknown crash would repeat the v0.1.0 mistake (shipping an unverified
integration).

**Independent Test**: the report contains a crash-analysis section that
separates (a) machine-verified facts about the delivered artifact,
(b) statically-verified facts about the code, and (c) hypotheses that
remain unconfirmed without device logs — with the log-capture procedure
that would confirm them.

**Acceptance Scenarios**:

1. **Given** the released APK, **When** it is inspected (manifest as
   built, DEX inventory, signatures, native libraries), **Then** the
   findings are documented and any structural defect is reported.
2. **Given** the source tree, **When** both launch paths (app activity
   and IME service) are traced, **Then** every crash candidate found is
   documented with its trigger condition.
3. **Given** that no logcat from the failing device exists, **When** the
   root-cause section states conclusions, **Then** each conclusion is
   labeled as confirmed fact, static finding, or hypothesis — nothing is
   asserted as the cause without evidence.

### User Story 2 - Every architecture option is evaluated on evidence (Priority: P1)

As the maintainer, I want Samsung Keyboard integration, a standalone
companion app, a floating assistant, alternative open-source keyboards,
and a hybrid each investigated independently, so the recommendation rests
on the complete option space rather than a premature favorite.

**Why this priority**: the maintainer explicitly asked for the option
space to be evaluated without assuming the current architecture must be
preserved.

**Independent Test**: for each of Options A–E the report documents what
was investigated, what is verified fact versus assumption (with sources),
and the concrete consequences for CipherKey.

**Acceptance Scenarios**:

1. **Given** the Samsung Keyboard question, **When** source availability
   and official extension APIs are researched, **Then** the answer (and
   its license/distribution consequences) is stated explicitly, using
   only legally available information.
2. **Given** each candidate mechanism (ACTION_PROCESS_TEXT, Sharesheet,
   clipboard, SYSTEM_ALERT_WINDOW overlay, Quick Settings tile,
   IME replacement), **When** platform behavior and restrictions are
   described, **Then** they are distinguished per Android version and
   labeled as verified or assumed.
3. **Given** the open-source keyboard candidates, **When** maintenance
   status, license, Android compatibility, and Persian support are
   assessed, **Then** each candidate is judged on current public state,
   not on the fact that it merely builds.

### User Story 3 - The cryptographic core survives the decision (Priority: P1)

As the maintainer, I want an inventory of which existing modules the
recommended architecture reuses unchanged, so that verified work (140
green unit tests, RFC 9106 vectors, pinned golden vectors) is not
discarded.

**Why this priority**: the crypto core is the project's proven asset;
any architecture that forces rewriting it needs a concrete technical
reason.

**Independent Test**: the report contains a module-by-module reuse table
mapping every existing Gradle module to keep / extend / decouple /
retire under the recommended architecture, with the reason for each.

**Acceptance Scenarios**:

1. **Given** modules `core/*`, `data/*`, `security/*`, `plugins/*`,
   `keyboard`, and `app`, **When** the recommended architecture is
   applied, **Then** each module's fate is stated with rationale.
2. **Given** the reuse table, **When** a module is marked "retire from
   the critical path", **Then** the report states that no code is
   deleted under this spec (research only).

### User Story 4 - The recommendation is reviewable before any implementation (Priority: P1)

As the maintainer, I want a comprehensive architecture decision report
containing root-cause analysis, option evaluations, a 14-criteria
comparison table, recommended workflows with failure/fallback behavior,
security analysis, API/permission inventory, prototype plan with
measurable acceptance criteria, roadmap, risks, and open questions —
ending with one clear recommendation.

**Why this priority**: the maintainer must review and approve the
direction before any implementation begins.

**Independent Test**: the report exists at
`docs/ARCHITECTURE_REASSESSMENT_REPORT.md` (canonical, English) with all
fourteen required sections plus the single recommendation, and a copy is
delivered to the maintainer for review.

**Acceptance Scenarios**:

1. **Given** the required report outline, **When** the report is read,
   **Then** all fourteen sections and the final recommendation are
   present and distinguish evidence from assumption.
2. **Given** the workflows in the report, **When** each is described,
   **Then** exact user actions, Android APIs involved, dependence on the
   calling app's support, failure/fallback behavior, manual-copy
   requirements, and security implications are documented — with no
   promise of universal compatibility.
3. **Given** the prototype plan, **When** acceptance criteria are read,
   **Then** they include: launches without crash, Samsung Keyboard
   remains default and functional, text encrypts through the chosen
   workflow, result returns or copies reliably, incoming ciphertext
   decrypts explicitly, Persian/Unicode preserved, no plaintext leaves
   the device, no unauthorized clipboard/accessibility access, and
   emulator results are explicitly separated from real-device results.

### User Story 5 - Evidence honesty (Priority: P1, constitutional)

As a security-tool user, I want the reassessment to follow the project
Constitution (security over features; honesty in claims), so that the
next architecture is chosen for reliability reasons that are stated
truthfully.

**Why this priority**: the project Constitution makes honesty
non-negotiable; this spec's output feeds a high-stakes decision.

**Independent Test**: every factual claim in the report that originates
outside the repository carries a source; every limitation of the
recommended option is stated as plainly as its advantages.

**Acceptance Scenarios**:

1. **Given** external platform facts (API behavior, Samsung source
   availability, keyboard project status), **When** they appear in the
   report, **Then** their source is cited and their verification date is
   noted.
2. **Given** the recommended architecture, **When** its weaknesses are
   assessed, **Then** they are documented in the same detail as its
   strengths.

### Edge Cases

- The failing device's logcat is unavailable: the report must state this
  plainly, rank hypotheses by evidence, and define the exact diagnostic
  procedure (adb commands / diagnostic build) to close the gap later.
- The sandbox lost the GitHub PAT after recycling: pushing the branch is
  deferred until the maintainer re-authorizes; the spec work itself is
  local and complete.
- An option cannot be fully verified from public sources (e.g., OEM
  behavior on a specific One UI version): it must be labeled an open
  question with a concrete device-test task, not silently assumed.
- Recommendation ambiguity between Options B and E: resolved by defining
  Option E as Option B's companion core plus optional accelerators
  (tile/overlay), so the recommendation stays a single architecture.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The spec MUST produce a root-cause analysis of the v0.1.0
  on-device failure that separates machine-verified artifact facts,
  static code findings, and unconfirmed hypotheses.
- **FR-002**: The spec MUST investigate and document Option A (Samsung
  Keyboard integration) including source availability, license status,
  and the existence or absence of official extension APIs, using only
  legal sources.
- **FR-003**: The spec MUST investigate Options B (standalone companion),
  C (floating assistant), D (alternative open-source keyboards), and E
  (hybrid) with per-option findings and consequences.
- **FR-004**: The spec MUST produce a comparison table covering the
  fourteen maintainer-supplied criteria, with the basis for every rating
  stated and facts distinguished from assumptions.
- **FR-005**: The spec MUST inventory every existing Gradle module and
  map it to keep / extend / decouple / retire under the recommendation,
  without deleting or modifying any code.
- **FR-006**: The spec MUST define outgoing and incoming user workflows
  with exact actions, Android APIs, app-support dependencies,
  failure/fallback behavior, manual-copy requirements, and security
  implications.
- **FR-007**: The spec MUST define the smallest validating prototypes
  with measurable acceptance criteria, explicitly separating emulator
  results from real-device results.
- **FR-008**: The spec MUST deliver the architecture decision report in
  English at `docs/ARCHITECTURE_REASSESSMENT_REPORT.md` and end with
  exactly one clear recommendation.
- **FR-009**: The work MUST be committed on branch
  `002-architecture-reassessment` without touching `main`; no release,
  no tag, no published artifact may result from this spec.
- **FR-010**: All engineering documents produced under this spec MUST be
  in English.

### Key Entities

- **Delivered artifact**: `CipherKey-v0.1.0-alpha-release.apk`
  (SHA-256 `7df7add9517cd5a7969566773c25e5a06266d149fb8043c59e83e33503f30184`)
  — the object of the failure analysis.
- **Architecture options A–E**: the five interaction architectures under
  evaluation, as defined by the maintainer directive.
- **Crypto core**: Gradle modules `core/*`, `data/*`, `security/*`,
  `plugins/*` — the verified, UI-independent asset to be preserved.
- **Report**: `docs/ARCHITECTURE_REASSESSMENT_REPORT.md` plus a rendered
  copy delivered for maintainer review.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: The report contains all fourteen required sections plus a
  single recommendation; every external fact carries a source.
- **SC-002**: The crash analysis names what was inspected (artifact
  structure, both launch paths, platform documentation) and explicitly
  lists what remains unconfirmable without device logs.
- **SC-003**: The comparison table rates all five options against all
  fourteen criteria with stated bases.
- **SC-004**: The reuse table accounts for all fifteen Gradle modules
  declared in `settings.gradle.kts`.
- **SC-005**: Branch `002-architecture-reassessment` contains the spec
  documents and report; `main` is untouched; no release or tag exists
  for this work.

## Assumptions

- The maintainer's Samsung device runs One UI on Android 8.0+ (the APK
  would not install below API 26); the exact model and firmware are not
  known to this investigation and are listed as an open question.
- No emulator or physical device is available in the working environment
  (no KVM acceleration); runtime reproduction of the crash is therefore
  impossible here and must be a maintainer-side step.
- The GitHub PAT used in the previous session was lost with the sandbox
  recycle; remote verification and pushing are deferred until it is
  re-provided. The local repository matches `origin/main` at the last
  verified SHA (`95ee9d46…` line plus the two CI-fix commits).
- Option evaluations rely on public documentation and project pages
  verified on 2026-11-04; OEM-specific behaviors that vary by firmware
  are treated as unverified until tested on the maintainer's device.
