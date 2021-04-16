package com.example.blogportalwear.APIFORWEAR

import com.example.blogportalwear.ResponseForWear.FetchUserResponse
import com.example.blogportalwear.ResponseForWear.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("email") email : String,
        @Field("password") password : String
    ): Response<LoginResponse>

    @GET("user/profile")
    suspend fun showprofile(
        @Header("Authorization") token : String
    ): Response<FetchUserResponse>
}