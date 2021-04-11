package com.melq.morse

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ViewModelMain {
    private val _isEncryptMode: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().also { mutableLiveData ->
            mutableLiveData.value = true
        }
    val isEncryptMode: LiveData<Boolean> get() = _isEncryptMode

    private val _outputText: MutableLiveData<String> =
        MutableLiveData<String>().also { mutableLiveData ->
            mutableLiveData.value = ""
        }
    val outputText: LiveData<String> get() = _outputText

    fun setOutPutText(text: String) {
        _outputText.value = text
    }
}