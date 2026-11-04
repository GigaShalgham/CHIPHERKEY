# Spec 004 — Samsung device fixes for the companion workflows

**Status**: implemented
**Input**: maintainer device report (Galaxy A54 5G), 2026-11-04
**Branch**: `004-samsung-companion-fixes` (off `003-option-e-companion`)

## Problem statement

On the maintainer's Samsung Galaxy A54 5G (Android 13+ / One UI), with
v0.2.0-alpha installed:

1. Copying text in another app and tapping **Encrypt clipboard** /
   **Decrypt clipboard** in CipherKey reported "the clipboard is empty"
   even though the clipboard demonstrably worked (paste into Notes).
2. The **Encrypt (CipherKey)** / **Decrypt (CipherKey)** entries never
   appeared in the text-selection toolbar in other apps.

Both fallback-ladder rungs were therefore unusable on the device.

## Root causes (investigated, not guessed)

### RC-1 — Clipboard read before window focus (real code defect)

`BaseCompanionActivity.onCreate()` called `dispatchIntent(intent)`
immediately, which for clipboard launches read the clipboard via
`ClipboardAccess.currentText()` **during onCreate**. On Android 10+
(API 29+), `ClipboardManager.getPrimaryClip()` only serves the app that
owns the *focused* window; a freshly created activity has no focus yet
(focus is granted asynchronously after resume/first draw). The read
returned null and the UI collapsed every failure mode into
"The clipboard is empty".

Evidence: `onCreate → dispatchIntent → peekClipboard → currentText →
manager.primaryClip == null` — no focus check anywhere; the old
`ClipboardAccess` doc comment even (incorrectly) assumed "while visible"
implied "focused".

Fix: clipboard launches are deferred to `onWindowFocusChanged(hasFocus =
true)` — the documented point where the window owns focus — with an
idempotence guard, a "Reading the clipboard…" transient state, and an
explicit **Try again** affordance on clipboard notices. The taxonomy
(`ClipboardRead`: Text / Empty / NonText / Blocked) now distinguishes a
genuinely empty clipboard, a non-text clip (e.g. `image/png`), and an
access denial (OEM `SecurityException`), each with its own message.
No background monitoring, no new permissions, no behavior changes to
crypto.

### RC-2 — Selection-toolbar entries not visible on One UI (not a manifest defect)

The merged manifest **in the shipped v0.2.0 APK** was verified with
`aapt2 dump xmltree`: both companion activities declare
`ACTION_PROCESS_TEXT` + `CATEGORY_DEFAULT` + `mimeType="text/plain"` +
`exported="true"`, exactly matching AOSP `TextView`'s
`createProcessTextIntent()` query. The filters are correct.

Where the entries actually are on modern Android/One UI: third-party
PROCESS_TEXT handlers appear in the selection toolbar's **overflow
(⋮)** menu, and many popular apps (WhatsApp/Telegram bubbles, etc.)
implement custom selection UIs that never surface PROCESS_TEXT handlers
at all. One UI 6.1+ additionally crowds the primary row with Galaxy AI
entries. This is environmental, not fixable in our manifest — the
designed answer is the (now fixed) clipboard fallback ladder, plus a
precise device test procedure (T-004-D) that tells the maintainer exactly
where to look.

### RC-3 — `EXTRA_PROCESS_TEXT_READONLY` ignored on the encrypt path (found during investigation)

`EncryptActivity` did not read `EXTRA_PROCESS_TEXT_READONLY`, so when the
entry was invoked on a selection inside a read-only view, the result card
offered "Replace in field" even though the calling app would ignore the
result (PROCESS_TEXT contract: no result may be returned for read-only
selections). Fixed: the flag is now read and suppresses the affordance.

## Requirements

- FR-1: Clipboard reads happen only while the hosting activity's window
  has focus; never during `onCreate`.
- FR-2: Empty vs non-text vs blocked clipboard outcomes produce distinct,
  honest notices; retry is available where it can help.
- FR-3: `EXTRA_PROCESS_TEXT_READONLY` respected on both PROCESS_TEXT
  activities.
- FR-4: No new permissions, services, receivers, accessibility usage, or
  IME re-introduction; Samsung Keyboard stays the default keyboard.
- FR-5: The full existing suite stays green; new tests cover the
  clipboard taxonomy (Persian, emoji, multiline, empty, non-text,
  blocked), PROCESS_TEXT malformed inputs, and both-workflow round trips.
- FR-6: No stable release, no merge to `main`, until the maintainer
  verifies both workflows on the actual device.

## Emulator verification plan (AOSP, API 34)

1. Install the **v0.2.0** APK, reproduce RC-1 via the AOSP Messaging app:
   type text → select → Copy → CipherKey → Encrypt clipboard → observe
   the false "clipboard is empty" notice.
2. Install the **v0.2.1** APK over it (same signing key) and repeat:
   the prepare screen must show the draft.
3. Verify PROCESS_TEXT resolution: select text in an editable field,
   open the toolbar overflow, confirm both CipherKey entries launch the
   sheet with the selected draft.

Samsung One UI specifics cannot be reproduced on AOSP — the maintainer
procedure (T-004-D) is the acceptance gate.
