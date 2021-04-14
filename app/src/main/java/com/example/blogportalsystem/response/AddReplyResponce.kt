package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.Reply


data class AddReplyResponce(
    val success:Boolean?=null,
    val message:String?=null,
    val data: Reply?=null
)
