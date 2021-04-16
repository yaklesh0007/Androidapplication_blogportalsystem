package com.example.blogportalsystem


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.blogportalsystem.ui.SplashScreenActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class CheckSplashActivity {
    @get:Rule
    val testRule= ActivityScenarioRule(SplashScreenActivity::class.java)
    @Test
    fun checkfortoloadSplashActivity(){
        onView(withId(R.id.imagelogo))
            .check(matches(isDisplayed()))
        onView(withId(R.id.progressbar))
            .check(matches(isDisplayed()))
        Thread.sleep(2000)
    }
}