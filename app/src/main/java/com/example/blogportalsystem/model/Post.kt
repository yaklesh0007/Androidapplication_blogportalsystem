package com.example.blogportalsystem.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Post(
    @PrimaryKey(autoGenerate = false)
    val _id:String="",
    val title:String?=null,
    val image:String?=null,
    val description:String?=null,
    val userID:String?=null,
    @SerializedName("category")
    val categoryID:String?=null,
//    val likes:ArrayList<Like>,
//    val comments:ArrayList<Comment>,
    val createdAT:String?=null,
    val updatedAT:String?=null
)

