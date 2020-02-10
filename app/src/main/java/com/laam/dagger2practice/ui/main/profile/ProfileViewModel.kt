package com.laam.dagger2practice.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    private val TAG = "ProfileViewModel"

    init {
        Log.d(TAG, "onStart: view model is ready")
    }
}