# CipherKey Constitution

## Core Principles

### I. Security Before Features (NON-NEGOTIABLE)
Any feature that would weaken the cryptographic posture of CipherKey is
rejected, no matter how useful it appears. Only established, reviewed
cryptographic libraries and algorithms are used; custom cryptographic
primitives are forbidden. Every encrypted payload must be authenticated
(AEAD with associated data binding the entire protocol header). Nonces are
never reused under the same key; salts are unique and cryptographically
random.

### II. Honesty in Claims (NON-NEGOTIABLE)
Test counts, build results, coverage figures, and feature status are only
reported from machine-readable evidence (JUnit XML, build tool output,
`aapt2`/`apksigner` output). Every capability is labeled as one of:
implemented, partially implemented, interface-only, or not implemented.
The project never claims to be unbreakable, anonymous, untraceable, or
Signal-Protocol-compatible. Limitations (metadata visibility, lack of
forward secrecy in mode A, device-testing gaps) must stay documented, not
marketed away.

### III. Cryptographic Isolation
The `:keyboard` module contains zero cryptographic implementation; it
consumes the `MessageCipher` facade only. The crypto core (`:core:crypto`
and friends) is plain Kotlin with no Android UI dependencies, so a future
desktop implementation can reuse it. Module dependencies form a DAG —
circular dependencies are forbidden.

### IV. Local-First and Least Privilege
No backend, no INTERNET permission, no Analytics, no Accessibility service,
no background clipboard access. The clipboard is read only immediately
after an explicit user action. Keystrokes and plaintext are never logged.
Protected key material lives in the Android Keystore or is wrapped by it.

### V. Test-Before-Claim
Changes to cryptography, protocol formats, or key handling require tests
covering: encrypt/decrypt roundtrip, wrong credentials, tampered
ciphertext, modified associated data, malformed envelopes, Persian/Unicode
payloads, and session isolation. A test that did not run is a test that
does not exist; variant re-runs of the same class are never counted as
new tests.

### VI. Spec-Driven Workflow
Meaningful engineering work follows GitHub Spec Kit: specification before
implementation, acceptance criteria before code, task breakdown before
execution, and status updates after. Specifications are written in
English. Trivial maintenance does not require a spec, but must never be
used to smuggle a meaningful change past the workflow.

## Security Requirements

- Argon2id parameter profiles must meet or exceed the shipped defaults
  (16/64/256 MiB memory tiers) and honor the RFC 9106 recommendation of
  at least 8×parallelism memory.
- Passphrases are never persisted; after unlock they live only in a
  wipeable in-memory session with an idle timeout.
- Superseded key eras are refused with a clear error, never silently
  decrypted with wrong-era keys.
- Plaintext lifetime in memory is minimized; buffers are zeroed best
  effort and the managed-runtime caveat is documented.
- Release artifacts must state exactly what they are (alpha, debug,
  unsigned) and what verification has and has not been performed.

## Development Workflow

- `main` holds verified code: CI (unit tests + debug build) must pass
  before merge.
- Commits carry meaningful messages; history is never force-pushed or
  rewritten on protected branches.
- No secrets (tokens, keystores, passwords) are ever committed; CI
  includes secret scanning.
- Releases are classified alpha / beta / release-candidate / stable, and
  an unverified build is never labeled stable.
- The repository is the source of truth; local-only state that cannot be
  rebuilt from the repo (signing keys, credentials) is stored outside it
  and documented.

## Governance

This constitution supersedes ad-hoc practice in any conflict. Amendments
require a written proposal, a documented migration impact on existing
specifications, and recording in the repository history. The two
NON-NEGOTIABLE principles (I and II) may only be amended by the repository
owner.

**Version**: 1.0.0 | **Ratified**: 2026-11-04 | **Last Amended**: 2026-11-04
