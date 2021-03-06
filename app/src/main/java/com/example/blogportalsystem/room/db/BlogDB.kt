package com.example.blogportalsystem.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.PostWithUser
import com.example.blogportalsystem.room.dao.PostDao

@Database(
    entities = [(Post::class), (PostWithUser::class)],
    version = 4
)
abstract class BlogDB:RoomDatabase() {
         abstract fun getPostDAO() : PostDao
    companion object {
        @Volatile
        private var Instance: BlogDB? = null

        fun getInstance(context: Context): BlogDB {
            if (Instance == null) {
                synchronized(BlogDB::class) {
                    Instance = buildDatabase(context)
                }
            }

            return Instance!!
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BlogDB::class.java,
                "Blogdatabase"
            ).fallbackToDestructiveMigration().build()
    }

}