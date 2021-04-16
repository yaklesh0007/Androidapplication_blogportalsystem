package com.example.blogportalsystem

import com.example.blogportalsystem.api.ServiceBuilder
import com.example.blogportalsystem.model.Post
import com.example.blogportalsystem.model.User
import com.example.blogportalsystem.repository.PostRepository
import com.example.blogportalsystem.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BlogPortalSystemTest {
    private lateinit var userRepository: UserRepository

    // -----------------------------User Testing-----------------------------
    @Test
    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.checkUser("aklesh@gmail.com", "aklesh")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }
    @Test
    fun checkRegister()=runBlocking{
        userRepository= UserRepository()
        val user=User(username = "test",email = "test@test.com"
            ,password = "password",phone = "9801231313",gender="Male",userType = "normaluser")
        val responce=userRepository.registerUser(user)
        val expectedResult=true
        val actualResult=responce.success
        Assert.assertEquals(expectedResult,actualResult)

    }
    @Test
    fun checkupdateuser()= runBlocking {
        userRepository= UserRepository()
        val user= User(username = "aklesh",gender = "Female",phone = "9831010311")
        ServiceBuilder.token ="Bearer " + userRepository.checkUser("aklesh@gmail.com","aklesh").token
        val response=userRepository.updateprofileinfor(user)
        val expectedResult=true
        val actualResult=response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
    // checking for the post

    private lateinit var Postrepository:PostRepository
    @Test
    fun checkinsertblog()= runBlocking {
        Postrepository= PostRepository()
        val post=Post(title = "test",description = "tested tested",categoryID = "Social")
        ServiceBuilder.token ="Bearer " + userRepository.checkUser("aklesh@gmail.com","aklesh").token
        val responce=Postrepository.addBlog(post)
        val expectedResult=true
        val actualResult=responce.success
        Assert.assertEquals(expectedResult,actualResult)

    }
    @Test
    fun checkupdateblog()= runBlocking {
        Postrepository= PostRepository()
        val post=Post(title = "test",description = "tested tested",categoryID = "Social")
        ServiceBuilder.token ="Bearer " + userRepository.checkUser("aklesh@gmail.com","aklesh").token
        val responce=Postrepository.blogupdate(post,"60697105b42b68086425104d")

        val expectedResult=true
        val actualResult=responce.success
        Assert.assertEquals(expectedResult,actualResult)

    }
}