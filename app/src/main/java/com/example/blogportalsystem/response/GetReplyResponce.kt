package com.example.blogportalsystem.response


import com.example.blogportalsystem.model.ReplyWithUser

data class GetReplyResponce(
    val data:List<ReplyWithUser>?=null,
    val success:Boolean?=null,
    val message:String?=null
)
