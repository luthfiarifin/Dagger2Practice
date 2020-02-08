package com.laam.dagger2practice.di

//import com.laam.dagger2practice.di.auth.AuthViewModelModule
import com.laam.dagger2practice.di.auth.AuthViewModelModule
import com.laam.dagger2practice.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity
}