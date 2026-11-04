# Implementation Plan: GitHub Synchronization, CI Hardening, and v0.1.0 Alpha Release

**Branch**: `001-github-sync-release` | **Date**: 2026-11-04 | **Spec**: `specs/001-github-sync-release/spec.md`

## Summary

Synchronize the existing local repository (15 Gradle modules, 76 Kotlin
files, full CK1 protocol implementation, 140 unique passing tests) to the
empty private GitHub repository `GigaShalgham/CHIPHERKEY`; harden the CI
workflow (Gradle caching, wrapper validation, gitleaks); add community
health files; correct the engineering report to machine-verified numbers;
and publish tag `v0.1.0` as a pre-release with a signed, verified APK.

## Technical Context

**Language/Version**: Kotlin 2.2.21 (KSP 2.2.21-2.0.5), JDK 21, AGP 8.13.2

**Primary Dependencies**: Jetpack Compose (BOM 2025.12.01), Hilt 2.56.2,
Room 2.8.5, DataStore 1.1.7, BouncyCastle 1.86 (bcprov-jdk18on)

**Storage**: Room DB (on-device), DataStore preferences; no network

**Testing**: JUnit 4 via Gradle `test` task; JUnit XML as source of truth;
`aapt2 dump badging` + `apksigner verify` for artifact verification

**Target Platform**: Android 8.0+ (minSdk 26, targetSdk 36); CI on
`ubuntu-latest` GitHub-hosted runners

**Build tooling**: Gradle 8.13 wrapper; sandbox uses a mirror-injection
init script that MUST NOT leak into the repo or CI (CI uses stock
repositories).

## US1 — Repository synchronization

- Credential: fine-grained PAT stored at a path outside every Git
  repository, mode 0600, referenced by a credential-helper script that
  outputs it only to Git's credential protocol. The literal value never
  appears in the tree, history, CI, or logs.
- `git remote add origin https://github.com/GigaShalgham/CHIPHERKEY.git`
- Push `main` (fast-forward, no force). Verify with
  `GET /repos/GigaShalgham/CHIPHERKEY/branches/main` → compare SHA with
  `git rev-parse main`.
- Feature branch `001-github-sync-release` is merged into `main` before
  the push; the branch is pushed as well for history transparency.

## US2 — CI hardening (`.github/workflows/android-ci.yml`)

- Keep: checkout, JDK 21 (temurin), Android SDK setup, `test`,
  `assembleDebug`, artifact upload, failure report upload.
- Add: `gradle/actions/setup-gradle@v4` (dependency caching),
  `gradle/actions/wrapper-validation@v4` (wrapper JAR integrity), and a
  `secret-scan` job running `gitleaks/gitleaks-action@v2` over the full
  history.
- CI must not depend on sandbox-specific mirrors or memory caps; stock
  runners have enough RAM for `test` + `assembleDebug`.

## US3 — v0.1.0-alpha release

- Signing: `app/build.gradle.kts` reads a properties file whose path is
  supplied via the `CK_KEYSTORE_PROPERTIES` environment variable
  (fallback: `keystore.properties` next to `settings.gradle.kts`,
  gitignored). If absent, release builds remain unsigned — CI-safe.
- Keystore: generated once with `keytool` (RSA 4096, 10000 days), stored
  outside the repository alongside the PAT; its SHA-256 fingerprint is
  published in the release notes.
- Build type `release` for this alpha: `isMinifyEnabled = false` and
  `isShrinkResources = false`, with a documented rationale (no on-device
  verification exists; R8 transformation would add unverified risk).
  Minification is deferred and tracked as follow-up work.
- Artifact verification: `aapt2 dump badging` (package `org.cipherkey`,
  versionCode 1, versionName 0.1.0, no system permissions),
  `apksigner verify --print-certs`, SHA-256 checksum.
- Release: REST API `POST /repos/.../releases` with `prerelease: true`,
  tag `v0.1.0` targeting the pushed `main` SHA; upload the APK to the
  release; re-fetch the asset and compare digests.

## US4 — Community health files

- `CHANGELOG.md` (Keep a Changelog format) with an `[0.1.0]` entry whose
  claims match the corrected engineering report.
- `CODE_OF_CONDUCT.md` (Contributor Covenant v2.1, adapted contact line).
- `.github/ISSUE_TEMPLATE/bug_report.md` and `feature_request.md`;
  `.github/PULL_REQUEST_TEMPLATE.md`.
- README: link the new files; add only truthful badges (license, CI
  status).

## Testing strategy

- Tests: `./gradlew test` — 140 unique tests / 15 classes must pass;
  counts read from JUnit XML, deduplicated by class.
- Build: `./gradlew assembleDebug` green; `assembleRelease` green locally
  with the keystore present.
- Artifact: badging + signature + checksum checks as above.
- Remote: API SHA comparison after push; release asset digest comparison.

## Rollout / order

1. Forensics and corrections (test-count truth) — completed pre-spec,
   folded in as FR-007.
2. Spec Kit adoption (constitution, this spec) — completed.
3. Community files + CI hardening + signing config (this branch).
4. Local verification (tests, debug build, release build + verify).
5. Merge to `main`, push `main` + branch, verify remote SHA.
6. Tag `v0.1.0`, create pre-release, upload asset, verify.

## Risks & mitigations

- Sandbox OOM during packaging: run `test` and `assemble*` as separate
  invocations with a capped daemon heap (documented in the report).
- Mirror flakiness: mirrors live only in the sandbox init script; CI uses
  stock repositories.
- PAT revocation: release/push would fail with 401; re-request a token
  through a secure channel, never log the old one.
- Unsigned-in-CI confusion: README states that CI APK artifacts are
  unsigned debug builds; only maintainer-published releases are signed.
