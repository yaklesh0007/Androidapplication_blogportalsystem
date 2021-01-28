package com.example.blogportalsystem.dao

import androidx.room.*
import com.example.blogportalsystem.model.User

@Dao
interface DaoUser {

    @Insert
    suspend fun registerUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from User where UserEmail=(:email) and UserPassword=(:password)")
    suspend fun checkUser(email: String, password: String): User
}