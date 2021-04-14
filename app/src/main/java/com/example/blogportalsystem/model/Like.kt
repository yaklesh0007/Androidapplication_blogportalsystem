package com.example.blogportalsystem.model

import androidx.room.PrimaryKey

data class Like(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val LikeBy:String?=null,
    val PostId:String?=null
)
