package com.example.blogportalsystem

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.blogportalsystem.ui.AddPostActivity

import org.junit.Rule
import org.junit.Test

class AddBlogInstrumentedTesting {
    @get:Rule
    val testRule= ActivityScenarioRule(AddPostActivity::class.java)
    @Test
    fun checkforaddblog(){

    }
}