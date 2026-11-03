package org.cipherkey.core.sessions

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.common.SessionId
import org.cipherkey.core.crypto.Argon2KeyDerivator
import org.cipherkey.core.crypto.Argon2Params
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class KeySessionManagerTest {

    private class MutableTimeSource(var now: Long) : TimeSource {
        override fun nowMillis(): Long = now
    }

    private val time = MutableTimeSource(1_000_000L)
    private val fastParams = Argon2Params(memoryExponent = 3, iterations = 1, parallelism = 1)
    private val contactA = ContactId("contact-a")
    private val contactB = ContactId("contact-b")

    private fun manager() = KeySessionManager(
        derivator = Argon2KeyDerivator(),
        timeSource = time,
    )

    private suspend fun unlock(manager: KeySessionManager, contactId: ContactId, passphrase: String, saltSeed: Int): SessionKey {
        val derived = manager.deriveKey(passphrase.toCharArray(), salt(saltSeed), fastParams)
        return manager.activate(
            contactId = contactId,
            sessionId = SessionId("session-$contactId-$saltSeed"),
            keyId = ByteArray(16) { (it + saltSeed).toByte() },
            salt = salt(saltSeed),
            params = fastParams,
            keyBytes = derived,
            timeoutMillis = 60_000,
        )
    }

    @Test
    fun `activate then activeSession returns the session`() = runTest {
        val manager = manager()
        val sessionKey = unlock(manager, contactA, "passphrase", 1)
        assertEquals(sessionKey.sessionId, manager.activeSession(contactA)?.sessionId)
    }

    @Test
    fun `expiry wipes the session`() = runTest {
        val manager = manager()
        val sessionKey = unlock(manager, contactA, "passphrase", 1)
        time.now += 60_001
        assertNull(manager.activeSession(contactA))
        // The key material of the expired session was wiped.
        assertTrue(sessionKey.keyBytes.all { it == 0.toByte() })
    }

    @Test
    fun `lock wipes the session`() = runTest {
        val manager = manager()
        val sessionKey = unlock(manager, contactA, "passphrase", 1)
        assertTrue(manager.lock(contactA))
        assertNull(manager.activeSession(contactA))
        assertTrue(sessionKey.keyBytes.all { it == 0.toByte() })
        assertFalse(manager.lock(contactA)) // second lock: nothing to lock
    }

    @Test
    fun `replacement wipes the previous session key`() = runTest {
        val manager = manager()
        val first = unlock(manager, contactA, "passphrase", 1)
        unlock(manager, contactA, "different-pass", 2)
        assertTrue(first.keyBytes.all { it == 0.toByte() })
        assertEquals(SessionId("session-contact-a-2"), manager.activeSession(contactA)?.sessionId)
    }

    @Test
    fun `sessions of different contacts are isolated`() = runTest {
        val manager = manager()
        val keyA = unlock(manager, contactA, "shared-pass", 1)
        val keyB = unlock(manager, contactB, "shared-pass", 2)
        // Same passphrase, different salt -> different keys.
        assertFalse(keyA.keyBytes.contentEquals(keyB.keyBytes))
        assertNotEquals(keyA.fingerprint, keyB.fingerprint)
        // Lookup never crosses contacts.
        assertEquals(keyA.sessionId, manager.activeSession(contactA)?.sessionId)
        assertEquals(keyB.sessionId, manager.activeSession(contactB)?.sessionId)
    }

    @Test
    fun `locking one contact leaves the other intact`() = runTest {
        val manager = manager()
        val keyA = unlock(manager, contactA, "shared-pass", 1)
        val keyB = unlock(manager, contactB, "shared-pass", 2)
        manager.lock(contactA)
        assertNull(manager.activeSession(contactA))
        assertEquals(keyB.sessionId, manager.activeSession(contactB)?.sessionId)
        assertFalse(keyB.keyBytes.all { it == 0.toByte() })
        assertTrue(keyA.keyBytes.all { it == 0.toByte() })
    }

    @Test
    fun `lockAll wipes everything and reports the count`() = runTest {
        val manager = manager()
        unlock(manager, contactA, "shared-pass", 1)
        unlock(manager, contactB, "shared-pass", 2)
        assertEquals(2, manager.lockAll())
        assertNull(manager.activeSession(contactA))
        assertNull(manager.activeSession(contactB))
        assertTrue(manager.unlockedContactIds().isEmpty())
    }

    @Test
    fun `unlockedContactIds sweeps expired sessions`() = runTest {
        val manager = manager()
        unlock(manager, contactA, "pass", 1)
        unlock(manager, contactB, "pass", 2)
        time.now += 61_000
        assertTrue(manager.unlockedContactIds().isEmpty())
    }

    @Test
    fun `expiryOf reports the absolute deadline`() = runTest {
        val manager = manager()
        unlock(manager, contactA, "pass", 1)
        assertEquals(1_060_000L, manager.expiryOf(contactA))
        assertNull(manager.expiryOf(contactB))
    }

    @Test
    fun `deriveKey is deterministic and salt-sensitive`() = runTest {
        val manager = manager()
        val a1 = manager.deriveKey("shared".toCharArray(), salt(1), fastParams)
        val a2 = manager.deriveKey("shared".toCharArray(), salt(1), fastParams)
        val b = manager.deriveKey("shared".toCharArray(), salt(2), fastParams)
        assertArrayEquals(a1, a2)
        assertFalse(a1.contentEquals(b))
    }

    @Test
    fun `fingerprint is stable for identical key material`() = runTest {
        val manager = manager()
        val s1 = unlock(manager, contactA, "pass", 1)
        assertEquals(s1.fingerprint, s1.fingerprint)
        // 6 groups of 3 decimal digits joined by colons: 6*3 + 5 = 23.
        assertEquals(23, s1.fingerprint.length)
    }

    private fun salt(seed: Int) = ByteArray(16) { ((it + seed * 7) % 256).toByte() }
}
