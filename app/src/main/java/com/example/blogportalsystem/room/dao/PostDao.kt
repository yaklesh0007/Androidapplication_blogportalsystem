package com.example.blogportalsystem.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blogportalsystem.model.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(bloglist:List<Post> )

    @Query("select * from Post")
    suspend fun getPost():List<Post>
}