package com.laam.dagger2practice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.network.auth.AuthAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(authApi: AuthAPI) : ViewModel() {
    private val TAG = "AuthViewModel"

    private var compositeDisposable = CompositeDisposable()

    init {

        Log.d(TAG, "Work Gannnnn!!")

        compositeDisposable.add(
            authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> Log.d(TAG, "onNext: ${value.email}") },
                    { error -> Log.e(TAG, "onError: ${error.printStackTrace()}") },
                    { Log.d(TAG, "onComplete: complete") }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}