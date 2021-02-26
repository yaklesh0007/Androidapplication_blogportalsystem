package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.User

data class FetchUserResponse(
    val message:String?=null,
    val data:User?=null,
    val success:Boolean?=null
)
