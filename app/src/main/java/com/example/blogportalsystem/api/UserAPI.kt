package com.example.blogportalsystem.api

import com.example.blogportalsystem.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    //Register User
    @POST("user/insert")
    suspend fun registerUser(
        @Body user: User
    )
}