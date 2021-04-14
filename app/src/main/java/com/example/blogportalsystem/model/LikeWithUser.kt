package com.example.blogportalsystem.model

import androidx.room.Embedded
import androidx.room.PrimaryKey

data class LikeWithUser(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    @Embedded(prefix = "user_")
    val LikeBy:User?=null,
    val PostId:String?=null
)
