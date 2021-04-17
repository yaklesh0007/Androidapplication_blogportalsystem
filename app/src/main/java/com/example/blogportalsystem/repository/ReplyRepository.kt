package com.example.blogportalsystem.repository

import com.example.blogportalsystem.api.CommentAPI
import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ReplyAPI
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Comment
import com.example.blogportalsystem.model.Reply
import com.example.blogportalsystem.response.*

class ReplyRepository: MyApiRequest() {
    private val replyAPI =
        ServiceBuilder.buildService(ReplyAPI::class.java)

    suspend fun insertreply(reply: Reply): AddReplyResponce {
        return apiRequest {
            replyAPI.addReply(ServiceBuilder.token!!,reply)
        }
    }

    suspend fun deletereply(id:String, userID:String): AddReplyResponce {
        return apiRequest {
            replyAPI.deleteReply(ServiceBuilder.token!!,id,userID)
        }
    }
    suspend fun getreplys(id:String): GetReplyResponce {
        return apiRequest {
            replyAPI.getReplys(ServiceBuilder.token!!,id)
        }
    }


}