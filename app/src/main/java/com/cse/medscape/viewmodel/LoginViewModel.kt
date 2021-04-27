package com.cse.medscape.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val TAG = "LoginViewModel"

    fun validatePhone(phone: String): Boolean {
        Log.d(TAG, "validatePhone: " + phone)
        return phone.length < 10
    }

}