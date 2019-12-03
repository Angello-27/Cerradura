package com.uagrm.topicos.cerradura.biometric

import androidx.biometric.BiometricManager

object BiometricUtils {

    fun checkStatus(manager: BiometricManager): Boolean {
        when (manager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> return true
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE,
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE,
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> return false
        }
        return false
    }
}