package com.uagrm.topicos.cerradura.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import java.util.concurrent.Executors

object BiometricUtils {

    fun checkStatus(context: Context): Boolean {
        val manager = BiometricManager.from(context)
        return when (manager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE,
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE,
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> false
            else -> false
        }
    }

    fun launchFingerprint(fragment: Fragment): BiometricPrompt {
        val executor = Executors.newSingleThreadExecutor()
        val authenticator = BiometricAuthentication()
        return BiometricPrompt(fragment, executor, authenticator)
    }
}