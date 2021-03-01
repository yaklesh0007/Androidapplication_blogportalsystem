package com.example.blogportalsystem.api

import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.response.AddblogResponce
import com.example.blogportalsystem.response.GetBlogResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BlogAPI {

    @POST("blog/insert")
    suspend fun addblog(
        @Header("Authorization") token : String,
        @Body blog : Post
    ): Response<AddblogResponce>

    @GET("post/all")
    suspend fun getblog(
        @Header("Authorization")token:String
    ):Response<GetBlogResponse>
}