package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.PostWithUser

data class getmyblogResponce(
    val success : Boolean?=true,
    val count : Int? = 0,
    val data : MutableList<PostWithUser>? = null,
    val message:String?=null
)