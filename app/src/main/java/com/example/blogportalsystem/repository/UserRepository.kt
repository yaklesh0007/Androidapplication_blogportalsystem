package com.example.blogportalsystem.repository

import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.api.UserAPI
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.response.LoginResponse
import com.example.blogportalsystem.response.RegisterResponse

class UserRepository:MyApiRequest() {
    private val userAPI =
        ServiceBuilder.buildService(UserAPI::class.java)

    //register User

    suspend fun registerUser(user: User): RegisterResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }
    //login user
    suspend fun checkUser(email:String,password:String):LoginResponse{
        return apiRequest {
            userAPI.checkUser(email,password)
        }
    }
    
}