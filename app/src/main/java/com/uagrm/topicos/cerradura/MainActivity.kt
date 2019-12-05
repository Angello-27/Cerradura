package com.uagrm.topicos.cerradura

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uagrm.topicos.cerradura.ui.main.MainFragment
import com.uagrm.topicos.cerradura.utils.biometric.BiometricUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        checkBiometric()
    }

    private fun checkBiometric() {
        if (!BiometricUtils.checkStatus(this)) {
            val makeText = Toast.makeText(
                this,
                R.string.message_not_support_biometric,
                Toast.LENGTH_LONG
            )
            makeText.show()
        }
    }

}
