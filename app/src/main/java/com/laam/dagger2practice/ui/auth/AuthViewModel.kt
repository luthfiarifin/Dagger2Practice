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

    private var mediatorLiveData = MediatorLiveData<User>()

    init {

        Log.d(TAG, "Work Gannnnn!!")
    }

    fun authenticateWithId(userId: Int) {
        val source: LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        )

        mediatorLiveData.addSource(
            source
        ) { user ->
            mediatorLiveData.value = user
            mediatorLiveData.removeSource(source)
        }
    }

    fun observeUser(): LiveData<User> = mediatorLiveData
}