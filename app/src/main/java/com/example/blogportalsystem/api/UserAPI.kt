package com.example.blogportalsystem.api

import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.response.FetchUserResponse
import com.example.blogportalsystem.response.LoginResponse
import com.example.blogportalsystem.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {
    //Register User
    @POST("user/insert")
    suspend fun registerUser(
        @Body user: User
    ):Response<RegisterResponse>
    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("email") email : String,
        @Field("password") password : String
    ): Response<LoginResponse>

    @GET("user/profile")
    suspend fun showprofile(
        @Header("Authorization") token : String
    ):Response<FetchUserResponse>

    @Multipart
    @PUT("user/profile/update")
    suspend fun updateprofilepic(
        @Header("Authorization") token : String,
        @Part file: MultipartBody.Part
    ):Response<FetchUserResponse>

}