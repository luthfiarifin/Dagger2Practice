package com.laam.dagger2practice.di

import com.laam.dagger2practice.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun someString(): String = "this is some string"

    }
}