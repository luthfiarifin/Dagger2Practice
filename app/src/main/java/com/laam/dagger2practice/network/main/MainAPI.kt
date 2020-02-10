package com.laam.dagger2practice.network.main

import com.laam.dagger2practice.model.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {

    @GET("posts")
    fun getPostsFromUser(
        @Query("userId") id: Int
    ): Flowable<List<Post>>
}