# Samsung Galaxy A54 5G — device test procedure (v0.2.1-alpha)

Do this after installing `CipherKey-v0.2.1-alpha-release.apk`
(**install over** v0.2.0 — same signing key, no uninstall needed).

Prepare once: open CipherKey → Contacts tab → **+ Add contact** →
name: anything → leave the invite field EMPTY → Create (a few seconds
while the key is derived) → open the contact → **Copy invite** → send
it to your contact over any channel; agree on a passphrase (min 8 chars)
**in person or by voice**, never in the same chat. Enter the passphrase
once to unlock. (For a quick self-test you can simply keep the default
contact unlocked on your own device.)

## A. Clipboard ladder (the reported bug — must pass)

1. Open Samsung Notes (or any app with a text field), type a sentence,
   long-press it → **Copy**.
2. Open CipherKey → tap **Encrypt clipboard**.
   - v0.2.0 bug: "The clipboard is empty" — must NOT happen anymore.
   - Expected: the sheet shows your sentence as the draft → pick the
     contact → **Encrypt** → you get the `CK1:…` envelope → **Copy**.
3. Paste the `CK1:…` envelope into a note, then copy it again.
4. CipherKey → **Decrypt clipboard** → plaintext preview appears →
   matches the original sentence.

Also try: copy an emoji/متن فارسی/multiline text — same result.
Copy a photo (e.g. in Gallery → Share → Copy) → "Encrypt clipboard"
must say the clipboard contains non-text content, not crash.

## B. Selection toolbar (where to look on One UI)

One UI puts third-party text actions in the toolbar's **overflow menu**:

1. Open Samsung Notes or Chrome → an **editable** text field.
2. Type a sentence → double-tap (or long-press) it to select.
3. In the floating toolbar tap the **⋮ three-dot overflow** (sometimes
   a **›** arrow). Look for **Encrypt (CipherKey)** there — not in the
   main row, which One UI reserves for its own actions.
4. Tap it → the CipherKey sheet opens with the selection as the draft →
   Encrypt → **Replace in field** → the sentence in the field is
   replaced by `CK1:…`.
5. To decrypt: select the whole `CK1:…` message → ⋮ →
   **Decrypt (CipherKey)** → preview → **Copy**. In read-only views
   (e.g. a received message) the entry still works; only "Insert" is
   hidden by design.

Note: some apps (WhatsApp/Telegram message bubbles) draw their own
selection menu and never show PROCESS_TEXT entries — that is an app
decision, not a CipherKey defect; use the sharesheet or the clipboard
ladder there.

## C. Sharesheet (second rung)

Long-press a received `CK1:…` message → **Share** →
**Decrypt with CipherKey** → preview.

## Report back

For each of A/B/C: worked / failed (with the exact notice text), the
app you tried it in, and your One UI version (Settings → About phone →
Software information). If B shows nothing even in the ⋮ overflow in
Samsung Notes AND Chrome, tell us — that is the one outcome that would
re-open the manifest investigation for One UI specifically.
