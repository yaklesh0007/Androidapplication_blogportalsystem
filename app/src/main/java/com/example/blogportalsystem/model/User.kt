package com.example.blogportalsystem.model

import android.os.Parcel
import android.os.Parcelable

data class User
    (
    val UserFullName:String?=null,
    val UserEmail:String?=null,
    val UserPassword:String?=null,
    val UserPhone:Int?=null,
    val UserImage:String?=null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(UserFullName)
        parcel.writeString(UserEmail)
        parcel.writeString(UserPassword)
        parcel.writeValue(UserPhone)
        parcel.writeString(UserImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}


