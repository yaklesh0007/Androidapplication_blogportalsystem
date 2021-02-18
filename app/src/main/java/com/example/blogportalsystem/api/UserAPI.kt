package com.example.blogportalsystem.api

import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface UserAPI {
    //Register User
    @POST("user/insert")
    suspend fun registerUser(
        @Body user: User
    ):Response<LoginResponse>

    @POST("user/login")
    suspend fun checkUser(
        @Field("username") username : String,
        @Field("password") password : String
    ): Response<LoginResponse>
}