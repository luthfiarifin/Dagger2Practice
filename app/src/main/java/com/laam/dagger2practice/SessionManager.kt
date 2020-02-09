package com.laam.dagger2practice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.laam.dagger2practice.model.User
import com.laam.dagger2practice.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private val TAG = "SessionManager"

    private var cachedUser =
        MediatorLiveData<AuthResource<User>>()

    fun authenticateWithID(source: LiveData<AuthResource<User>>) {
        cachedUser.let { mediatorAuth ->

            mediatorAuth.value = AuthResource.loading(null)
            mediatorAuth.addSource(source, Observer { authResource ->
                mediatorAuth.value = authResource
                mediatorAuth.removeSource(source)
            })
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User>> = cachedUser

}