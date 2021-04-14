package com.example.blogportalsystem.model

import androidx.room.PrimaryKey

data class Reply(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val replybody:String?=null,
    val userID:String?=null,
    val commentID:String?=null,
    val createdAT:String?=null,
    val updatedAT:String?=null
)
