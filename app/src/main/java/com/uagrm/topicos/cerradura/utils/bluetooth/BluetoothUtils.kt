package com.uagrm.topicos.cerradura.utils.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import java.io.IOException
import java.util.*

class BluetoothUtils {

    private var socket: BluetoothSocket? = null
    private var device: BluetoothDevice? = null
    private var adapter: BluetoothAdapter? = null

    var MAC = "98:D3:34:90:6F:A1"
    var BASE_UUID =
        UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    init {
        adapter = BluetoothAdapter.getDefaultAdapter()
    }

    fun devicesSearch() {
        val devices = adapter!!.bondedDevices
        if (devices.size > 0) {
            for (element in devices) {
                if (element.name == "Plotter") device = element
            }
        }
    }

    fun existDevice(): Boolean {
        return device == null
    }

    @Throws(IOException::class)
    fun connect(): Boolean {
        if (device != null && (socket == null || !socket!!.isConnected)) {
            socket = device!!.createInsecureRfcommSocketToServiceRecord(BASE_UUID)
            socket!!.connect()
            return true
        }
        return false
    }

    @Throws(IOException::class)
    fun sendSignal(value: String): Boolean {
        if (socket != null) {
            socket!!.outputStream.write(value.toByteArray())
            return true
        }
        return false
    }
}