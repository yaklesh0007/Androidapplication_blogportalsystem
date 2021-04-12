package com.example.blogportalsystem.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostWithUser(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val title:String?=null,
    val image:String?=null,
    val description:String?=null,
    @Embedded(prefix = "user_")
    val userID:User?=null,
    val categoryID:String?=null,
//    val likes:ArrayList<Like>,
//    val comments:ArrayList<Comment>,
    val createdAT:String?=null,
    val updatedAT:String?=null
)
