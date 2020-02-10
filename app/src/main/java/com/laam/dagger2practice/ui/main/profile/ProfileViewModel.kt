package com.laam.dagger2practice.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.SessionManager
import com.laam.dagger2practice.model.User
import com.laam.dagger2practice.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val sessionManager: SessionManager) : ViewModel() {
    private val TAG = "ProfileViewModel"

    init {
        Log.d(TAG, "onStart: view model is ready")
    }

    fun getAuthenticateUser(): LiveData<AuthResource<User>> =
        sessionManager.getAuthUser()
}