package com.laam.dagger2practice

import com.laam.dagger2practice.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}