package com.zulham.mtv.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.contrib.ViewPagerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zulham.mtv.R
import com.zulham.mtv.ui.main.MainActivity
import com.zulham.mtv.utils.DummyData
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeUITest {

    @get : Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val listItemInTest = 9

    private val movieInTest = DummyData.generateDummyMovie()
    private val movieDetailInTest = movieInTest[listItemInTest]

    private val tvInTest = DummyData.generateDummyTV()
    private val tvDetailInTest = tvInTest[listItemInTest]

    @Test
    fun test_isFragment_onLaunch() {
        onView(withId(R.id.tabs))
                .check(matches(isDisplayed()))
        onView(withId(R.id.view_pager))
                .perform(ViewPagerActions.scrollRight(true))
    }

    @Test
    fun loadMovie(){
        onView(withId(R.id.tabs))
                .check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.rvMovie)))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(movieInTest.size))
    }

    @Test
    fun loadTV(){
        onView(withId(R.id.view_pager))
                .perform(ViewPagerActions.scrollRight(true))
        onView(withId(R.id.tabs))
                .check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.rvTV)))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(tvInTest.size))
    }

    @Test
    fun detailMovie(){
        onView(allOf(isDisplayed(), withId(R.id.rvMovie)))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(listItemInTest, click()))

        onView(withId(R.id.titleDetail))
                .check(matches(isDisplayed()))
        onView(withId(R.id.titleDetail))
                .check(matches(withText(movieDetailInTest.title)))

        onView(withId(R.id.genreDetail))
                .check(matches(isDisplayed()))
        onView(withId(R.id.genreDetail))
                .check(matches(withText(movieDetailInTest.genre)))

        onView(withId(R.id.showId))
                .check(matches(isDisplayed()))
        onView(withId(R.id.showId))
                .check(matches(withText(movieDetailInTest.showId)))

        onView(withId(R.id.showProduction))
                .check(matches(isDisplayed()))
        onView(withId(R.id.showProduction))
                .check(matches(withText(movieDetailInTest.production)))

        onView(withId(R.id.tv_justified_paragraph))
                .check(matches(isDisplayed()))
        onView(withId(R.id.tv_justified_paragraph))
                .check(matches(withText(movieDetailInTest.description)))
    }

    @Test
    fun detailTVShow(){
        onView(withId(R.id.view_pager))
                .perform(ViewPagerActions.scrollRight(true))
        onView(withId(R.id.tabs))
                .check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.rvTV)))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(listItemInTest, click()))

        onView(withId(R.id.titleDetail))
                .check(matches(isDisplayed()))
        onView(withId(R.id.titleDetail))
                .check(matches(withText(tvDetailInTest.title)))

        onView(withId(R.id.genreDetail))
                .check(matches(isDisplayed()))
        onView(withId(R.id.genreDetail))
                .check(matches(withText(tvDetailInTest.genre)))

        onView(withId(R.id.showId))
                .check(matches(isDisplayed()))
        onView(withId(R.id.showId))
                .check(matches(withText(tvDetailInTest.showId)))

        onView(withId(R.id.showProduction))
                .check(matches(isDisplayed()))
        onView(withId(R.id.showProduction))
                .check(matches(withText(tvDetailInTest.production)))

        onView(withId(R.id.tv_justified_paragraph))
                .check(matches(isDisplayed()))
        onView(withId(R.id.tv_justified_paragraph))
                .check(matches(withText(tvDetailInTest.description)))
    }

}