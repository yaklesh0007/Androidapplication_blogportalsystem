package com.example.blogportalsystem.response

data class RegisterResponse(
    val success:Boolean?=null,
    val message:String?=null,
    val errors:List<ErrorResponse>? = null
)