package com.laam.dagger2practice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.laam.dagger2practice.R
import com.laam.dagger2practice.model.User
import com.laam.dagger2practice.ui.auth.AuthResource
import com.laam.dagger2practice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    private val TAG = "ProfileFragment"

    private lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: profile view model create")

        profileViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory)[ProfileViewModel::class.java]

        subscribeObservers()
    }

    private fun subscribeObservers() {
        profileViewModel.getAuthenticateUser().removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthenticateUser().observe(viewLifecycleOwner, Observer {
            it?.let { authResource ->
                when (authResource.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetail(authResource.data)
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        setErrorDetails(authResource.message)
                    }
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        message?.let {
            email.text = it
            username.text = "Error"
            website.text = "Error"
        }
    }

    private fun setUserDetail(data: User?) {
        data?.let {user ->
            email.text = user.email
            username.text = user.username
            website.text = user.website
        }
    }
}