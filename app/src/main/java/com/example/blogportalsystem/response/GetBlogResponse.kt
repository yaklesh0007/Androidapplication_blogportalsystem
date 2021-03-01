package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.Post

data class GetBlogResponse(
    val data:List<Post>?=null,
    val success:Boolean?=null,
    val message:String?=null
)