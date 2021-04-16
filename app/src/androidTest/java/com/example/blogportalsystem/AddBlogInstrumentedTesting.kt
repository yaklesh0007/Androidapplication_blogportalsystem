package com.example.blogportalsystem


import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.blogportalsystem.ui.AddPostActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers

import org.hamcrest.Matchers.containsString

import org.junit.Rule
import org.junit.Test

class AddBlogInstrumentedTesting {
    @get:Rule
    val testRule= ActivityScenarioRule(AddPostActivity::class.java)
    @Test
    fun checkforaddblog(){
        onView(withId(R.id.etTitle))
            .perform(ViewActions.typeText("test2131"))
            .perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(1000)
        onView(withId(R.id.etdescription))
            .perform(ViewActions.typeText("test2131"))
            .perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(1000)
        onView(withId(R.id.spinner)).perform(click())

        onView(withId(R.id.spinner))
            .perform(click())
            .check(matches(withText(containsString("Social"))))

    }
}