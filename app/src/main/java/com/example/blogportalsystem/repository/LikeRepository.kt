package com.example.blogportalsystem.repository


import com.example.blogportalsystem.api.LikeAPI
import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.response.AddlikeResponce

class LikeRepository: MyApiRequest(){
    private val LikeAPI= ServiceBuilder.buildService(LikeAPI::class.java)

    suspend fun addlike(postID:String):AddlikeResponce{
        return apiRequest {
            LikeAPI.insertlike(
                ServiceBuilder.token!!,postID
            )
        }
    }

}