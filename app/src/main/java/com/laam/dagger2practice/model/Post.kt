package com.laam.dagger2practice.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("userId")
    @Expose
    val userId: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("body")
    @Expose
    val body: String
)
