package com.example.blogportalsystem.model

import androidx.room.Embedded
import androidx.room.PrimaryKey

data class ReplyWithUser(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val replybody:String?=null,
    @Embedded(prefix = "user_")
    val userID:User?=null,
    val commentID:String?=null,
    val createdAT:String?=null,
    val updatedAT:String?=null
)
