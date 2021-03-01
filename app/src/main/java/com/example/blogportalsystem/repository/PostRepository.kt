package com.example.blogportalsystem.repository

import com.example.blogportalsystem.api.BlogAPI
import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.response.AddblogResponce
import com.example.blogportalsystem.response.GetBlogResponse

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
}