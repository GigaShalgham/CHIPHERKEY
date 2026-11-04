# Tasks: Samsung device fixes for the companion workflows

**Input**: spec.md (this directory)
**Test gate**: full JVM suite green; emulator reproduction of RC-1;
maintainer device procedure for final acceptance.

## Phase 1 — Investigation (read-only)

- [x] T001 Read the complete clipboard path (HomeScreen buttons →
      EncryptActivity/DecryptActivity → BaseCompanionActivity.onCreate →
      CompanionViewModel.peekClipboard → AndroidClipboardAccess.currentText)
- [x] T002 Identify RC-1: read happens during onCreate, before window
      focus; Android 10+ denies clipboard access to unfocused windows
- [x] T003 Dump the merged manifest from the shipped v0.2.0 APK
      (`aapt2 dump xmltree`): PROCESS_TEXT + DEFAULT + text/plain +
      exported=true confirmed on both activities → RC-2 is environmental
- [x] T004 Find RC-3: EXTRA_PROCESS_TEXT_READONLY ignored by
      EncryptActivity

## Phase 2 — Fixes

- [x] T005 ClipboardAccess: add `ClipboardRead` taxonomy + pure
      `ClipboardParser`/`ClipSnapshot` seam; `read()` implementation
      with per-item runCatching and OEM SecurityException → Blocked;
      `currentText()` kept as a bridge for :keyboard
- [x] T006 BaseCompanionActivity: defer clipboard launches to
      `onWindowFocusChanged(true)`; idempotent; Idle phase renders
      "Reading the clipboard…"; retry wired only for clipboard launches
- [x] T007 CompanionViewModel: `beginFromClipboard(mode)` maps the
      taxonomy to distinct notices; `retryClipboard()` resets the flow;
      `beginEncrypt` takes readOnly
- [x] T008 EncryptActivity/DecryptActivity: clipboard branch uses
      beginFromClipboard; EncryptActivity reads
      EXTRA_PROCESS_TEXT_READONLY
- [x] T009 NoticeCard: optional "Try again" button
- [x] T010 Version bump: versionCode 3 / versionName 0.2.1

## Phase 3 — Tests

- [x] T011 ClipboardParserTest (22 tests): ordinary text, Persian,
      emoji, multiline LF/CRLF, mixed RTL/emoji/Latin, null snapshot,
      zero items, blank items, image/non-text mimes, precedence
      (usable text beats lying mime), Blocked reason round-trip
- [x] T012 CompanionWorkflowRoundTripTest (7 tests): selection→clipboard
      and clipboard→selection round trips over the real crypto stack,
      Persian/emoji/multiline matrix, non-text classification,
      blocked-vs-empty distinction, PROCESS_TEXT null/malformed/oversized
      inputs, non-envelope decrypt outcome
- [x] T013 :keyboard FakeClipboard updated for the new interface;
      module still compiles (not shipped)
- [x] T014 Full suite green: 187 unique tests / 18 classes / 0 failures

## Phase 4 — Verification and delivery

- [x] T015 AOSP API-34 emulator: reproduce RC-1 with the shipped v0.2.0
      APK (uiautomator-evidenced)
- [x] T016 AOSP API-34 emulator: verify the v0.2.1 APK fixes RC-1
      (Encrypt clipboard shows the copied draft; round trip works)
- [x] T017 AOSP API-34 emulator: verify PROCESS_TEXT resolution
      (am start implicit PROCESS_TEXT intent resolves to CipherKey)
- [x] T018 Signed release build (same v0.2 keystore → in-place update),
      badging + manifest verification, SHA-256 recorded
- [x] T019 Commit on 004-samsung-companion-fixes; push; verify remote SHA
- [x] T020 Publish v0.2.1-alpha as a clearly-labeled device-testing
      release (NOT stable; no merge to main)

## Maintainer device gate (outside this spec's automation)

- [ ] T-004-D Samsung Galaxy A54 procedure (see plan): clipboard ladder
      in two apps; selection toolbar incl. the ⋮ overflow; sharesheet
      decrypt; report results — stable v0.2.1 waits for this
