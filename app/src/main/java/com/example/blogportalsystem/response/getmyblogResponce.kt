package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.PostWithUser

data class getmyblogResponce(
    val success : Boolean?=null,
    val data : ArrayList<Post>? = null,

    )