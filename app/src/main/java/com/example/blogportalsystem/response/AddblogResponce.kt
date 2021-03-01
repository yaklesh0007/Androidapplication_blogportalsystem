package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.Post

data class AddblogResponce(
    val success:Boolean?=null,
    val data:Post?=null,
    val message:String?=null
)
