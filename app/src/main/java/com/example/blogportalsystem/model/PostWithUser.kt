package com.example.blogportalsystem.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PostWithUser(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val title:String?=null,
    val image:String?=null,
    val description:String?=null,
    @Embedded(prefix = "user_")
    val userID:User?=null,
    @SerializedName("category")
    val categoryID:String?=null,
    val createdAT:String?=null,
    val updatedAT:String?=null
)
