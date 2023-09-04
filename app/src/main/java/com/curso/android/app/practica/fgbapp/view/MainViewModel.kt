package com.curso.android.app.practica.fgbapp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.android.app.practica.fgbapp.model.StringComparison
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel: ViewModel() {
    private var _stringComparison = MutableLiveData<StringComparison>()
    val stringComparison: LiveData<StringComparison> get() = _stringComparison

    private var _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?> get() = _errorMsg

    fun compareStrings(str1: String, str2: String) {
        try {
            if (str1.isNotEmpty() && str2.isNotEmpty()) {
                val comparisonResult = str1 == str2
                updateStringComparison(str1, str2, comparisonResult)
                updateErrorMsg(null)
            } else {
                throw Exception("Complete ambos campos.")
            }
        } catch (e: Exception) {
            updateErrorMsg("Error: ${e.message}")
            Log.e(TAG, "compareStrings catch; e -> $e")
        }
    }

    private fun updateStringComparison(str1: String, str2: String, comparisonResult: Boolean) {
        viewModelScope.launch {
            _stringComparison.value = StringComparison(str1, str2, comparisonResult)
        }
    }

    private fun updateErrorMsg(msg: String?) {
        viewModelScope.launch {
            _errorMsg.value = msg
        }
    }

}
