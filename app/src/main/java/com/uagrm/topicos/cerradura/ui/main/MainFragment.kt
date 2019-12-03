package com.uagrm.topicos.cerradura.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uagrm.topicos.cerradura.R
import com.uagrm.topicos.cerradura.biometric.BiometricUtils
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        image_view_lock.setOnClickListener { activateFootprint() }
    }

    private fun activateFootprint() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.label_biometric_unlock))
            .setSubtitle(getString(R.string.lable_fingerprint_unlock))
            //.setNegativeButtonText("cancel")
            .setDeviceCredentialAllowed(true)
            .build()
        val biometric = BiometricUtils.launchFingerprint(this)
        biometric.authenticate(promptInfo)
    }

}
