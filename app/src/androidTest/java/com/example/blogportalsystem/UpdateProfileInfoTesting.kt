package com.example.blogportalsystem

import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.action.ViewActions.*

import androidx.test.espresso.assertion.ViewAssertions.matches

import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest

import com.example.blogportalsystem.ui.UpdateProfileActivity

import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class UpdateProfileInfoTesting {
    @get:Rule
    val testRule= ActivityScenarioRule(UpdateProfileActivity::class.java)
    @Test
    fun instrutestforprofileupdate(){
        onView(withId(R.id.Etupdatefullname))
            .perform(typeText("test2131"))
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)
        onView(withId(R.id.EtupdatePhone))
            .perform(typeText("9831200112"))
            .perform(closeSoftKeyboard())
        Thread.sleep(2000)
        onView(withId(R.id.UpdateRdoMale))
            .perform(click())
            .check(matches(isChecked()))
        onView(withId(R.id.UpdateRdoFemale))
            .check(matches(not(isChecked())))
        onView(withId(R.id.UpdateRdoOther))
            .check(matches(not(isChecked())))
        Thread.sleep(2000)
        onView(withId(R.id.btnUpdateProfileinfo))
            .perform(click())
        Thread.sleep(2000)

        onView(withId(R.id.FabBtnAdd))
            .check(matches(isDisplayed()))

    }
}