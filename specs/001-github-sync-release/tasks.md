# Tasks: GitHub Synchronization, CI Hardening, and v0.1.0 Alpha Release

**Input**: Design documents from `/specs/001-github-sync-release/`

**Prerequisites**: plan.md (required), spec.md (required)

**Tests**: verification tasks are mandatory for this spec (Constitution
principle V applies to release claims).

## Phase 1: Forensics and corrections *(completed pre-spec, folded in as FR-007)*

- [x] T001 Aggregate JUnit XML results machine-readably, deduplicated by
      test class, across all result locations
      (`test`, `testDebugUnitTest`, `testReleaseUnitTest`)
- [x] T002 Root-cause the 190-vs-140 discrepancy (variant double-count of
      the 5 Android-library test classes) and document it in the report
- [x] T003 Correct `ENGINEERING_REPORT.md` to 140 unique tests / 15
      classes / 0 failures in all four affected places
- [x] T004 Fresh clean rebuild + full test run in-session; verify XML
      timestamps; `assembleDebug` green

## Phase 2: Spec Kit adoption

- [x] T005 Install official `specify-cli` and verify version
- [x] T006 `specify init --here --force --non-interactive` (additive
      only; no existing file overwritten)
- [x] T007 Author the CipherKey Constitution (English, security-first,
      honesty non-negotiables)
- [x] T008 Scaffold spec 001 with `create-new-feature.sh`

## Phase 3: User Story 1 — repository becomes the source of truth (P1)

- [x] T009 Store the PAT outside all repositories (mode 0600) and wire a
      credential-helper script; verify GitHub API access without exposing
      the value
- [x] T010 Add remote `origin` → `https://github.com/GigaShalgham/CHIPHERKEY.git`
- [x] T011 Push `main` (and the feature branch); no force-push
- [x] T012 Verify remote SHA via GitHub API equals local `git rev-parse`
      (95ee9d46da5757ec0d08af629df7a6c8ed8ee9d8, MATCH)

## Phase 4: User Story 2 — CI hardening (P1)

- [x] T013 Add `gradle/actions/setup-gradle@v4` caching to the workflow
- [x] T014 Add `gradle/actions/wrapper-validation@v4`
- [x] T015 Add gitleaks secret-scan job scanning full history

## Phase 5: User Story 3 — v0.1.0-alpha release (P1)

- [x] T016 Add env-based signing config to `app/build.gradle.kts`
      (`CK_KEYSTORE_PROPERTIES` or gitignored `keystore.properties`);
      disable minification for the alpha with documented rationale
- [x] T017 Generate the release keystore OUTSIDE the repository
      (RSA 4096, 10000 days); record its SHA-256 fingerprint
- [x] T018 Build `assembleRelease`; verify with `aapt2 dump badging` and
      `apksigner verify --print-certs`; compute SHA-256
      (cert SHA-256 24870e1b6a9bca5b369ab09fc9401f8d31533b285d0788ec252d485a5a342cc4;
      APK SHA-256 7df7add9517cd5a7969566773c25e5a06266d149fb8043c59e83e33503f30184)
- [x] T019 Tag `v0.1.0` on the verified `main` commit
- [x] T020 Create the GitHub pre-release with honest notes and upload
      the APK asset
      (https://github.com/GigaShalgham/CHIPHERKEY/releases/tag/v0.1.0)
- [x] T021 Re-fetch the asset and verify its digest matches
      (byte-identical SHA-256 after download)

## Phase 6: User Story 4 — community health files (P2)

- [x] T022 Add `CHANGELOG.md` with `[0.1.0]` entry matching the report
- [x] T023 Add `CODE_OF_CONDUCT.md` (Contributor Covenant v2.1)
- [x] T024 Add issue templates (bug report, feature request) and a PR
      template
- [x] T025 Update README: community file links, truthful badges only

## Phase 7: Verification and finalization

- [x] T026 Secret-scan the final diff and full history
      (GitHub PAT tokens, private-key blocks, keystores, keystore
      properties) — clean
- [x] T027 Merge `001-github-sync-release` into `main`
- [x] T028 Update local `download/` deliverables (fresh APK copy,
      refreshed source archive, real README)
      (done: release APK, debug APK, source zip, and README with
      SHA-256 checksums verified present; README extended after spec 002
      to cover the architecture report PDF)
- [x] T029 Write the final session report (what changed, test evidence,
      commit hash, release link, remaining work)
      (done: `CipherKey-Session-Report-2026-11-04.pdf` delivered to
      `download/`, covering specs 001 + 002 and the PAT-restoration
      continuation)
