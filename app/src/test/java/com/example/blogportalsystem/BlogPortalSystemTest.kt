package com.example.blogportalsystem

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
}