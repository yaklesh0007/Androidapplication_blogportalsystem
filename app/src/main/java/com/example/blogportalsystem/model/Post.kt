package com.example.blogportalsystem.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Post(
    @PrimaryKey
    val _id:String,
    val title:String?=null,
    val image:String?=null,
    val description:String?=null,
    val userID:String?=null,
    val categoryID:String?=null,
    val likes:ArrayList<Like>
)
data class Like(
    val LikedBy:String?=null
)
