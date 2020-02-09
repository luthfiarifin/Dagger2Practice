package com.laam.dagger2practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.laam.dagger2practice.ui.auth.AuthActivity
import com.laam.dagger2practice.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    private val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObsevers()
    }

    private fun subscribeObsevers() {
        sessionManager.getAuthUser().observe(this, Observer {
            it?.let { authResource ->
                when (authResource.status) {
                    AuthResource.AuthStatus.LOADING -> {

                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "subscribeObsevers: Login Success ${authResource.data?.email}")
                    }
                    AuthResource.AuthStatus.ERROR -> {

                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }
                }
            }
        })
    }

    private fun navLoginScreen() {
        Intent(this@BaseActivity, AuthActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}
