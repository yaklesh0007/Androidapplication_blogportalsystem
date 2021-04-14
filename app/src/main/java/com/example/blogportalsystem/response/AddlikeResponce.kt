package com.example.blogportalsystem.response

import com.example.blogportalsystem.model.Like
import com.example.blogportalsystem.model.LikeWithUser

data class AddlikeResponce(
    val data:LikeWithUser?=null,
    val success:Boolean?=null,
    val message:String?=null
) {
}