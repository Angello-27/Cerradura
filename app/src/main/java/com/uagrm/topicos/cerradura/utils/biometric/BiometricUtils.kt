package com.uagrm.topicos.cerradura.utils.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import com.uagrm.topicos.cerradura.R
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

    fun createInfo(context: Context): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(context.getString(R.string.label_biometric_unlock))
            .setSubtitle(context.getString(R.string.lable_fingerprint_unlock))
            .setDeviceCredentialAllowed(true)
            .build()
    }

    fun launchFingerprint(fragment: Fragment): BiometricPrompt {
        val executor = Executors.newSingleThreadExecutor()
        val authenticator = BiometricAuthentication(listener = fragment as OnLockListener)
        return BiometricPrompt(fragment, executor, authenticator)
    }
}