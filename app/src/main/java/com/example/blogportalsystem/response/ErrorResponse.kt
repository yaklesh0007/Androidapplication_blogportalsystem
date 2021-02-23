package com.example.blogportalsystem.response

data class ErrorResponse(
    val msg:String?=null,
    val param:String?=null,
    val location:String?=null,
    val value:String?=null
)