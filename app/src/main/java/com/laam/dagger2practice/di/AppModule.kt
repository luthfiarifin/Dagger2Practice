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

@Module
object AppModule {

    @JvmStatic
    @Provides
    fun provideRequestOption(): RequestOptions = RequestOptions
        .placeholderOf(R.drawable.white_background)
        .error(R.drawable.white_background)

    @JvmStatic
    @Provides
    fun provideRequestManager(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager = Glide
        .with(application)
        .setDefaultRequestOptions(requestOptions)

    @JvmStatic
    @Provides
    fun provideAppDrawable(application: Application): Drawable =
        ContextCompat.getDrawable(application, R.drawable.codingwithmitch)!!
}