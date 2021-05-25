package com.zulham.mtv.core.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.zulham.mtv.R
import com.zulham.mtv.splash.SplashActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashUITest {

    @get : Rule
    val activityScenario = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(SplashActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }

    @Test
    fun generalSplash(){
        onView(withId(R.id.tvSplash)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSplash)).check(matches(withText(R.string.MTV)))
    }

}