package com.example.blogportalsystem.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User
    (
    val UserFullName:String?=null,
    val UserEmail:String?=null,
    val UserPassword:String?=null,
    val UserPhone:String?=null,
    val UserImage:String?=null
){
@PrimaryKey(autoGenerate = true)
var userId:Int=0
}





