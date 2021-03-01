package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.User

data class RegisterResponse(
    val success:Boolean?=null,
    val message:String?=null,
    val data:User?=null,
    val errors:List<ErrorResponse>? = null
)