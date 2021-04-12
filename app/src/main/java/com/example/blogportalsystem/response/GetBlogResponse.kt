package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.PostWithUser

data class GetBlogResponse(
    val data:List<PostWithUser>?=null,
    val success:Boolean?=null,
    val message:String?=null
)