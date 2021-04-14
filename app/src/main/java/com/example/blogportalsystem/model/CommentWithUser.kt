package com.example.blogportalsystem.model

import androidx.room.Embedded
import androidx.room.PrimaryKey

data class CommentWithUser(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val commentBody:String?=null,
    @Embedded(prefix = "user_")
    val userID:User?=null,
    val postID:String?=null,
    val createdAT:String?=null,
    val updatedAT:String?=null
)
