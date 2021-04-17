package com.example.blogportalsystem.repository

import com.example.blogportalsystem.api.CommentAPI
import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Comment
import com.example.blogportalsystem.response.AddCommentResponce
import com.example.blogportalsystem.response.GetCommentResponce

class CommentRepository:MyApiRequest (){
    private val commentAPI =
        ServiceBuilder.buildService(CommentAPI::class.java)

    suspend fun insertComment(comment: Comment): AddCommentResponce {
        return apiRequest {
            commentAPI.addcomment(ServiceBuilder.token!!,comment)
        }
    }

    suspend fun deletecomment(id:String, userID:String):AddCommentResponce{
        return apiRequest {
            commentAPI.deleteComment(ServiceBuilder.token!!,id,userID)
        }
    }
    suspend fun getcomments(id:String):GetCommentResponce{
        return apiRequest {
            commentAPI.getComments(ServiceBuilder.token!!,id)
        }
    }
    suspend fun updatecomment(id:String,body:Comment):AddCommentResponce{
        return apiRequest {
            commentAPI.updatecomment(ServiceBuilder.token!!,id,body)
        }
    }

}