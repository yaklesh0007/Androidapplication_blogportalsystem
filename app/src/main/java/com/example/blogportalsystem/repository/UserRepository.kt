package com.example.blogportalsystem.repository

import com.example.blogportalsystem.api.MyApiRequest
import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.api.UserAPI
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.response.LoginResponse

class UserRepository:MyApiRequest() {
    private val userAPI =
        ServiceBuilder.buildService(UserAPI::class.java)

    //register User

    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            userAPI.registerUser(user)
        }
    }
}