# Feature Specification: GitHub Synchronization, CI Hardening, and v0.1.0 Alpha Release

**Feature Branch**: `001-github-sync-release`

**Created**: 2026-11-04

**Status**: Approved — implementation in progress

**Input**: Maintainer directive: make the private GitHub repository
(`GigaShalgham/CHIPHERKEY`) the permanent source of truth, gate it with
CI, and publish an honestly-labeled v0.1.0-alpha release.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Repository becomes the source of truth (Priority: P1)

As the repository owner, I want the full project history, specifications,
and engineering artifacts synchronized to GitHub so that the repository —
not any local sandbox — is the authoritative copy.

**Why this priority**: without synchronization all other work is at risk
of loss; every later workflow depends on the remote existing.

**Independent Test**: `git ls-remote origin main` returns the SHA of local
`main`, and the GitHub API reports the repository as non-empty.

**Acceptance Scenarios**:

1. **Given** a clean local history and an empty remote, **When**
   `git push origin main` runs, **Then** the remote default branch points
   at the pushed commit and no force-push was used.
2. **Given** a completed push, **When** the remote is queried via API,
   **Then** the returned SHA equals local `git rev-parse main`.
3. **Given** the credential file, **When** Git authenticates, **Then** the
   PAT is read from outside the repository and never appears in the tree,
   the history, or any log.

### User Story 2 - CI gates every push (Priority: P1)

As a maintainer, I want every push and pull request to run unit tests, a
debug build, wrapper validation, and a secret scan, so that broken or
leaking code cannot land silently.

**Why this priority**: CI is the enforcement arm of Constitution
principles II (honesty) and V (test-before-claim).

**Independent Test**: the Actions run triggered by the initial push
completes with the test, build, and secret-scan jobs green.

**Acceptance Scenarios**:

1. **Given** a push to `main`, **When** CI completes, **Then**
   `./gradlew test` and `./gradlew assembleDebug` both succeeded on the
   runner.
2. **Given** the repository history, **When** gitleaks scans it, **Then**
   zero secrets are reported.
3. **Given** the Gradle wrapper JAR in the tree, **When** wrapper
   validation runs, **Then** the JAR matches the official distribution.

### User Story 3 - Honest v0.1.0-alpha release (Priority: P1)

As a prospective user, I want a release containing an APK I can verify
(package, version, permissions, signature) and notes that state exactly
what is and is not verified, so I can make an informed decision before
installing an alpha of a security tool.

**Why this priority**: an unverified security artifact with inflated
claims is worse than no release at all.

**Independent Test**: the published release is marked pre-release;
`aapt2 dump badging` on the asset shows `org.cipherkey`, versionName
`0.1.0`, and no system permissions; `apksigner verify` passes; the
SHA-256 of the asset matches the value in the notes.

**Acceptance Scenarios**:

1. **Given** the tagged tree, **When** the release APK is built and
   signed, **Then** `apksigner verify --print-certs` succeeds and the
   certificate SHA-256 fingerprint is published.
2. **Given** the release page, **When** a user reads the notes, **Then**
   the alpha status, the absence of on-device testing, and the
   minification decision are explicitly stated.
3. **Given** the release assets, **When** the APK SHA-256 is recomputed,
   **Then** it matches the checksum published in the release notes.

### User Story 4 - Community health files (Priority: P2)

As a contributor, I want CONTRIBUTING, a Code of Conduct, issue/PR
templates, a CHANGELOG, and a SECURITY policy in place, so participation
is structured and expectations are clear.

**Why this priority**: improves quality of future contributions but does
not block the release.

**Independent Test**: new issues and PRs on GitHub render the templates;
the CHANGELOG contains a 0.1.0 entry consistent with the engineering
report.

**Acceptance Scenarios**:

1. **Given** the merged tree, **When** a new issue is opened, **Then**
   the bug/feature templates are offered.
2. **Given** the CHANGELOG, **When** the 0.1.0 entry is read, **Then**
   its claims match the machine-verified test count (140 unique tests,
   15 classes, 0 failures).

### Edge Cases

- Push rejected (remote unexpectedly non-empty): stop and reconcile the
  histories; never force-push (Constitution, Development Workflow).
- CI runner differences vs the sandbox (memory, mirrors): the workflow
  uses stock GitHub runners, stock repositories, and no sandbox-specific
  mirror injection.
- Signing keystore unavailable in CI: CI release builds remain unsigned;
  release signing is a maintainer-local step, documented in the plan.
- GitHub API failure during asset upload: retry with backoff and verify
  the uploaded asset digest before declaring success.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: The repository MUST be pushed to
  `https://github.com/GigaShalgham/CHIPHERKEY.git` branch `main` without
  history rewriting.
- **FR-002**: CI MUST run on push (`main`) and pull requests: unit tests
  (`./gradlew test`), debug APK build (`./gradlew assembleDebug`), Gradle
  wrapper validation, and secret scanning.
- **FR-003**: The release MUST provide a signed APK for tag `v0.1.0`
  passing `apksigner verify`, with the certificate fingerprint disclosed.
- **FR-004**: Release notes MUST disclose: alpha classification, absence
  of on-device testing, minification disabled for this alpha, and the
  v0.1 known limitations.
- **FR-005**: The release MUST publish the APK SHA-256 checksum.
- **FR-006**: Community files MUST be added: `CODE_OF_CONDUCT.md`,
  issue templates, PR template, `CHANGELOG.md`.
- **FR-007**: The engineering report MUST reflect machine-verified numbers
  only (140 unique tests / 15 classes / 0 failures).

### Key Entities

- **Release artifact**: `CipherKey-v0.1.0-alpha-release.apk` plus its
  published SHA-256 checksum.
- **Signing identity**: maintainer-generated keystore held outside the
  repository; its SHA-256 fingerprint is published for user verification.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: Remote `main` SHA equals local `main` SHA after push
  (verified via the GitHub API).
- **SC-002**: The CI run on the initial push completes green (test,
  build, wrapper-validation, secret-scan jobs).
- **SC-003**: Release `v0.1.0` exists, marked as pre-release, with one
  APK asset whose SHA-256 matches the published checksum.
- **SC-004**: A secret scan over the pushed tree and history finds no
  credentials (`github_pat_`, private keys, keystores).

## Assumptions

- The GitHub repository remains private and the provided fine-grained PAT
  remains valid for it; the PAT is stored outside the repository in a
  protected file with mode 0600.
- No on-device or emulator testing is possible in the current
  environment; the release is therefore classified alpha.
- The signing keystore is generated during this release session and
  persisted outside the repository; users verify its fingerprint.
- Variant double-counting of Android-library unit tests is excluded from
  all reported totals (dedup by test class).
