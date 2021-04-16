package com.example.blogportalwear.ResponseForWear

import com.example.blogportalwear.ModalForWear.User

data class FetchUserResponse(
    val message:String?=null,
    val data: User?=null,
    val success:Boolean?=null
)
