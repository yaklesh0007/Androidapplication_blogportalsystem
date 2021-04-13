package com.example.blogportalsystem.api

import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.response.AddblogResponce
import com.example.blogportalsystem.response.GetBlogResponse
import com.example.blogportalsystem.response.getmyblogResponce
import retrofit2.Response
import retrofit2.http.*

interface BlogAPI {

    @POST("android/blogs/insert")
    suspend fun addblog(
        @Header("Authorization") token : String,
        @Body blog : Post
    ): Response<AddblogResponce>

    @GET("post/all")
    suspend fun getblog(
        @Header("Authorization")token:String
    ):Response<GetBlogResponse>

    @DELETE("post/delete/{id}/{userID}")
    suspend fun deletepost(
        @Header("Authorization")token: String,
        @Header(value = "id")id:String,
        @Header(value = "userID")userID:String
    ): Response<getmyblogResponce>

    @GET("showmypost")
    suspend fun getmypost(
        @Header("Authorization")token: String
    ):Response<getmyblogResponce>
}