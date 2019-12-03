package com.uagrm.topicos.cerradura.biometric

import androidx.biometric.BiometricPrompt

class BiometricAuthentication : BiometricPrompt.AuthenticationCallback() {

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
    }
}