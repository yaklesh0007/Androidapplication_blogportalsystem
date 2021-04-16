package com.example.blogportalsystem.repository

import com.example.blogportalsystem.api.CommentAPI
import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Comment
import com.example.blogportalsystem.response.AddCommentResponce
class CommentRepository:MyApiRequest (){
    private val commentAPI =
        ServiceBuilder.buildService(CommentAPI::class.java)

    suspend fun insertComment(comment: Comment): AddCommentResponce {
        return apiRequest {
            commentAPI.addcomment(ServiceBuilder.token!!,comment)
        }
    }

}