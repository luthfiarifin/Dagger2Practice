package com.laam.dagger2practice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.network.auth.AuthAPI
import javax.inject.Inject

class AuthViewModel @Inject constructor(authAPI: AuthAPI?) : ViewModel() {
    private val TAG = "AuthViewModel"

    init {
        Log.d(TAG, "Work Gannnnn!!")
        authAPI?.let {
            Log.d(TAG, "Auth Api nggak null gan")
        } ?: Log.d(TAG, "Auth Api null gan")
    }
}