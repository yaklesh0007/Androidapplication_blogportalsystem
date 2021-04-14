package com.example.blogportalsystem.api

import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.response.*
import okhttp3.MultipartBody
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
        @Path(value = "id")id:String,
        @Path(value = "userID")userID:String
    ): Response<DeleteblogResponce>

    @GET("showmypost")
    suspend fun getmypost(
        @Header("Authorization")token: String
    ):Response<getmyblogResponce>

    @Multipart
    @POST("blog/{id}/image")
    suspend fun uploadImage(
        @Header("Authorization")token:String,
        @Path("id")id: String,
        @Part file: MultipartBody.Part
    ):Response<AddblogResponce>

    @PUT("post/update/{id}")
    suspend fun blogupdate(
        @Header("Authorization")token:String,
        @Body blog: Post,
        @Path("id")id:String
    ):Response<UpdateBlogResponce>
}