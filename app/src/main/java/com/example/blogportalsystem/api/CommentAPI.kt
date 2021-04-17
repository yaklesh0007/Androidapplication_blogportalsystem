package com.example.blogportalsystem.api

import com.example.blogportalsystem.model.Comment

import com.example.blogportalsystem.response.AddCommentResponce
import com.example.blogportalsystem.response.GetCommentResponce
import retrofit2.Response
import retrofit2.http.*

interface CommentAPI {
    @POST("comment/insert")
    suspend fun addcomment(
        @Header("Authorization") token : String,
        @Body comment : Comment
    ): Response<AddCommentResponce>

    @PUT("comment/update/{id}")
    suspend fun updatecomment(
        @Header("Authorization") token : String,
        @Part("id")id:String,
        @Body comment : Comment
    ):Response<AddCommentResponce>

    @GET("comment/single/{id}")
    suspend fun getcommentsingle(
        @Header("Authorization") token:String,
        @Part("id")id:String,
    ):Response<AddCommentResponce>

    @DELETE("comment/delete/{id}/{userID}")
    suspend fun deleteComment(
        @Header("Authorization")token:String,
        @Part("id")id:String,
        @Part("userID")userID:String
    ):Response<AddCommentResponce>

    @GET("comment/{id}")
    suspend fun getComments(
        @Header("Authorization")token:String,
        @Part("id")id:String
    ):Response<GetCommentResponce>
}