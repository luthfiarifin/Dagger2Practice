package com.laam.dagger2practice.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.model.User
import com.laam.dagger2practice.network.auth.AuthAPI
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(val authApi: AuthAPI) : ViewModel() {
    private val TAG = "AuthViewModel"

    private var mediatorLiveData = MediatorLiveData<AuthResource<User>>()

    init {

        Log.d(TAG, "Work Gannnnn!!")
    }

    fun authenticateWithId(userId: Int) {
        mediatorLiveData.value = AuthResource.loading(null)

        val source: LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(
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

        mediatorLiveData.addSource(
            source
        ) {
            mediatorLiveData.value = it
            mediatorLiveData.removeSource(source)
        }
    }

    fun observeUser(): LiveData<AuthResource<User>> = mediatorLiveData
}