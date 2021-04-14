package com.example.blogportalsystem.repository

import com.example.blogportalsystem.api.BlogAPI
import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.response.*
import okhttp3.MultipartBody

class PostRepository :MyApiRequest(){
    private val blogAPI=ServiceBuilder.buildService(BlogAPI::class.java)

    suspend fun addBlog(blog: Post): AddblogResponce {
        return apiRequest {
            blogAPI.addblog(
                ServiceBuilder.token!!, blog
            )
        }
    }

    suspend fun getallBlog():GetBlogResponse{
        return apiRequest {
            blogAPI.getblog(
                ServiceBuilder.token!!
            )
        }
    }
    suspend fun deleteblog(id:String,userID:String): DeleteblogResponce {
        return apiRequest {
            blogAPI.deletepost(
                ServiceBuilder.token!!,id,userID
            )
        }
    }
    suspend fun getmypost():getmyblogResponce{
        return apiRequest {
            blogAPI.getmypost(
                ServiceBuilder.token!!
            )
        }
    }
    suspend fun uploadImage(id: String, body: MultipartBody.Part):AddblogResponce{
        return apiRequest {
            blogAPI.uploadImage(ServiceBuilder.token!!,id,body)
        }
    }

    suspend fun blogupdate(blog: Post,id:String): UpdateBlogResponce {
        return apiRequest {
            blogAPI.blogupdate(ServiceBuilder.token!!,blog,id)
        }
    }
}