package com.example.blogportalsystem

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.blogportalsystem.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class loadSinupActivityTesting {
    @get:Rule
    val testRule= ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun toloadSignupActvity(){
        onView(withId(R.id.tvRegister))
            .perform(click())
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)
//        onView(withId(R.id.TvSignUpTitle))
////            .check(matches(isDisplayed()))
//            .check(matches(withText("Sign up Here!!")))
//        Thread.sleep(2000)
    }
}