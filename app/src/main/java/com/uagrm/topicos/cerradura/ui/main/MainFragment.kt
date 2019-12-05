package com.uagrm.topicos.cerradura.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.uagrm.topicos.cerradura.R
import com.uagrm.topicos.cerradura.utils.biometric.BiometricUtils
import com.uagrm.topicos.cerradura.utils.biometric.OnLockListener
import com.uagrm.topicos.cerradura.utils.bluetooth.BluetoothUtils
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), OnLockListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var bluetooth: BluetoothUtils
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
                bluetooth.sendSignal("A")
                image_view_lock.visibility = View.GONE
                image_view_unlock.visibility = View.VISIBLE
            } else {
                bluetooth.sendSignal("C")
                image_view_lock.visibility = View.VISIBLE
                image_view_unlock.visibility = View.GONE
            }
        })
        bluetooth = BluetoothUtils()
        if (bluetooth.existDevice()) this.bluetooth.devicesSearch()
        bluetooth.connect()
    }

    private fun closeFootprint() {
        viewModel.change(false)
        biometric.cancelAuthentication()
    }

    private fun activateFootprint() {
        val promptInfo = context?.let { BiometricUtils.createInfo(it) }
        biometric = BiometricUtils.launchFingerprint(this)
        promptInfo?.let { biometric.authenticate(it) }
    }

    override fun onChange(boolean: Boolean) {
        viewModel.change(boolean)
    }

}
