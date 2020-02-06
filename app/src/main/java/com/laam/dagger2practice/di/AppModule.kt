package com.laam.dagger2practice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.laam.dagger2practice.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideRequestOption(): RequestOptions = RequestOptions
        .placeholderOf(R.drawable.white_background)
        .error(R.drawable.white_background)

    @Singleton
    @JvmStatic
    @Provides
    fun provideRequestManager(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager = Glide
        .with(application)
        .setDefaultRequestOptions(requestOptions)

    @Singleton
    @JvmStatic
    @Provides
    fun provideAppDrawable(application: Application): Drawable =
        ContextCompat.getDrawable(application, R.drawable.codingwithmitch)!!
}