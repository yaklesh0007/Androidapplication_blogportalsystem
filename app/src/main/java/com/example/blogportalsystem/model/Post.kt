package com.example.blogportalsystem.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Post(
    @PrimaryKey
    val _id:String,

    val title:String?=null,
    val image:String?=null,
    val description:String?=null,
    val userID:String?=null,
    val categoryID:String?=null,
//    val likes:ArrayList<Like>,
//    val comments:ArrayList<Comment>,
    val createdAT:String?=null,
    val updatedAT:String?=null
){


}
@Entity
data class Comment(
    @PrimaryKey
    val _id: String,
    val commentBody:String?=null,
    val userID:String?=null,
    val replies:ArrayList<Reply>,
    val postID:String?=null,
    val createdAT:Date?=null,
    val updatedAT:Date?=null
)
@Entity
data class Reply(
    @PrimaryKey
    val _id: String,
    val replyBody:String?=null,
    val userID: String?=null,
    val commentID:String?=null,
    val createdAT:Date?=null,
    val updatedAT:Date?=null
)
@Entity
data class Like(
    val LikedBy:String?=null
)
