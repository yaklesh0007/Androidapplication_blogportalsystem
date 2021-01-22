package com.example.blogportalsystem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.blogportalsystem.model.User

@Dao
interface DaoUser {

@Insert
suspend fun registerUser(user: User)

    @Update
    suspend fun updateUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)
}