package com.example.blogportalsystem.response

import org.w3c.dom.Comment

data class AddCommentResponce(
    val success:Boolean?=null,
    val message:String?=null,
    val data:Comment?=null
)
