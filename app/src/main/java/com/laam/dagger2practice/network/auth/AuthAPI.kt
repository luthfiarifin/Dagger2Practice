package com.laam.dagger2practice.network.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthAPI {

    @GET
    fun getFakeStuff():Call<ResponseBody>
}