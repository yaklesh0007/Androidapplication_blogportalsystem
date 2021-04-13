package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.User

data class UpdateprofileInfo(
    val success:Boolean?=null,
    val Data:User?=null,
    val message:String?=null
)
