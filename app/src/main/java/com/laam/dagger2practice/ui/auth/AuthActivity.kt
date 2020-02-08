package com.laam.dagger2practice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.laam.dagger2practice.R
import com.laam.dagger2practice.viewmodels.ViewModelProviderFactory
//import com.laam.dagger2practice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"

    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProviders.of(this, factory)[AuthViewModel::class.java]

        login_button.setOnClickListener {
            attemptLogin()
        }

        setLogo()

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observeUser().observe(this, Observer {
            it?.let { authResource ->
                when (authResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)

                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d(TAG, "onChange: Login Success ${authResource.data?.name}")
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, authResource.message, Toast.LENGTH_SHORT).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)

                    }
                }
            }
        })
    }

    fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }

        viewModel.authenticateWithId(user_id_input.text.toString().toInt())
    }
}