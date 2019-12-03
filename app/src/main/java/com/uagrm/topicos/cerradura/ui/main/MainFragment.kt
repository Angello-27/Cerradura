package com.uagrm.topicos.cerradura.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.uagrm.topicos.cerradura.R
import com.uagrm.topicos.cerradura.biometric.BiometricUtils
import com.uagrm.topicos.cerradura.biometric.OnLockListener
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), OnLockListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var biometric: BiometricPrompt
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
        image_view_unlock.setOnClickListener { closeFootprint() }
        viewModel.observableStatus.observe(this, Observer { value ->
            if (value) {
                image_view_lock.visibility = View.GONE
                image_view_unlock.visibility = View.VISIBLE
            } else {
                image_view_lock.visibility = View.VISIBLE
                image_view_unlock.visibility = View.GONE
            }
        })
    }

    private fun closeFootprint() {
        viewModel.change(false)
        biometric.cancelAuthentication()
    }

    private fun activateFootprint() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.label_biometric_unlock))
            .setSubtitle(getString(R.string.lable_fingerprint_unlock))
            //.setNegativeButtonText("cancel")
            .setDeviceCredentialAllowed(true)
            .build()
        biometric = BiometricUtils.launchFingerprint(this)
        biometric.authenticate(promptInfo)
    }

    override fun onChange(boolean: Boolean) {
        viewModel.change(boolean)
    }

}
