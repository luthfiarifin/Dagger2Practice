package com.laam.dagger2practice.di.main

import com.laam.dagger2practice.ui.main.post.PostFragment
import com.laam.dagger2practice.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment
}