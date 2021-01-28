package com.example.blogportalsystem.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blogportalsystem.dao.DaoUser
import com.example.blogportalsystem.model.User

@Database(
    entities = [(User::class)],
    version = 1
)
abstract class UserDB : RoomDatabase() {
    abstract fun getUserDao():DaoUser
    companion object {
        @Volatile

        private var instance: UserDB? = null
        fun getInstance(context: Context): UserDB {
            if (instance == null) {
                synchronized(UserDB::class) {
                    instance = BuildDatabase(context)
                }

            }
            return instance!!
        }

        private fun BuildDatabase(context: Context): UserDB? {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDB::class.java,
                "BlogBackend"
            ).build()
        }
    }
}