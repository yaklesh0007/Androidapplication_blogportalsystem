package com.example.blogportalsystem.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User
    (
    val _id:String?=null,
    val username:String?=null,
    val email:String?=null,
    val password:String?=null,
    val phone:String?=null,
    val image:String?=null,
    val gender:String?=null,
    val userType:String?=null
){
}





