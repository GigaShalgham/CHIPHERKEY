# Feature Specification: Option E — Hybrid Companion Architecture (Implementation)

**Feature Branch**: `003-option-e-companion`

**Created**: 2026-11-04

**Status**: Approved — implementation phase (P1 + P2 core)

**Input**: Maintainer directive following the architecture reassessment
(`docs/ARCHITECTURE_REASSESSMENT_REPORT.md`, spec 002): implement the
recommended Option E — a hybrid companion architecture that keeps Samsung
Keyboard as the user's keyboard and delivers CipherKey encryption through
OS-level text mechanisms (selection toolbar, sharesheet, clipboard).

## Scope

### In scope (P1 + P2 core, per report §10)

1. **Encrypt entry** — exported Activity handling `ACTION_PROCESS_TEXT`
   (editable variant) labeled "Encrypt (CipherKey)": receives the selected
   draft via `EXTRA_PROCESS_TEXT`, encrypts it for the chosen (sticky)
   contact with the existing `MessageCipher.encryptDraft`, returns the CK1
   envelope via `setResult` so the selection is replaced in place.
2. **Decrypt entry** — exported Activity handling `ACTION_PROCESS_TEXT`
   (including the `EXTRA_PROCESS_TEXT_READONLY` variant) labeled
   "Decrypt (CipherKey)": receives the selected ciphertext, decrypts via
   `MessageCipher.decryptEnvelope`, shows an explicit preview, offers
   Copy and — in editable contexts — result-insertion.
3. **Share target** — exported Activity handling `ACTION_SEND text/plain`
   labeled "Decrypt with CipherKey": same decrypt-preview flow for
   messengers where sharing is more natural than selection.
4. **Clipboard fallbacks** — the main app's Home screen offers explicit
   "Encrypt clipboard" / "Decrypt clipboard" actions through the existing
   `ClipboardAccess` chokepoint, completing the fallback ladder when the
   selection toolbar is unavailable.
5. **Sticky contact** — the last-used contact is remembered (one DataStore
   key in `data:preferences`) so the encrypt flow usually needs zero extra
   taps. Passphrase is prompted only when the session is locked.
6. **Keyboard decoupled** — `:keyboard` leaves the release APK (removed
   from `:app` dependencies; the module stays in `settings.gradle.kts`
   and keeps compiling in CI). The IME service therefore no longer exists
   in the shipped app, removing the entire Compose-in-IME crash class.
7. **Input hardening** — arbitrary/fuzzed input to the processing
   activities must never crash: null/blank `EXTRA_PROCESS_TEXT`,
   oversized selections (> 16 KiB UTF-8), non-envelope junk in the decrypt
   flow — all handled with graceful notices.

### Out of scope (evidence-gated or later phases)

- Quick Settings tile (report phase 4 — after device feedback).
- Floating overlay (deferred indefinitely without friction data).
- Public-key mode B, old-era decryption (independent crypto roadmap).
- Any change to the crypto core, codecs, or persistence schema.
- Deleting the `:keyboard` module (preserved in-repo per report §5.4).
- The v0.2.0 GitHub release/tag itself: the APK is built for maintainer
  device testing first; the release happens only after P1/P2 acceptance
  on the real Samsung device (report §10 acceptance criteria).

## Constraint: reuse, not rework

The verified stack is preserved exactly (report §13): `:core:*`,
`:data:*`, `:security:*`, `:plugins:*` are unchanged except for one
additive DataStore key (sticky contact). All new UI code lives in `:app`.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Send an encrypted message from any app (Priority: P1)

As a user, I want to select the message I just typed with my normal
keyboard and tap "Encrypt (CipherKey)" in the selection toolbar, so the
draft is replaced by a CK1 envelope I can send normally — without
switching keyboards.

**Why this priority**: this is the riskiest integration (per-app selection
toolbar variance) and the primary new mechanism; everything else either
supports it or is a fallback for it.

**Independent Test**: on a real device, in a standard editable field, the
toolbar entry appears, and confirming replaces the selection with a `CK1:`
envelope that round-trips byte-exact through decrypt.

### User Story 2 - Read an encrypted message from any app (Priority: P1)

As a recipient, I want to select (or share) a received `CK1:` message and
tap "Decrypt (CipherKey)", so I can read the plaintext in an explicit
preview and copy it — without CipherKey ever persisting it.

**Independent Test**: decrypt via both entries (selection on read-only
text, share target) shows the plaintext preview with the contact name and
copies on demand.

### User Story 3 - The fallback ladder always exists (Priority: P2)

As a user in an app whose selection toolbar does not offer CipherKey, I
want to copy the text, open CipherKey, and use "Encrypt clipboard" /
"Decrypt clipboard", so the workflow completes anyway.

**Independent Test**: with the toolbar entries unavailable, both clipboard
actions complete their workflow from the Home screen.

### User Story 4 - No new attack surface (Priority: P1)

As a security-conscious user, I want the companion app to keep the
zero-permission property and to harden its exported activities against
arbitrary input, so adding OS integration points does not add risk.

**Independent Test**: merged manifest still requests zero permissions; JVM
unit tests feed junk (null, blank, oversized, malformed, adversarial
unicode) into the intent-parsing contract and assert graceful outcomes.

### User Story 5 - The fragile IME path is gone from the APK (Priority: P1)

As the maintainer whose device crashed on launch, I want the shipped app
to no longer register an IME service at all, so the documented crash class
cannot occur while the keyboard code remains available in-repo.

**Independent Test**: `aapt2 dump badging` / manifest inspection of the
built APK shows no IME service; `:keyboard` still compiles in CI.

## Technical Requirements

- **FR-001**: Three exported activities (Encrypt, Decrypt, Share-decrypt)
  with correct intent filters (`ACTION_PROCESS_TEXT` + `DEFAULT` +
  `text/plain`; `ACTION_SEND` + `DEFAULT` + `text/plain`) and
  user-visible labels exactly as specified in the workflows.
- **FR-002**: The encrypt activity returns its result via
  `setResult(RESULT_OK, intent.putExtra(EXTRA_PROCESS_TEXT, envelope))`.
- **FR-003**: The decrypt activity must honor `EXTRA_PROCESS_TEXT_READONLY`
  (no insertion offer in read-only contexts).
- **FR-004**: All processing activities must be hardened: null/blank/absent
  extras, > 16 KiB UTF-8 selections, non-envelope text in decrypt mode —
  graceful notice, never a crash.
- **FR-005**: Sticky contact persisted via `data:preferences` (single new
  key); cleared contact selections never leak into other contacts.
- **FR-006**: `:app` no longer depends on `:keyboard`; `settings.gradle.kts`
  still includes `:keyboard` so CI keeps compiling it.
- **FR-007**: versionCode 2, versionName 0.2.0; release signing via the
  external-keystore env mechanism (new keystore — the v0.1.0 keystore was
  lost with the sandbox; identity change disclosed in the changelog).
- **FR-008**: Zero Android permissions requested (unchanged).
- **FR-009**: Plaintext previews are never written to disk; passphrase
  fields use `visualTransformation` masking; `CharArray` wiped after use.
- **FR-010**: CHANGELOG gains an `[0.2.0-alpha]` (unreleased) entry whose
  claims match what is actually built.

### Key Entities

- **ProcessTextContract**: pure-JVM object holding the intent-parsing and
  hardening rules (sanitize, size caps, envelope detection) — the unit-
  tested seam between arbitrary OS input and the app.
- **CompanionViewModel**: shared state machine for the processing flows
  (contact selection, unlock, encrypt, decrypt, clipboard write-back).

## Release Plan

No tag, no GitHub release in this spec. Deliverable: a signed
`assembleRelease` APK (plus the standard debug APK) placed in the
maintainer's `download/` area for the P1/P2 device test matrix (report
§10). The v0.2.0 release happens in a follow-up only after device
acceptance.

## Quality Bar

- Full JVM test suite green (140 existing + new contract tests).
- `assembleDebug` and signed `assembleRelease` green.
- Badging verification: no IME service, zero permissions, correct
  version/identity.
- Every user-visible string in scope added to `strings.xml` (no inline
  literals in new composables where a resource is the established
  pattern; the existing app uses inline English strings — new UI follows
  the existing inline-English convention for consistency, documented
  here as an accepted debt).
