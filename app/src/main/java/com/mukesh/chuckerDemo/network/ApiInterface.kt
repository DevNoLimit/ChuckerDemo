package com.mukesh.chuckerDemo.network

import com.mukesh.chuckerDemo.model.Comment
import com.mukesh.chuckerDemo.model.Posts
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET(GET_ALL_POSTS)
    suspend fun getAllPosts(): Response<List<Posts>>


    @GET(GET_POST)
    suspend fun getPostById(
        @Path("postId") postId: String
    ): Response<Posts>


    @GET(GET_COMMENTS)
    suspend fun getComments(
        @Header("channelName") channelName: String = "Dev No Limit",
        @HeaderMap headerMap: Map<String, String>,
        @QueryMap queryMap: Map<String, String>
    ): Response<List<Comment>>


    @POST(ADD_POST)
    suspend fun addPost(
        @Body requestBody: RequestBody
    ): Response<Any>


    @FormUrlEncoded
    @POST(ADD_POST)
    suspend fun addPostWithFormData(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Int,
    ): Response<Any>


    @PUT(PUT_POST)
    suspend fun putPostMethod(
        @Path("postId") postId: String,
        @Body requestBody: RequestBody
    ): Response<Any>


    @PATCH(PATCH_POST)
    suspend fun patchPost(
        @Path("postId") postId: String,
        @Body requestBody: RequestBody
    ): Response<Any>


    @DELETE(DELETE_POST)
    suspend fun deletePost(
        @Path("postId") postId: String
    ): Response<Any>

}