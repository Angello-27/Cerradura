package com.uagrm.topicos.cerradura.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>().apply { value = false }

    val observableStatus: LiveData<Boolean>
        get() = status

    fun change(boolean: Boolean) {
        status.value = boolean
    }
}
