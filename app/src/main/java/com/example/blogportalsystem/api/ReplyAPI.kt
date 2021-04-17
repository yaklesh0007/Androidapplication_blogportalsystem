package com.example.blogportalsystem.api
import com.example.blogportalsystem.model.Reply
import com.example.blogportalsystem.response.AddCommentResponce
import com.example.blogportalsystem.response.AddReplyResponce

import com.example.blogportalsystem.response.GetReplyResponce
import retrofit2.Response
import retrofit2.http.*

interface ReplyAPI {
    @POST("reply/insert")
    suspend fun addReply(
        @Header("Authorization") token : String,
        @Body reply : Reply
    ): Response<AddReplyResponce>

    @PUT("reply/update/{id}")
    suspend fun updateReply(
        @Header("Authorization") token : String,
        @Part("id")id:String,
        @Body reply: Reply
    ): Response<AddCommentResponce>

    @GET("reply/single/{id}")
    suspend fun getreplysingle(
        @Header("Authorization") token:String,
        @Part("id")id:String,
    ): Response<AddCommentResponce>

    @DELETE("reply/delete/{id}/{userID}")
    suspend fun deleteReply(
        @Header("Authorization")token:String,
        @Part("id")id:String,
        @Part("userID")userID:String
    )

    @GET("reply/{commentID}")
    suspend fun getReplys(
        @Header("Authorization")token:String,
        @Part("commentID")commentID:String
    ): Response<GetReplyResponce>
}