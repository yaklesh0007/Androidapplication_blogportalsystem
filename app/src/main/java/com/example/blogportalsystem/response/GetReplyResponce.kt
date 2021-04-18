package com.example.blogportalsystem.response


import com.example.blogportalsystem.model.ReplyWithUser

data class GetReplyResponce(
    val success:Boolean?=null,
    val message:String?=null,
    val data:MutableList<ReplyWithUser>?=null,
)
