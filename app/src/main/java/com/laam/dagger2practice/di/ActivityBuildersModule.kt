package com.laam.dagger2practice.di

import com.laam.dagger2practice.di.auth.AuthModule
import com.laam.dagger2practice.di.auth.AuthViewModelModule
import com.laam.dagger2practice.di.main.MainFragmentBuildersModule
import com.laam.dagger2practice.ui.auth.AuthActivity
import com.laam.dagger2practice.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class, AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}