package com.example.blogportalsystem.api

import com.example.blogportalsystem.response.AddlikeResponce
import com.example.blogportalsystem.response.GetBlogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface LikeAPI {
    @GET("like/{postID}")
    suspend fun getblog(
        @Header("Authorization")token:String,
        @Path(value = "postID")postID:String,
    ): Response<AddlikeResponce>
}