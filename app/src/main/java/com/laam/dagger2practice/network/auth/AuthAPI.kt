package com.laam.dagger2practice.network.auth

import com.laam.dagger2practice.model.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthAPI {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Flowable<User>
}