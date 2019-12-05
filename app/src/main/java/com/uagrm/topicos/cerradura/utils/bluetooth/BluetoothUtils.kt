package com.uagrm.topicos.cerradura.utils.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice

object BluetoothUtils {

    fun searchKnownDevice(): BluetoothDevice? {
        val adapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        val devices: Set<BluetoothDevice> = adapter!!.bondedDevices
        if (devices.isNotEmpty()) {
            for (element in devices) {
                if (element.name == "Plotter") return element
            }
        }
        return null
    }

}