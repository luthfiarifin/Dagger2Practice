package com.laam.dagger2practice.di.auth

import com.laam.dagger2practice.network.auth.AuthAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthModule {

    @JvmStatic
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthAPI = retrofit
        .create(AuthAPI::class.java)
}