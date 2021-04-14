package com.example.blogportalsystem.model

import androidx.room.Embedded
import androidx.room.PrimaryKey

data class Comment(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val commentBody:String?=null,
    val userID:String?=null,
    val postID:String?=null,
    val createdAT:String?=null,
    val updatedAT:String?=null
)
