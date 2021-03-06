package com.laam.dagger2practice.di.main

import com.laam.dagger2practice.network.main.MainAPI
import com.laam.dagger2practice.ui.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {

    @MainScope
    @JvmStatic
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainAPI = retrofit
        .create(MainAPI::class.java)

    @MainScope
    @JvmStatic
    @Provides
    fun provideAdapter(): PostsRecyclerAdapter = PostsRecyclerAdapter()
}