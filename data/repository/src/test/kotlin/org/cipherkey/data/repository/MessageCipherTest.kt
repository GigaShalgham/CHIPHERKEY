package org.cipherkey.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.cipherkey.core.crypto.CryptoError
import org.cipherkey.core.messages.Ck1
import org.cipherkey.core.messages.InviteCodec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * End-to-end tests of the composition facade. "Two devices" are two
 * independent [TestDevice]s (separate databases, separate key protectors —
 * emulating device-bound Keystores — and separate session managers), exactly
 * like two phones sharing a passphrase and a CKS1 invite.
 */
class MessageCipherTest {

    private val passphrase = "correct horse battery staple"

    /** Sender creates a contact; receiver imports the invite. Both unlock. */
    private suspend fun pairedDevices(): Pair<TestDevice, TestDevice> {
        val sender = TestDevice()
        val receiver = TestDevice()
        val (contact, era) = sender.newContact("Bob")
        val invite = InviteCodec.decode(era.inviteCode)
        val alice = receiver.contacts.createContact("Alice")
        receiver.sessionKeys.importEra(alice.id, invite)
        assertTrue(sender.cipher.unlockContact(contact.id, passphrase.toCharArray()) is UnlockOutcome.Unlocked)
        assertTrue(receiver.cipher.unlockContact(alice.id, passphrase.toCharArray()) is UnlockOutcome.Unlocked)
        return sender to receiver
    }

    @Test
    fun `two devices round trip a message`() = runTest {
        val (sender, receiver) = pairedDevices()
        val contactId = sender.theSenderContact()

        val outcome = sender.cipher.encryptDraft(contactId, "meet me at the usual place")
        assertTrue(outcome is EncryptOutcome.Encrypted)
        val envelope = (outcome as EncryptOutcome.Encrypted).envelope
        assertTrue(envelope.startsWith("CK1:"))

        val decrypted = receiver.cipher.decryptEnvelope(envelope)
        assertTrue(decrypted is DecryptOutcome.Decrypted)
        val result = decrypted as DecryptOutcome.Decrypted
        assertEquals("meet me at the usual place", result.plaintext)
        assertEquals("Alice", result.contactName)
    }

    @Test
    fun `wrong passphrase is rejected on the second unlock`() = runTest {
        val device = TestDevice()
        val (contact, _) = device.newContact("Bob")
        // First unlock stores the Keystore-wrapped verifier.
        assertTrue(device.cipher.unlockContact(contact.id, passphrase.toCharArray()) is UnlockOutcome.Unlocked)
        device.cipher.lockContact(contact.id)
        // Second unlock with the wrong passphrase must fail verification.
        val outcome = device.cipher.unlockContact(contact.id, "wrong passphrase here".toCharArray())
        assertTrue(outcome is UnlockOutcome.WrongPassphrase)
        assertNull(device.cipher.activeSessionFingerprint(contact.id))
    }

    @Test
    fun `wrong passphrase on a fresh device is caught by authentication`() = runTest {
        val sender = TestDevice()
        val receiver = TestDevice()
        val (contact, era) = sender.newContact("Bob")
        val invite = InviteCodec.decode(era.inviteCode)
        val alice = receiver.contacts.createContact("Alice")
        receiver.sessionKeys.importEra(alice.id, invite)

        senderUnlock(sender, contact.id)
        val envelope =
            (sender.cipher.encryptDraft(contact.id, "top secret") as EncryptOutcome.Encrypted).envelope

        // Fresh device has no verifier yet: the wrong passphrase "unlocks"…
        assertTrue(
            receiver.cipher.unlockContact(alice.id, "wrong passphrase here".toCharArray())
                is UnlockOutcome.Unlocked,
        )
        // …but the AEAD authentication rejects the message.
        val outcome = receiver.cipher.decryptEnvelope(envelope)
        assertTrue(outcome is DecryptOutcome.Failed)
        assertEquals(CryptoError.AuthenticationFailed, (outcome as DecryptOutcome.Failed).error)
    }

    private suspend fun senderUnlock(device: TestDevice, contactId: org.cipherkey.core.common.ContactId) {
        device.cipher.unlockContact(contactId, passphrase.toCharArray())
    }

    @Test
    fun `tampered ciphertext is rejected`() = runTest {
        val (sender, receiver) = pairedDevices()
        val contactId = sender.theSenderContact()
        val envelope = (sender.cipher.encryptDraft(contactId, "integrity check") as EncryptOutcome.Encrypted).envelope

        val tampered = flipEnvelopeByte(envelope, 60) // inside the ciphertext region
        val outcome = receiver.cipher.decryptEnvelope(tampered)
        assertTrue(outcome is DecryptOutcome.Failed)
        assertEquals(CryptoError.AuthenticationFailed, (outcome as DecryptOutcome.Failed).error)
    }

    @Test
    fun `tampered header is rejected through aad binding`() = runTest {
        val (sender, receiver) = pairedDevices()
        val contactId = sender.theSenderContact()
        val envelope = (sender.cipher.encryptDraft(contactId, "aad check") as EncryptOutcome.Encrypted).envelope

        // Byte 30 sits inside the salt field (part of the authenticated header).
        val tampered = flipEnvelopeByte(envelope, 30)
        val outcome = receiver.cipher.decryptEnvelope(tampered)
        assertTrue(outcome is DecryptOutcome.Failed)
        assertEquals(CryptoError.AuthenticationFailed, (outcome as DecryptOutcome.Failed).error)
    }

    @Test
    fun `unknown key id is reported`() = runTest {
        val sender = TestDevice()
        val receiver = TestDevice()
        val (contact, _) = sender.newContact("Bob")
        senderUnlock(sender, contact.id)
        val envelope = (sender.cipher.encryptDraft(contact.id, "hello?") as EncryptOutcome.Encrypted).envelope
        // Receiver never imported the invite -> key unknown.
        val outcome = receiver.cipher.decryptEnvelope(envelope)
        assertTrue(outcome is DecryptOutcome.UnknownKey)
    }

    @Test
    fun `locked contact yields needs unlock with contact details`() = runTest {
        val (sender, receiver) = pairedDevices()
        val contactId = sender.theSenderContact()
        val envelope = (sender.cipher.encryptDraft(contactId, "secret message") as EncryptOutcome.Encrypted).envelope

        receiver.cipher.lockAll()
        val outcome = receiver.cipher.decryptEnvelope(envelope)
        assertTrue(outcome is DecryptOutcome.NeedsUnlock)
        val needs = outcome as DecryptOutcome.NeedsUnlock
        assertEquals("Alice", needs.contactName)
        assertNotNull(needs.contactId)
    }

    @Test
    fun `stale key era is reported`() = runTest {
        val (sender, receiver) = pairedDevices()
        val contactId = sender.theSenderContact()
        val envelope = (sender.cipher.encryptDraft(contactId, "old era message") as EncryptOutcome.Encrypted).envelope

        // Rotate: create a newer era on the RECEIVER side with a strictly
        // later timestamp so it becomes the latest era there.
        val aliceId = receiver.theSenderContact()
        receiver.time.now += 10_000
        receiver.sessionKeys.createEra(aliceId, TestDevice.FAST_PARAMS)

        val outcome = receiver.cipher.decryptEnvelope(envelope)
        assertTrue(outcome is DecryptOutcome.StaleKeyEra)
    }

    @Test
    fun `encrypt without unlock asks for the passphrase`() = runTest {
        val device = TestDevice()
        val (contact, _) = device.newContact("Bob")
        val outcome = device.cipher.encryptDraft(contact.id, "hello")
        assertTrue(outcome is EncryptOutcome.NeedsUnlock)
    }

    @Test
    fun `encrypt of empty draft is rejected`() = runTest {
        val device = TestDevice()
        val (contact, _) = device.newContact("Bob")
        device.cipher.unlockContact(contact.id, passphrase.toCharArray())
        val outcome = device.cipher.encryptDraft(contact.id, "")
        assertTrue(outcome is EncryptOutcome.EmptyDraft)
    }

    @Test
    fun `oversized plaintext is rejected`() = runTest {
        val device = TestDevice()
        val (contact, _) = device.newContact("Bob")
        device.cipher.unlockContact(contact.id, passphrase.toCharArray())
        val outcome = device.cipher.encryptDraft(contact.id, "x".repeat(16 * 1024 + 1))
        assertTrue(outcome is EncryptOutcome.Failed)
        assertEquals(CryptoError.InputTooLarge, (outcome as EncryptOutcome.Failed).error)
    }

    @Test
    fun `non envelope text is rejected early`() = runTest {
        val (_, receiver) = pairedDevices()
        assertTrue(receiver.cipher.decryptEnvelope("just some text") is DecryptOutcome.NotAnEnvelope)
        assertTrue(receiver.cipher.decryptEnvelope("") is DecryptOutcome.NotAnEnvelope)
    }

    @Test
    fun `persian and unicode content round trips`() = runTest {
        val (sender, receiver) = pairedDevices()
        val contactId = sender.theSenderContact()
        val persian = "سلام دنیا! این یک پیام محرمانه است. 🌙 تعداد: ۱۲۳۴۵\n" +
            "Second line with émojis 🚀 and «quotes» — and RTL marks."
        val envelope = (sender.cipher.encryptDraft(contactId, persian) as EncryptOutcome.Encrypted).envelope
        val outcome = receiver.cipher.decryptEnvelope(envelope)
        assertTrue(outcome is DecryptOutcome.Decrypted)
        assertEquals(persian, (outcome as DecryptOutcome.Decrypted).plaintext)
    }

    @Test
    fun `session expiry forces a fresh unlock`() = runTest {
        val device = TestDevice()
        val (contact, _) = device.newContact("Bob")
        device.cipher.unlockContact(contact.id, passphrase.toCharArray())
        assertTrue(device.cipher.isUnlocked(contact.id))

        device.time.now += 300_001 // past the injected 5-minute timeout
        assertTrue(!device.cipher.isUnlocked(contact.id))
        val outcome = device.cipher.encryptDraft(contact.id, "hello")
        assertTrue(outcome is EncryptOutcome.NeedsUnlock)
    }

    @Test
    fun `contacts are cryptographically isolated`() = runTest {
        val device = TestDevice()
        val (bob, _) = device.newContact("Bob")
        device.time.now += 10
        val (carol, _) = device.newContact("Carol")
        // Same passphrase on both contacts...
        device.cipher.unlockContact(bob.id, passphrase.toCharArray())
        device.cipher.unlockContact(carol.id, passphrase.toCharArray())
        // ...but the fingerprints (hence the keys) differ.
        val bobPrint = device.cipher.activeSessionFingerprint(bob.id)
        val carolPrint = device.cipher.activeSessionFingerprint(carol.id)
        assertNotNull(bobPrint)
        assertNotNull(carolPrint)
        assertTrue(bobPrint != carolPrint)
        // Locking Carol leaves Bob alone.
        device.cipher.lockContact(carol.id)
        assertTrue(device.cipher.isUnlocked(bob.id))
        assertTrue(!device.cipher.isUnlocked(carol.id))
    }

    @Test
    fun `two contacts cannot read each other's envelopes`() = runTest {
        val sender = TestDevice()
        val receiver = TestDevice()
        val (bob, bobEra) = sender.newContact("Bob")
        val (carol, carolEra) = sender.newContact("Carol")
        // Receiver imports ONLY Carol's invite.
        val carolContact = receiver.contacts.createContact("Carol")
        receiver.sessionKeys.importEra(carolContact.id, InviteCodec.decode(carolEra.inviteCode))

        senderUnlock(sender, bob.id)
        senderUnlock(sender, carol.id)
        receiver.cipher.unlockContact(carolContact.id, passphrase.toCharArray())

        val bobEnvelope = (sender.cipher.encryptDraft(bob.id, "for bob only") as EncryptOutcome.Encrypted).envelope
        // Carol's device-side lookup cannot resolve Bob's keyId.
        val outcome = receiver.cipher.decryptEnvelope(bobEnvelope)
        assertTrue(outcome is DecryptOutcome.UnknownKey)
        // And the unused era proves the invites are distinct.
        assertTrue(!bobEra.keyId.contentEquals(carolEra.keyId))
    }

    @Test
    fun `lockAll clears every session`() = runTest {
        val device = TestDevice()
        val (bob, _) = device.newContact("Bob")
        device.time.now += 10
        val (carol, _) = device.newContact("Carol")
        device.cipher.unlockContact(bob.id, passphrase.toCharArray())
        device.cipher.unlockContact(carol.id, passphrase.toCharArray())
        assertEquals(2, device.cipher.lockAll())
        assertTrue(!device.cipher.isUnlocked(bob.id))
        assertTrue(!device.cipher.isUnlocked(carol.id))
    }

    @Test
    fun `envelope survives leading and trailing whitespace`() = runTest {
        val (sender, receiver) = pairedDevices()
        val contactId = sender.theSenderContact()
        val envelope = (sender.cipher.encryptDraft(contactId, "trimmed") as EncryptOutcome.Encrypted).envelope
        // Messaging apps frequently add newlines around copied text.
        val outcome = receiver.cipher.decryptEnvelope("  $envelope\n")
        assertTrue(outcome is DecryptOutcome.Decrypted)
    }

    /** The (single) contact of a paired sender device. */
    private suspend fun TestDevice.theSenderContact(): org.cipherkey.core.common.ContactId =
        contacts.observeContacts().first().single().id

    /** Flips one bit of the decoded payload at [byteIndex], re-encodes. */
    private fun flipEnvelopeByte(envelope: String, byteIndex: Int): String {
        val body = envelope.substring(Ck1.MAGIC.length)
        val payload = java.util.Base64.getUrlDecoder().decode(body)
        payload[byteIndex] = (payload[byteIndex].toInt() xor 0x01).toByte()
        return Ck1.MAGIC + Ck1.encodeBase64Url(payload)
    }
}
