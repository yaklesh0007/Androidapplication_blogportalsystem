package com.example.blogportalwear.RepositoryForwear

import com.example.blogportalwear.APIFORWEAR.MyApiRequest
import com.example.blogportalwear.APIFORWEAR.ServiceBuilder
import com.example.blogportalwear.APIFORWEAR.UserAPI
import com.example.blogportalwear.ModalForWear.User
import com.example.blogportalwear.ResponseForWear.FetchUserResponse
import com.example.blogportalwear.ResponseForWear.LoginResponse
import okhttp3.MultipartBody

class UserRepository: MyApiRequest() {

    private val userAPI =
        ServiceBuilder.buildService(UserAPI::class.java)


    //login user
    suspend fun checkUser(email:String,password:String): LoginResponse {
        return apiRequest {
            userAPI.checkUser(email,password)
        }
    }
    // show profile
    suspend fun showprofile():FetchUserResponse{
        return apiRequest {
            userAPI.showprofile(ServiceBuilder.token!!)
        }
    }


}