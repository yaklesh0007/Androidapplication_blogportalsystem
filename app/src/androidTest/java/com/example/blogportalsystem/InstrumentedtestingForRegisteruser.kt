package com.example.blogportalsystem

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.blogportalsystem.ui.SignUpActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class InstrumentedtestingForRegisteruser {
    @get:Rule
    val testRule= ActivityScenarioRule(SignUpActivity::class.java)
    @Test
    fun checkforResgister(){
        onView(withId(R.id.edtfullname))
            .perform(typeText("test2131"))
            .perform(closeSoftKeyboard())
       onView(withId(R.id.edtemail))
            .perform(typeText("Test2231@gmail.com"))
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)
       onView(withId(R.id.edtpassword))
            .perform(typeText("aklesh"))
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)
        onView(withId(R.id.edtphone))
            .perform(typeText("1213131312"))
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)
        onView(withId(R.id.RdoMale))
            .perform(click())
            .check(matches(isChecked()))
        onView(withId(R.id.RdoFemale))
            .check(matches(not(isChecked())))
        onView(withId(R.id.RdoOther))
            .check(matches(not(isChecked())))

        onView(withId(R.id.btnSignup))
            .perform(click())
        Thread.sleep(2000)


    }




}