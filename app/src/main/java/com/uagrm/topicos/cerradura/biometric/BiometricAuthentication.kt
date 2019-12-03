package com.uagrm.topicos.cerradura.biometric

import androidx.biometric.BiometricPrompt

class BiometricAuthentication(private val listener: OnLockListener) :
    BiometricPrompt.AuthenticationCallback() {

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)
        listener.onChange(false)
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        listener.onChange(true)
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        listener.onChange(false)
    }
}