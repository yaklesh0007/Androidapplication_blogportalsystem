package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.Post

data class UpdateBlogResponce(
    val result:Post?=null,
    val message:String?=null,
    val success:Boolean?=null
)
