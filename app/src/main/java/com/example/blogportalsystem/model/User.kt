package com.example.blogportalsystem.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.blogportalsystem.api.ServiceBuilder

@Entity
data class User
    (

    @PrimaryKey
    val _id: String = "",
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    val phone: String? = null,
    val image: String? = null,
    val gender: String? = null,
    val userType: String? = null
) {
        fun loadImagePath() : String{
            return "${ServiceBuilder.loadImagePath()}/user/$_id"
        }
    }




