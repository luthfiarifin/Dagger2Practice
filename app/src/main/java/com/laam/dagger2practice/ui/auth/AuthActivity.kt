package com.laam.dagger2practice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
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

        setLogo()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }
}