package org.cipherkey.app.security

import androidx.biometric.BiometricManager
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * v0.4.1: pins the honest biometric gate mapping. The BiometricManager
 * constants are inlined at compile time, so these tests run without any
 * framework — and pin the raw values too, so a library bump that renumbers
 * them cannot silently change the UX.
 */
class BiometricDiagnosticsTest {

    @Test
    fun `every documented canAuthenticate result maps to its gate`() {
        assertEquals(BiometricGate.AVAILABLE, BiometricDiagnostics.gateFor(BiometricManager.BIOMETRIC_SUCCESS))
        assertEquals(BiometricGate.NONE_ENROLLED, BiometricDiagnostics.gateFor(BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED))
        assertEquals(BiometricGate.NO_HARDWARE, BiometricDiagnostics.gateFor(BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE))
        assertEquals(BiometricGate.HW_UNAVAILABLE, BiometricDiagnostics.gateFor(BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE))
        assertEquals(
            BiometricGate.SECURITY_UPDATE,
            BiometricDiagnostics.gateFor(BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED),
        )
    }

    @Test
    fun `undocumented codes fall back to NOT_STRONG`() {
        assertEquals(BiometricGate.NOT_STRONG, BiometricDiagnostics.gateFor(-1))
        assertEquals(BiometricGate.NOT_STRONG, BiometricDiagnostics.gateFor(7))
        assertEquals(BiometricGate.NOT_STRONG, BiometricDiagnostics.gateFor(Int.MAX_VALUE))
    }

    @Test
    fun `the raw constant values are the framework ones`() {
        // If androidx renumbers these, the mapping above silently changes
        // meaning — pin the values the framework has shipped since 1.0.
        assertEquals(0, BiometricManager.BIOMETRIC_SUCCESS)
        assertEquals(1, BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED)
        assertEquals(2, BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE)
        assertEquals(3, BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE)
        assertEquals(4, BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED)
    }
}
