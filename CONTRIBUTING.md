# Contributing

Thanks for considering a contribution.

## Ground rules

1. **Security beats features.** A PR that adds functionality by weakening a
   guarantee in `docs/THREAT_MODEL.md` will be rejected.
2. **Keep the module boundaries.** In particular:
   - `keyboard/` must not contain cryptographic code — it talks to
     `MessageCipher` and nothing else.
   - `core/*` modules must stay Android-free (pure Kotlin, JVM-testable).
   - No cyclic module dependencies; direction is documented in
     `docs/ARCHITECTURE.md`.
3. **Wire format changes require a version bump.** Any change to
   `ProtocolHeader`, `Ck1`, `InviteCodec` or the AAD construction must keep
   the golden-vector test passing or bump the CK1 version with migration
   notes in `docs/PROTOCOL.md`.
4. **Tests are not optional** for anything under `core/`, `data/` or
   `security/`. New crypto behaviour needs a negative test (tampering,
   wrong key, wrong passphrase) alongside the happy path.

## Workflow

```bash
./gradlew test assembleDebug   # everything must pass
```

- Kotlin, 4-space indent, KDoc on public security-relevant APIs.
- No new Android permissions, no network dependencies, no analytics.
- Keep the zero-logging rule for sensitive material.

## Reporting security issues

See `SECURITY.md` — do not use public issues for vulnerabilities.
