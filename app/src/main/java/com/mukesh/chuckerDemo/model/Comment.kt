package com.mukesh.chuckerDemo.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Comment(
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("postId")
    val postId: Int? = null
)