# CipherKey — Architecture Decision Report

**Reassessment and Integration Feasibility Study**

| | |
| --- | --- |
| **Document status** | Draft for maintainer review — no implementation authorized |
| **Date** | 2026-11-04 |
| **Spec** | `specs/002-architecture-reassessment/` |
| **Trigger** | The delivered `v0.1.0` APK failed immediately on the maintainer's Samsung device |
| **Scope** | Research and evaluation only. No code was changed, no module removed, no release published. |
| **Evidence convention** | Statements are labeled **[VERIFIED]** (machine-checked in this session), **[DOCUMENTED]** (primary public source, cited), or **[ASSUMED]** (needs device test / maintainer input) |

---

## 1. Root-Cause Analysis of the Current Application Crash

### 1.1 What we know about the failure

The maintainer reported: the first delivered APK "did not load successfully
and crashed or closed immediately" on a Samsung Android device. **No logcat,
stack trace, or crash report was captured**, and no emulator or physical
device is available in the working environment (no KVM acceleration;
Android SDK absent after sandbox recycle). Therefore the exact crash site
**cannot be confirmed from here**. What follows is everything that *can* be
established with evidence, plus a procedure to close the gap.

### 1.2 Machine-verified facts about the delivered artifact

Inspection of `CipherKey-v0.1.0-alpha-release.apk`
(SHA-256 `7df7add9517cd5a7969566773c25e5a06266d149fb8043c59e83e33503f30184`) **[VERIFIED]**:

- Package `org.cipherkey`, versionName `0.1.0`, versionCode `1`.
- `minSdk 26` (Android 8.0), `targetSdk 36`.
- Application class `org.cipherkey.app.CipherKeyApplication` present.
- Launcher activity `org.cipherkey.app.MainActivity`, `exported=true`, with
  a valid MAIN/LAUNCHER intent filter — the app **is** launchable.
- IME service `org.cipherkey.keyboard.CipherKeyboardService` declared with
  `android.permission.BIND_INPUT_METHOD`, the `android.view.InputMethod`
  action, and `@xml/method` metadata — the IME declaration is well-formed.
- **Zero Android system permissions requested** (only the build-generated
  `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`).
- Signed with the v2 scheme only. v2 is honored by Android 7.0+; with
  minSdk 26 this is safe on every supported device.
- 23,343 classes across 3 DEX files. All Hilt-generated components
  (`Hilt_CipherKeyApplication`, Dagger components), all 15 modules' classes,
  and all key services (`MessageCipher`, `AndroidKeystoreKeyProtector`,
  `ImeController`, `KeyboardLifecycleOwner`) are present. **No missing-class
  or build-truncation defect.**
- 8 native libraries (Compose `androidx.graphics.path`, DataStore shared
  counter) covering all four ABIs; ELF `PT_LOAD` segments are 16 KiB-aligned
  — no 16 KiB-page-size hazard (and Samsung devices in the field are 4 KiB
  anyway).

**Conclusion:** the shipped APK is structurally complete and correctly
assembled. The failure is a *runtime* behavior, not a packaging defect.

### 1.3 Static analysis of the two launch paths

**Path 1 — App launch (icon tap):** `CipherKeyApplication` (empty Hilt root)
→ `MainActivity` (single `ComponentActivity`, Compose) → three ViewModels
whose repositories are Hilt singletons (Room database, DataStore). All
ViewModel work is dispatched to `Dispatchers.IO`; the first screen renders
static cards. **No deterministic crash was found** in this path: no
main-thread database access, no eager crypto, no unguarded framework calls,
no version-conditional code. **[VERIFIED — static]**

**Path 2 — IME enable/select:** the system binds
`CipherKeyboardService` (Hilt `@AndroidEntryPoint`), which hosts a
**ComposeView inside the InputMethodService** using a custom
`KeyboardLifecycleOwner` (implements `LifecycleOwner`,
`ViewModelStoreOwner`, `SavedStateRegistryOwner`). This is the documented
workaround for the well-known Compose-in-IME crash
(`IllegalStateException: Composed into the View which doesn't propagate
ViewTreeLifecycleOwner!` at `AndroidComposeView.onAttachedToWindow`) — see
Stack Overflow 65755763 **[DOCUMENTED]**. The pattern is implemented
correctly *on paper*, with two residual weaknesses **[VERIFIED — static]**:

1. The custom lifecycle owner only ever dispatches `ON_CREATE` /
   `ON_DESTROY`; it never advances to `ON_START`/`ON_RESUME` (the current
   Compose calls used are non-lifecycle-aware, but the margin is thin).
2. The ComposeView-in-IME integration is a documented fragile area whose
   behavior varies with Compose version and OEM firmware; the same Stack
   Overflow thread records version-specific follow-up breakage
   (2023–2024) **[DOCUMENTED]**.

### 1.4 Testing reality at release time

- 140 unique JVM unit tests / 15 classes / 0 failures — **all logic-level,
  zero Android-runtime coverage**. This was honestly disclosed in the
  v0.1.0 release notes and README ("On-device smoke test: not performed").
  The crash happened precisely in the untested layer: the Android runtime
  integration. **[VERIFIED]**

### 1.5 Ranked hypotheses (unconfirmed without device logs)

| # | Hypothesis | Supporting evidence | Plausibility |
| --- | --- | --- | --- |
| H1 | Crash occurred in the **IME path** when CipherKey was enabled/selected as keyboard (ComposeView-in-IME failure on that firmware) | The integration point matches a documented crash class; OEM-sensitive; never exercised on any device | Moderate–high **if** the keyboard was enabled before the failure |
| H2 | Crash on **app open** from a device/version-specific incompatibility in the very recent stack (Compose BOM 2025.12.01, Kotlin 2.2.21, targetSdk 36 on the device's Android version) | Stack is ~1 year newer than typical device test baselines; no static defect found, so if the app itself crashed, it is environment-specific | Cannot be excluded |
| H3 | **Install/environment issue** (Samsung Auto Blocker on One UI 6+, Play Protect interdiction, device below API 26, corrupted transfer) — presents as "did not load" | All are common on Samsung sideloads; would explain failure without any code defect | Moderate |
| H4 | **Android Keystore failure** on first contact creation on that device (OEM Keystore quirks) | Wrap/unwrap happens on first unlock; wrapped in try/catch paths but `loadOrCreateMasterKey` can throw | Low (requires the user to have gotten that far) |

### 1.6 Required diagnostic procedure (maintainer-side)

To convert hypotheses into a confirmed cause — do this **before** any
architecture work, it costs minutes:

1. `adb bugreport` right after the crash, or live:
   `adb logcat -b crash *:E` then reproduce (open app; then enable the
   keyboard in Settings → General management → Keyboard list and default).
2. Record: device model, One UI version, Android version, and **which
   action** crashed (opening the app vs. showing the keyboard).
3. Check install-time blockers: Settings → Security → Auto Blocker;
   Play Protect scan dialog.
4. If a diagnostic build is wanted later, spec 003 can add opt-in local
   crash logging (file-only, no network) — not added now (research-only
   constraint).

### 1.7 Can the current keyboard realistically be repaired?

**Possibly — but it does not matter for this decision.** Even a successful
repair would leave the product coupled to the single most fragile
integration surface on Android (custom IME + Compose rendering + OEM
firmware variance) with no device-lab capability to regression-test it,
while the maintainer's stated product goal is to **keep using Samsung
Keyboard**. The repairability question is therefore subordinate to the
architecture question. The `:keyboard` module stays in the repository,
untouched, under this spec; whether it ships in future APKs is a spec-003
decision.

---

## 2. Samsung Keyboard: Source Availability and Integration Options

Investigated using only legal, public sources (verified 2026-11-04).

| Question | Finding | Source / label |
| --- | --- | --- |
| Is Samsung Keyboard's source published? | **No.** Samsung's open-source release site (opensource.samsung.com) publishes 110+ projects — SoC/architecture, Tizen, network stacks, ML frameworks; the keyboard application is not among them. The Samsung GitHub org hosts no keyboard app either. | **[DOCUMENTED]** opensource.samsung.com/projects, github.com/SAMSUNG |
| Is there an official extension/plugin SDK for Samsung Keyboard? | **No.** The Galaxy SDK catalog covers S Pen Remote, AR Emoji, DeX, Blockchain, eSE, TEEGRIS, Health, Wallet, Themes… — nothing extends the keyboard. | **[DOCUMENTED]** developer.samsung.com SDK listing |
| Can a third-party app control or feed text into Samsung Keyboard? | **No public API exists** — Samsung's own developer forum confirms even programmatic input-language switching is impossible. On DeX, users cannot even fully replace it without adb workarounds. | **[DOCUMENTED]** forum.developer.samsung.com thread 18217; Samsung DeX community thread |
| What integration remains legally possible? | Only **OS-level mechanisms that do not require Samsung's cooperation**: the text-selection toolbar (`ACTION_PROCESS_TEXT`), Sharesheet, clipboard, notifications, tiles, overlays. These work with *any* keyboard, including Samsung's. | **[DOCUMENTED]** developer.android.com (see §4.3) |

**Consequence:** Option A in its literal form — building CipherKey *into*
Samsung Keyboard — is **infeasible**. The realistic reading of "integrate
with Samsung Keyboard" is "coexist with Samsung Keyboard", which is exactly
what Options B/E provide. No decompilation or extraction was attempted or
needed; the absence of source and APIs is itself the answer.

---

## 3. Evaluation of the Architecture Options

### 3.1 Option A — Integrate with Samsung Keyboard

- **Feasibility: blocked** (see §2). Closed source, no extension API, no
  plugin system, no documented intent surface.
- What remains possible is keyboard-*agnostic* OS integration
  (`ACTION_PROCESS_TEXT` appears in the selection toolbar regardless of the
  active IME — Google Translate's tap-to-translate works this way on One
  UI **[ASSUMED — to verify on the maintainer's device]**).
- **Verdict:** rejected as stated; its usable remainder is absorbed into
  Options B/E.

### 3.2 Option B — Standalone Companion Application

An app that lives *next to* the user's keyboard, offering explicit
encrypt/decrypt actions.

- **`ACTION_PROCESS_TEXT`** (API 23+): any app can add an action to the
  floating text-selection toolbar (e.g. "Translate", "Search Wikipedia").
  The activity receives `EXTRA_PROCESS_TEXT` (selected text) and can return
  a replacement via `setResult(...)` — the selected text in the original
  field is replaced. This is the exact mechanism Google Translate uses.
  **[DOCUMENTED]** Android Developers blog (Ian Lake, 2015); CommonsWare
  excerpt.
  Limitations **[DOCUMENTED]**: password fields never offer the action;
  read-only selections arrive with `EXTRA_PROCESS_TEXT_READONLY=true` and
  cannot be replaced; some apps filter or suppress the toolbar (documented
  Google Keep case, Stack Overflow 72910365); some apps (e.g. Facebook
  Messenger per Stack Overflow 54027838) ignore the returned replacement;
  WebView/Chrome support is partial/read-only.
- **Sharesheet**: registering for `ACTION_SEND text/plain` makes
  "Decrypt with CipherKey" reachable from any app's share menu — useful for
  incoming messages. **[DOCUMENTED]**
- **Clipboard**: foreground (focused) apps may read the clipboard; Android
  10+ blocks background reads; Android 12+ shows a system toast when an app
  reads (transparency); Android 13+ auto-clears the clipboard after a
  period. **[DOCUMENTED]** developer.android.com, "Secure clipboard
  handling".
- **Returning text**: via the PROCESS_TEXT result (editable fields), or the
  clipboard + manual paste (universal).
- **Verdict: strong.** Zero permissions, no keyboard replacement, every
  component is a bog-standard Activity — the most crash-resistant class of
  Android component. Residual risk is per-app toolbar variance, mitigated
  by clipboard fallbacks.

### 3.3 Option C — Floating CipherKey Assistant (overlay)

- Requires `SYSTEM_ALERT_WINDOW` — a special "draw over other apps" access
  granted in Settings (not a normal runtime permission). Android 11+
  tightened grants (Settings-only flow, auto-revocation for unused apps).
  **[DOCUMENTED]** developer.android.com, Android 11 privacy changes.
- **An overlay cannot read another app's text field.** Text must still
  arrive via clipboard (foreground rules apply) or explicit share. So the
  overlay is a *shortcut shell*, not an integration mechanism — exactly as
  the directive anticipated.
- Overlays are a flagged malware vector (tapjacking), so users see security
  warnings and some enterprise/MDM profiles forbid them. Battery-optimizer
  kills on Samsung require an exclusion ask. One UI does support legitimate
  overlay apps. **[DOCUMENTED]** OWASP MASTG; NowSecure.
- **Verdict: not a primary architecture.** Keep as an optional Phase-3
  accelerator if, and only if, user testing shows the app-switch costs too
  much friction.

### 3.4 Option D — Adopt an Alternative Open-Source Keyboard

| Candidate | Status (2026-11) | License | Android floor | Persian | Notes |
| --- | --- | --- | --- | --- | --- |
| **FlorisBoard** | Active (stable 0.5.2 Nov 2025; 0.6.0-alpha02 in progress) | Apache-2.0 | 8.0+ | "Limited support" for Persian/Arabic/Hebrew layouts; app itself localized to Farsi | No word suggestions yet (v0.6 goal); Compose-based; extension system still evolving; permissions: notifications, vibration |
| **HeliBoard** | Very active (v4.2, Oct 2026) | GPL-3.0-only | 5.0+ | Persian layout present; Persian *dictionary* missing (user-built possible) | AOSP/OpenBoard lineage; 100% offline; reads contacts permission; F-Droid distribution |
| **OpenBoard** | **Dormant** — last release Nov 2022, removed from Play Store 2023 | GPL-3.0 | 4.4+ | via layouts | Effectively superseded by HeliBoard |
| **AOSP LatinIME** | Maintained inside AOSP but **not productized** as a standalone app | Apache-2.0 | varies | via layouts | Building/forking it is a maintenance project in itself |
| **AnySoftKeyboard** | Maintained | Apache-2.0 | varies | Persian language pack exists | Dated UX; plugin architecture |

Sources: project repositories, F-Droid metadata, Wikipedia (HeliBoard),
release pages — **[DOCUMENTED]**.

**Verdict:** technically credible (FlorisBoard Apache-2.0 is
license-compatible; HeliBoard is the most actively maintained), but Option D
**contradicts the primary stated requirement**: the user keeps Samsung
Keyboard as their default. It also re-imports the entire class of IME risk
we are trying to escape (IME lifecycle, OEM variance, "keyboard must be
trusted with every keystroke"). Keep as a documented future alternative —
e.g. if the maintainer later wants *inline* typing encryption and accepts
switching keyboards. Note the copyleft consequence: a HeliBoard-derived
keyboard would be GPL-3.0 even though CipherKey is Apache-2.0 (acceptable
only as a separate distributed app, not merged into the Apache codebase).

### 3.5 Option E — Hybrid Architecture (recommended shape)

Composition, with each layer justified by the findings above:

1. **Samsung Keyboard remains the default and only keyboard.** (User's
   primary requirement; removes IME risk entirely.)
2. **Companion app** (Option B core) with:
   - **`ACTION_PROCESS_TEXT` activities** — "Encrypt" (editable selections;
     returns the CK1 envelope as replacement) and "Decrypt" (any selection;
     explicit decryption with preview, copy-out).
   - **Sharesheet target** ("Decrypt with CipherKey") — incoming fallback
     for apps whose share sheet is easier to reach than selection handles.
   - **Clipboard workflows** — universal fallback (copy → CipherKey →
     paste), incl. auto-detection of `CK1:` envelopes when the app opens.
   - **Quick Settings tile** (optional accelerator, Phase 2/3) —
     `TileService`, API 24+, no user-facing permission; tap → decrypt
     preview flow. **[DOCUMENTED]** developer.android.com Quick Settings
     tiles guide.
   - **Floating overlay** — deliberately deferred (Option C findings);
     revisit only with device-test evidence of friction.
3. **All crypto flows through the existing `MessageCipher` facade** — the
   verified core is untouched (see §14).

**Verdict: best balance** of reliability (standard components), user
requirement (Samsung Keyboard preserved), security (zero permissions,
explicit actions only), and reuse (100% of the crypto/data/security
modules).

---

## 4. Comparison Table

Ratings: **E**xcellent / **G**ood / **F**air / **P**oor / **X** (blocked).
Basis notes are keyed (b1–b14) below the table. Facts referenced are from
§§1–3 and their cited sources.

| # | Criterion | A: Integrate w/ Samsung Kb | B: Companion app | C: Floating overlay | D: Alternative keyboard | E: Hybrid (B core + accelerators) |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | User experience | X/P — nothing can be built (b1) | G — explicit but extra tap vs. inline (b2) | F — quick reach, awkward input (b3) | E — inline typing encryption (b4) | G — same as B plus tile shortcut |
| 2 | Preserve Samsung Keyboard as default | E (nothing changes — because nothing is possible) (b1) | E — no IME involved (b2) | E — no IME involved (b3) | **X — requires switching keyboards** (b4) | E — no IME involved |
| 3 | Reliability / crash risk | X (b1) | **E** — plain Activities, no service lifecycle (b5) | F — overlay + background-kill risk (b3) | F — IME lifecycle + OEM variance (b6) | **E** — same components as B |
| 4 | Messaging-app compatibility | n/a | G — PROCESS_TEXT + share + clipboard cover most; per-app variance documented (b7) | F — still needs clipboard for I/O (b3) | E — an IME types anywhere (b4) | G — same as B |
| 5 | Replace selected text in place | n/a | G — PROCESS_TEXT result replaces in standard fields; some apps ignore it (b7) | F — cannot write into other apps (b3) | E — the IME edits the field directly (b4) | G — same as B |
| 6 | Incoming-decryption workflow | n/a | G — share target / selection / clipboard, then paste (b7) | F — same inputs, extra permission (b3) | E — decrypt-on-tap inside the keyboard (b4) | G — same as B, plus tile |
| 7 | Android + One UI compatibility | n/a | E — API 23+ mechanisms, One UI renders the standard selection toolbar (b8) | F — overlay restrictions vary; battery managers (b3) | F/G — IME behavior varies per OEM (b6) | E — same as B |
| 8 | Permissions required | n/a | **E — zero system permissions** (b5) | P — SYSTEM_ALERT_WINDOW special access (b3) | F — vibration, often contacts/boot (b9) | E — zero for the core; tile adds none |
| 9 | Security & privacy posture | n/a | **E** — sees only explicitly selected/shared text (b10) | F — overlay = tapjacking-class surface, user warnings (b3) | P — keyboard observes *every keystroke* in *every field* (b10) | **E** — same as B |
| 10 | Development complexity | X | **Low** — 2 small activities + wiring to existing facade (b11) | Medium — service, window mgmt, edge cases (b3) | **High** — fork/maintain an IME (b6) | Low–Medium — B plus optional tile |
| 11 | Maintenance burden | n/a | Low — stable public APIs (b11) | Medium — OEM overlay churn (b3) | High — keyboard is a product, not a feature (b6) | Low–Medium |
| 12 | Future extensibility | n/a | G — plugin API (mode B) unaffected; overlay can be added later (b11) | F — boxed in by its own permission (b3) | G — full keyboard control (b4) | **E** — B's ceiling plus accelerators |
| 13 | Offline functionality | n/a | E — fully offline, as today (b5) | E | E — offline-first keyboards exist (b9) | E |
| 14 | Dependence on proprietary software | **X — 100% dependent on an unextendable closed app** (b1) | E — none (b5) | E — none (b3) | E — none (open-source keyboards) (b4) | E — none |

**Basis notes**

- **b1** — Samsung Keyboard: no public source, no extension API (§2, opensource.samsung.com; developer.samsung.com). **[DOCUMENTED]**
- **b2** — PROCESS_TEXT mechanics + limitations (§3.2; Android Developers blog; CommonsWare). **[DOCUMENTED]**
- **b3** — Overlay mechanics: special access, no underlying-text read, Android 11+ restrictions, malware-vector reputation, battery managers (§3.3). **[DOCUMENTED]**
- **b4** — Inline typing requires *being* the IME; user must switch away from Samsung Keyboard (§3.4 candidate facts). **[DOCUMENTED]**
- **b5** — Companion = ordinary exported Activities + existing facade; zero-permission claim verified against the shipped APK's manifest pattern (§1.2). **[VERIFIED]**
- **b6** — ComposeView-in-IME fragility and OEM variance documented in §1.3; keyboard forks are product-sized maintenance (OpenBoard→HeliBoard lineage). **[DOCUMENTED]**
- **b7** — Per-app PROCESS_TEXT variance: Google Keep case (SO 72910365), Messenger replacement failure report (SO 54027838), WebView read-only. **[DOCUMENTED]**
- **b8** — One UI renders the standard selection toolbar with third-party entries (Google Translate works); exact placement (inline vs. overflow) varies — **[ASSUMED until verified on the maintainer's device]**.
- **b9** — HeliBoard requests contacts-read/vibration/boot; FlorisBoard notifications+vibration (F-Droid metadata). **[DOCUMENTED]**
- **b10** — Threat-surface comparison: keyboard sees all keystrokes in all fields vs. companion sees only explicitly selected/shared text (docs/THREAT_MODEL.md logic applied to both architectures). **[VERIFIED — analysis]**
- **b11** — Existing `MessageCipher` facade already implements encrypt/decrypt/unlock against Room+Keystore+Argon2; UI surface required is two Activities (§14). **[VERIFIED]**

---

## 5. Recommended Architecture

**Option E — Hybrid Companion Architecture**, with Option B as its core
and strictly optional accelerators:

1. **Core (must ship):** companion app; PROCESS_TEXT "Encrypt"/"Decrypt"
   activities; sharesheet decrypt target; clipboard fallbacks; existing
   management UI (contacts, keys, settings) unchanged.
2. **Accelerator (phase 2):** Quick Settings tile for clipboard-decrypt.
3. **Deferred (evidence-gated):** floating overlay — only if device-tested
   friction data justifies it.
4. **Decoupled (not deleted):** the `:keyboard` IME module leaves the
   release critical path; code remains in-repo. Whether it is excluded from
   future APKs or kept behind a flag is a spec-003 decision.
5. **Preserved:** the entire crypto core, data layer, security layer, and
   plugin API (§14).

---

## 6. Reasons for Rejecting the Alternatives

- **A (Samsung Keyboard integration):** impossible — no source, no API,
  no plugin system (§2). Everything "integrable" about Samsung Keyboard is
  already in Option E via OS mechanisms.
- **B (pure standalone) — rejected *as a label*, not as a substance:** the
  companion core of E *is* B. B as a closed scope loses the cheap wins
  (tile) and leaves no planned answer to friction; E simply names B's core
  plus gated extras. If the maintainer prefers minimalism, shipping E's
  core alone is a valid v0.2.
- **C (floating assistant):** costs a scary permission, cannot read the
  text it exists to process, fights battery managers, and still needs B's
  mechanisms underneath (§3.3). Not an architecture — a UI garnish.
- **D (alternative keyboard):** violates the "keep Samsung Keyboard"
  requirement by definition; re-imports IME fragility; GPL-3.0-only
  candidates (HeliBoard/OpenBoard) conflict with the Apache-2.0 codebase
  unless kept as a separate app; and FlorisBoard, while
  license-compatible, is still in beta with no word suggestions (§3.4).
  Remains the documented fallback if requirements ever change.
- **"Just fix the current IME":** the crash cause is unconfirmed (§1.5);
  even a confirmed-and-fixed IME keeps the product on the least testable,
  most OEM-sensitive surface, while not delivering what the user actually
  asked for (Samsung Keyboard retained).

---

## 7. Proposed User Workflows

Both workflows are written for the recommended architecture. Fields:
actions, APIs, app-support dependency, failure/fallback, manual copy,
security implications. **No universal compatibility is promised** — the
per-app variance in §3.2 is real and must be device-tested (§11, §15).

### 7.1 Outgoing message (encrypt)

**Primary — selection toolbar (editable field):**
1. User types the message with **Samsung Keyboard** (unchanged).
2. User long-presses / double-taps to select the draft text.
3. Selection toolbar → **"Encrypt (CipherKey)"**.
   *APIs: `ACTION_PROCESS_TEXT` intent filter on an exported Activity
   (label = toolbar entry); `EXTRA_PROCESS_TEXT` delivers the selection.
   App-support dependency: the field must be a standard editable
   (EditText/TextView) that shows the system selection toolbar, and the
   app must not filter third-party PROCESS_TEXT entries (documented
   exceptions exist).*
4. CipherKey's lightweight activity appears (contact remembered from last
   use; passphrase prompt only if the session is locked).
   *APIs: `MessageCipher.encryptDraft()` (existing).*
5. User confirms → activity finishes with the CK1 envelope as the result.
   *APIs: `setResult(RESULT_OK, intent.putExtra(EXTRA_PROCESS_TEXT,
   envelope))`. On standard fields the selection is **replaced in place**;
   the user then sends the message normally.*

**Failure/fallback ladder:** if the entry does not appear (app suppresses
the toolbar) or the replacement is ignored by the app → user copies the
draft (normal Copy), pulls the CipherKey tile or opens the app, taps
"Encrypt clipboard", pastes the envelope back. *APIs: ClipboardManager read
on an explicit action in the foreground (Android 10+ compliant).* If even
that is undesirable, the user can compose inside CipherKey and copy out.
**Manual copy required:** only on the fallback paths. **Security:** the
plaintext draft transits one explicit, user-initiated app switch (same
exposure class as any "Translate" action); the envelope returns via the
same channel; nothing is persisted; nothing leaves the device.

### 7.2 Incoming message (decrypt)

**Primary — selection or share on the received ciphertext:**
1. User long-presses the received `CK1:...` message → selects it.
2. Selection toolbar → **"Decrypt (CipherKey)"**
   (arrives with `EXTRA_PROCESS_TEXT_READONLY=true` in read-only views) —
   **or** uses the chat's Share → **"Decrypt with CipherKey"**.
   *APIs: PROCESS_TEXT (read-only variant) and/or `ACTION_SEND text/plain`
   target. App-support dependency: the messenger must make message text
   selectable or shareable — nearly universal, but style varies per app.*
3. CipherKey shows an **explicit decryption preview** (contact, plaintext);
   passphrase prompt if the session is locked.
   *APIs: `MessageCipher.decryptEnvelope()` (existing).*
4. User reviews the plaintext → **Copy** (or, in an editable field via the
   encrypt-style result path, direct insert where the target field
   supports it).

**Fallback ladder:** copy the ciphertext → tile/app → "Decrypt clipboard"
→ preview → copy → paste where needed. *Android 13+ clipboard auto-clear
makes long-lived clipboard reliance unwise — the tile flow is deliberately
instant.* **Manual copy required:** at the final step in most read-only
contexts (insertion into another app's field is not generally possible
without the PROCESS_TEXT result path). **Security:** decryption is always
explicit and previewed before anything is inserted/copied; the clipboard
holds ciphertext in, plaintext out only after an explicit user action in
the foreground (Android 12+ shows the system "pasted from clipboard" toast
— transparency, by design); preview plaintext is never written to disk.

---

## 8. Security and Privacy Analysis

- **Permission inventory (core): zero Android system permissions** — same
  as v0.1.0 (verified against the shipped manifest, §1.2). The optional
  tile adds `BIND_QUICK_SETTINGS_TILE`, a system-bound service permission
  with no user-facing prompt. The deferred overlay would add
  `SYSTEM_ALERT_WINDOW` special access — a reason it is deferred.
- **Attack-surface comparison with the IME architecture:** an IME can
  observe every keystroke of every field (including passwords — the reason
  `SensitiveFieldPolicy` existed). The companion sees **only text the user
  explicitly selects or shares to it**. This is a strict reduction in
  trusted-surface. **[VERIFIED — analysis]**
- **Untrusted input handling:** PROCESS_TEXT/share/clipboard input is
  untrusted by definition. The existing CK1 codec is strict (length caps:
  16 KiB plaintext / 256 KiB envelope / 360k chars; strict unpadded
  base64url; malformed input rejected) and covered by the 140 passing
  tests. The new activities must additionally guard non-CK1 input
  gracefully (show "not a CipherKey message" — never crash). **[VERIFIED
  for the codec; activity-level hardening is a spec-003 task]**
- **Clipboard hygiene:** reads happen only on explicit user action in the
  foreground (Android 10+ compliant); Android 12's read-toast is
  transparency, not leakage; Android 13+ auto-clear bounds clipboard
  residence. No clipboard *listeners* — same chokepoint design as the
  existing `ClipboardAccess` security contract.
- **Keystore & at-rest security:** unchanged — `AndroidKeystoreKeyProtector`
  wraps session keys exactly as today; keys in memory only, wiped on
  lock/timeout (`KeySessionManager`).
- **No accessibility service, no network, no analytics** — Constitution
  principles preserved.
- **Residual risks:** (a) user confusion could lead to pasting plaintext
  into the wrong chat — mitigated by explicit preview + contact name shown
  before copy; (b) PROCESS_TEXT entries are visible system-wide (any app
  can declare them) — user-level spoofing consideration, mitigated by the
  app label + icon shown in the toolbar; (c) plaintext in the preview
  could be screenshotted — same as any messenger; documented, not solved.

---

## 9. Required Android APIs and Permissions

| Mechanism | API / declaration | Min API | Permission cost |
| --- | --- | --- | --- |
| Selection-toolbar entry | `<intent-filter>` `ACTION_PROCESS_TEXT` + `DEFAULT` + `text/plain` on exported Activities (`android:label` = toolbar text; must not set `exported=false`) | 23 (device floor 26 for CipherKey) | none |
| Replacement return | `setResult` with `EXTRA_PROCESS_TEXT` | 23 | none |
| Read-only detection | `EXTRA_PROCESS_TEXT_READONLY` | 23 | none |
| Share target | `ACTION_SEND` / `text/plain` | 1 | none |
| Clipboard read/write | `ClipboardManager` (foreground only on 10+) | 1 | none |
| Quick Settings tile (optional) | `TileService` + `BIND_QUICK_SETTINGS_TILE` | 24 | system-bound only |
| Overlay (deferred) | `SYSTEM_ALERT_WINDOW` special access | 23 (TYPE_APPLICATION_OVERLAY on 26+) | user grant in Settings |
| Existing core | Android Keystore, Room, DataStore | 26 | none |

Sources: Android Developers (Intent reference, Quick Settings tiles guide,
Android 11 privacy changes, secure clipboard handling). **[DOCUMENTED]**

---

## 10. Prototype Plan

Principle: validate the riskiest assumption first — **that the
selection-toolbar mechanism works acceptably on the maintainer's actual
Samsung device with the actual messengers** — before building anything
beyond it. Emulator results are recorded separately from real-device
results and never substitute for them.

**P0 — Diagnostics (no code; closes §1's open question)**
Run the §1.6 procedure; capture logcat; record device/One UI/Android
versions. *Acceptance: a named crash signature (or a confirmed
install-time cause) is attached to the failure.*

**P1 — Encrypt spike (the riskiest integration)**
One new exported Activity handling `ACTION_PROCESS_TEXT` (editable
variant), wired to the existing `MessageCipher.encryptDraft`, returning
the envelope as the result. Hardened against arbitrary input.
*Acceptance criteria (all must pass on the real Samsung device; emulator
runs recorded separately and labeled):*
1. App installs and launches without crashing (and Samsung Keyboard
   remains selected and functional throughout — verified by typing in a
   notes app before and after each step).
2. "Encrypt (CipherKey)" appears in the selection toolbar of a standard
   editable field.
3. Selected Persian/Unicode text encrypts to a `CK1:` envelope that
   **round-trips byte-exact through decrypt** (UTF-8 preserved — verified
   by the existing JVM codec tests plus on-device round-trip).
4. The envelope replaces the selection in the target field.
5. Fuzz: sharing/processing random junk text into the activity never
   crashes it (graceful notice instead).
6. No plaintext leaves the device (no network permission exists —
   verified in the merged manifest).

**P2 — Decrypt + fallbacks (completes the loop)**
Decrypt PROCESS_TEXT (read-only) entry, ACTION_SEND target, clipboard
fallback, sticky-contact UX. *Acceptance: incoming workflow from §7.2
completes on the real device in at least two different messenger apps
(maintainer picks the two that matter most); fallback ladder works when
the toolbar entry is unavailable.*

**P3 — Accelerators (evidence-gated)**
Quick Settings tile; overlay only with measured friction data.
*Acceptance: tile tap → decrypt-preview in ≤ 2 interactions; battery
  impact negligible over a day of typical use.*

---

## 11. Implementation Roadmap

| Phase | Content | Depends on |
| --- | --- | --- |
| 0. Diagnostics | §1.6 log-capture; device facts recorded | maintainer device |
| 1. Spec 003 + P1 spike | implementation spec; encrypt activity; hardening; device test matrix | this report approved |
| 2. P2 loop-closure | decrypt entry, share target, clipboard fallback, UX polish, Persian strings (RTL) | P1 green on device |
| 3. v0.2.0-alpha | companion-only APK (keyboard decoupled from the critical path per §5); honest notes | P2 green |
| 4. Accelerators | tile; (overlay if evidence) | v0.2 feedback |
| 5. Crypto roadmap (unchanged) | mode B public-key plugin, old-era decryption | independent track |

---

## 12. Estimated Complexity and Major Risks

**Complexity (Option E core): LOW–MODERATE.** The entire cryptographic and
persistence stack already exists and is tested (140 tests). The new surface
is two small Activities + intent filters + one optional TileService —
measured in days, not weeks. This contrasts with Option D (forking and
maintaining a keyboard: a permanent product-sized commitment) or repairing
the IME blind (unknown, unbounded — cannot even reproduce locally).

**Major risks and mitigations:**

| Risk | Severity | Mitigation |
| --- | --- | --- |
| PROCESS_TEXT entries missing or ignored in key messengers | High (product) | P1 device test *first*; fallback ladder (share/clipboard) always present; never claim universal support |
| Friction of explicit app-switch vs. inline IME encryption | Medium (adoption) | tile accelerator; sticky contact; auto-detect envelope on open; overlay remains an evidence-gated option |
| Crash cause never confirmed (logcat unavailable) | Medium (knowledge) | P0 procedure is cheap; architecture decision is robust to the answer (companion works regardless) |
| Clipboard auto-clear (Android 13+) breaking slow user flows | Low | instant tile flow; document; no reliance on long-lived clipboard |
| OEM variance in toolbar rendering (One UI overflow placement) | Low–Medium | device test; label entries clearly; both entry styles (toolbar + share) shipped |
| Dormant `:keyboard` module rotting in-tree | Low | decide in spec 003: exclude from APK or keep compiling in CI; code is not deleted either way |

---

## 13. Reusable Existing CipherKey Modules

Fifteen Gradle modules are declared; every one has a defined fate. **No
module is deleted by this decision.**

| Module | Fate under Option E | Rationale |
| --- | --- | --- |
| `:core:crypto` | **keep unchanged** | Pure-JVM AEAD/Argon2/protocol header; RFC 9106 vectors pinned; zero Android coupling |
| `:core:messages` | **keep unchanged** | CK1/CKS1 codecs with strict parsing and golden vectors |
| `:core:sessions` | **keep unchanged** | In-memory key lifetime, timeout, wipe semantics |
| `:core:keys` | **keep unchanged** | `KeyProtector` port + JVM fixed implementation |
| `:core:common` | **keep unchanged** | IDs, dispatcher provider |
| `:data:database` | **keep unchanged** | Room schema v1 (contacts, key eras, wrapped keys) |
| `:data:repository` | **keep unchanged** | `MessageCipher` facade — the exact API the new activities call; plus contact/session repositories |
| `:data:preferences` | **keep unchanged** | DataStore settings with policy clamps |
| `:security:storage` | **keep unchanged** | Android Keystore wrapping |
| `:security:clipboard` | **keep unchanged — and newly central** | `ClipboardAccess` chokepoint reused by companion fallback flows |
| `:security:policies` | **keep unchanged** | KeyPolicy / Argon2Profile enforcement |
| `:plugins:api`, `:plugins:registry` | **keep unchanged** | Future mode B (public-key) plugin surface |
| `:app` | **extend** | Add PROCESS_TEXT activities, share target, tile; management UI unchanged |
| `:keyboard` | **decouple from the release critical path (not deleted)** | The fragile, untestable-in-this-environment layer; code preserved in-repo for a possible future inline-typing option |

The architecture decision therefore preserves **100% of the verified
cryptographic work** and re-homes it behind a UI surface that is testable,
permission-free, and independent of Samsung Keyboard's internals.

---

## 14. Outstanding Questions and Unresolved Uncertainties

1. **Device facts** — exact Samsung model, One UI version, Android version
   of the failing phone (drives H2/H3 and toolbar rendering specifics).
2. **Crash signature** — no logcat exists; §1.6 procedure closes this.
   Which action failed: opening the app, or enabling/showing the keyboard?
3. **Priority messengers** — which two to five apps must the workflows
   pass on the real device (PROCESS_TEXT/share variance is per-app)?
4. **One UI toolbar placement** — whether third-party PROCESS_TEXT entries
   appear inline or under an overflow on the maintainer's firmware.
5. **GitHub access** — the PAT was lost with the sandbox recycle; branch
   `002-architecture-reassessment` is local until re-authorized (no push
   has been attempted; `main` is untouched).
6. **CI status** — the last CI run could not be re-checked for the same
   reason (token loss); last verified state: two post-merge CI-fix
   commits on `main` (`e8af87f`, `05db8a5`).
7. **Overlay appetite** — is a floating shortcut wanted at all, given its
   permission cost? (Currently deferred.)
8. **`:keyboard` endgame** — exclude from future APKs, or keep shipping
   behind a flag? (spec-003 decision; not prejudged here.)
9. **Persian UI language** — should the companion UI itself be bilingual
   (fa/en, RTL), given the maintainer's locale?

---

## Final Recommendation

**Adopt Option E — the Hybrid Companion Architecture: keep Samsung
Keyboard as the default input method; deliver CipherKey as a zero-permission
companion app whose entry points are the platform's text-selection toolbar
(ACTION_PROCESS_TEXT), the Sharesheet, and explicit clipboard actions,
with an optional Quick Settings tile as an accelerator and the floating
overlay deferred until device-tested evidence demands it. Decouple — do
not delete — the custom keyboard module from the release critical path,
and reuse the entire verified cryptographic core unchanged.**

The deciding facts: Samsung Keyboard cannot be extended by any legal
means (Option A blocked); replacing the keyboard contradicts the user's
primary requirement (Option D); overlays cannot read the text they would
process and cost a high-trust permission (Option C); and the custom-IME
path is the one component that just demonstrably failed on a real device
while being the least testable surface available. Option E keeps every
verified asset, removes the fragile dependency, preserves the user's
keyboard, and can be validated with a days-sized prototype on the
maintainer's own device before any release.

*Prepared under `specs/002-architecture-reassessment/`. No implementation
was started. Awaiting maintainer review.*
