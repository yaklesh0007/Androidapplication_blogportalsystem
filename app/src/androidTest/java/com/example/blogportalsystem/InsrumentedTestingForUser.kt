package com.example.blogportalsystem

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.blogportalsystem.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class InsrumentedTestingForUser {
    @get:Rule
    val testRule= ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun checkforlogin(){
        onView(withId(R.id.edtemail))
            .perform(clearText())
            .perform(typeText("aklesh@gmail.com"))
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)
        onView(withId(R.id.edtpassword))
            .perform(clearText())
            .perform(typeText("aklesh"))
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)

        onView(withId(R.id.btnlogin))
            .perform(click())
        Thread.sleep(2000)
    }


}