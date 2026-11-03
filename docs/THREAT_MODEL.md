# CipherKey Threat Model (v0.1)

## Assets

| Asset | Where | Protection |
|---|---|---|
| Shared passphrases | User memory only | Never stored, never logged |
| Derived session keys (AES-256) | Process memory | Wiped on lock/expiry; never persisted raw |
| Keystore-wrapped verifier | Room DB | AES-GCM under a non-exportable AndroidKeyStore key |
| Plaintext drafts / decrypted previews | Process memory; input fields of third-party apps | Best-effort; see "Residual risks" |
| Contact names, key metadata (keyId, salt, params) | Room DB (local) | Not secret by design; exposed in envelopes anyway |

## Adversaries

1. **Network observer** (carrier, ISP, messaging-platform operator):
   sees `CK1:` ciphertexts. Learns message *length*, *timing*, *participants
   of the underlying transport* — but not content. CipherKey does **not**
   hide metadata; it encrypts content only.
2. **Device thief (locked phone)**: FBE + lockscreen protect everything;
   CipherKey adds no attack surface (no credentials stored beyond the
   Keystore-wrapped blob, which is useless without the Keystore key).
3. **Device thief (unlocked phone)**: can read the Room DB (contact names,
   salts — public), cannot decrypt past or future messages without the
   passphrase. **However** an unlocked phone with CipherKey as the active
   keyboard lets the attacker type/decrypt in place if a session is active;
   sessions auto-lock (default 5 minutes) and lock on demand. This is the
   same trust level as any unlocked-phone scenario.
4. **Malicious app on the device**: cannot read CipherKey's data (sandbox);
   cannot bind to the IME (system-only permission). A malicious *keyboard*
   the user installs alongside could read everything typed — inherent to
   Android's IME model; the app's onboarding tells users to keep exactly one
   active keyboard.
5. **Offline attacker with a copied DB**: has salts and parameters (public)
   and the Keystore-wrapped blob (device-bound, no offline oracle). Brute
   force requires running Argon2id per guess (64 MiB / 3 passes default) —
   the KDF is the entire defence here, hence the tunable profiles.
6. **Malicious messaging platform modifying ciphertexts in transit**:
   AEAD authentication (whole header as AAD) makes any modification detectable
   (decryption fails); there is no plaintext-equivalent oracle.

## Trust boundaries

- **User ↔ CipherKey**: the keyboard is trusted with every keystroke (same
  trust every IME requires). Compensating controls: zero permissions, no
  network code, open source.
- **CipherKey ↔ third-party apps**: via `InputConnection` only. No
  Accessibility service, no screen reading, no background clipboard. The
  clipboard is read exclusively on an explicit tap of the Decrypt button.
- **CipherKey ↔ storage**: raw keys never cross this boundary; only
  Keystore-wrapped blobs and public parameters do.

## Security requirements → implementation

| Requirement | Mechanism | Verified by |
|---|---|---|
| Ciphertext integrity | AES-256-GCM, 128-bit tag, header-as-AAD | `AesGcmAeadCipherTest`, `PassphraseCryptoProviderTest` (bit-flip every sealed byte) |
| Key derivation cost | Argon2id 64 MiB / 3 passes / 1 lane default | RFC 9106 vector test; profile policy tests |
| Wrong passphrase rejection | AEAD auth failure + Keystore verifier after first unlock | `MessageCipherTest` (both paths) |
| Session isolation | per-contact salt, single map keyed by ContactId, wipe on replace | `KeySessionManagerTest`, `MessageCipherTest` |
| Memory hygiene | wipe on lock/expiry/replace; passphrase CharArray zeroed after use | unit tests assert zeroed buffers |
| No sensitive logging | code review; no log statements in crypto/keyboard paths | review (not machine-checked) |
| Password-field protection | `SensitiveFieldPolicy` | `SensitiveFieldPolicyTest`, controller test asserts zero clipboard reads |
| Wire strictness | canonical encoder; decoder rejects padding/standard alphabet/whitespace/unknown IDs pre-crypto | `Ck1CodecTest`, `InviteCodecTest` |
| Cross-impl compatibility | pinned golden vector | `Ck1CodecTest.golden vector` |

## Residual risks (accepted, v0.1)

1. **No forward secrecy.** Mode A is a static shared passphrase; compromise
   of the passphrase compromises all traffic for that key era. Mode B
   (ratcheting public key) is specified but not integrated — docs/PLUGINS.md.
2. **GC residue.** Java/Kotlin cannot guarantee erasure; `Arrays.fill(0)`
   is best-effort and copies made by the runtime may persist until GC.
   Standard limitation of managed runtimes.
3. **Fresh-device sender mistake.** On a device that has not unlocked the
   era before, encrypting with a mistyped passphrase produces ciphertext the
   peer cannot read (detected only at decryption there). The Keystore
   verifier closes this hole from the second unlock on.
4. **Plaintext in third-party fields.** After *Insert*, plaintext is in the
   target app's edit buffer — that app's security posture applies.
5. **Clipboard exposure.** The system clipboard briefly holds ciphertext
   (and any plaintext the user put there). CipherKey never *writes* plaintext
   to the clipboard; the preview panel is in-memory only.
6. **IME trust.** Users must not run a second keyboard simultaneously; any
   IME can log keystrokes. Communicated in onboarding.
7. **On-device attacks (root, hooked framework)**: out of scope for any
   Android app.
8. **Unverified on real hardware.** All automated verification in this
   release is JVM-level; Keystore behaviour, IME field replacement across
   popular apps, and clipboard access under Android 10+ focus rules still
   need an on-device smoke test.
