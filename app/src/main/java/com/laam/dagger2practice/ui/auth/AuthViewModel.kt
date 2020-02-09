package com.laam.dagger2practice.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.SessionManager
import com.laam.dagger2practice.model.User
import com.laam.dagger2practice.network.auth.AuthAPI
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(val authApi: AuthAPI, val sessionManager: SessionManager) :
    ViewModel() {
    private val TAG = "AuthViewModel"

    init {

        Log.d(TAG, "Work Gannnnn!!")
    }

    fun authenticateWithId(userId: Int) {
        Log.d(TAG, "authenticateWithId: attempting to login")
        sessionManager.authenticateWithID(queryUserId(userId))
    }

    fun queryUserId(userId: Int): LiveData<AuthResource<User>> =
        LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn {
                    return@onErrorReturn User(-1, "", "", "", "")
                }
                .map { user ->
                    if (user.id == -1) {
                        return@map AuthResource.error("Could not authenticate", user)
                    }
                    return@map AuthResource.authenticated(user)
                }
                .subscribeOn(Schedulers.io())
        )

    fun observeAuthState(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()
}