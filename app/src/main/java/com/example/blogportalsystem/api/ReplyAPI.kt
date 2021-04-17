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
        @Path("id")id:String,
        @Body reply: Reply
    ): Response<AddReplyResponce>

    @GET("reply/single/{id}")
    suspend fun getreplysingle(
        @Header("Authorization") token:String,
        @Path("id")id:String,
    ): Response<AddReplyResponce>

    @DELETE("reply/delete/{id}/{userID}")
    suspend fun deleteReply(
        @Header("Authorization")token:String,
        @Path("id")id:String,
        @Path("userID")userID:String
    ):Response<AddReplyResponce>

    @GET("reply/{commentID}")
    suspend fun getReplys(
        @Header("Authorization")token:String,
        @Path("commentID")commentID:String
    ): Response<GetReplyResponce>
}