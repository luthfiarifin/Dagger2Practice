package com.laam.dagger2practice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {
    private val TAG = "AuthViewModel"

    init {
        Log.d(TAG, "Work Gannnnn!!")
    }
}