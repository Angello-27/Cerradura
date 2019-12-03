package com.uagrm.topicos.cerradura.biometric

import android.util.Log
import androidx.biometric.BiometricPrompt

class BiometricAuthentication : BiometricPrompt.AuthenticationCallback() {

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)
        Log.e("todo bien", "a la chucha error")
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        Log.e("todo bien", "se desbloqueo el bicho")
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        Log.i("todo bien", "falla con algo")
    }
}