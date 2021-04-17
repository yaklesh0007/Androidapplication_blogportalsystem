package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.CommentWithUser
import com.example.blogportalsystem.model.PostWithUser

data class GetCommentResponce(
    val result:List<CommentWithUser>?=null,
    val success:Boolean?=null,
    val message:String?=null
)

