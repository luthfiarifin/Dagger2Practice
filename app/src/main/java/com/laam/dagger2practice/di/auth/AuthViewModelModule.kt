package com.laam.dagger2practice.di.auth

import androidx.lifecycle.ViewModel
import com.laam.dagger2practice.di.ViewModelKey
import com.laam.dagger2practice.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}