package com.laam.dagger2practice.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @JvmStatic
    @Provides
    fun someString(): String = "this is some string"

    @JvmStatic
    @Provides
    fun getApp(application: Application?): Boolean = application == null
}