package com.zulham.mtv.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.zulham.mtv.R
import com.zulham.mtv.ui.splash.SplashActivity
import org.junit.Rule
import org.junit.Test

class SplashUITest {

    @get : Rule
    val activityScenario = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun generalSplash(){
        onView(withId(R.id.tvSplash)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSplash)).check(matches(withText(R.string.MTV)))

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

}