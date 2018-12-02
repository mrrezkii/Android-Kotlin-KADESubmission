package com.dicoding.submission2

import android.content.Intent
import android.os.SystemClock
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.dicoding.submission2.R.id.recyclerViewLastMatch
import com.dicoding.submission2.R.id.viewpager_main
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecyclerViewFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    private var myActivityIntent = Intent(InstrumentationRegistry.getTargetContext(), MainActivity::class.java)

    @Before
    fun setup() {
        activityRule.launchActivity(myActivityIntent)
    }

    @Test
    fun testRv() {
        Thread.sleep(3000)
        try {
            Espresso.onView(ViewMatchers.withId(recyclerViewLastMatch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(recyclerViewLastMatch))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
            Espresso.pressBack()
            onView(withId(viewpager_main)).check(matches(isCompletelyDisplayed()))
            Thread.sleep(3000)
            try {
                val matcher = allOf(
                    withText("TAB TITLE"),
                    isDescendantOfA(withId(R.id.tabs_main))
                )
                onView(matcher).perform(click())
                SystemClock.sleep(800) // Wait a little until the content is loaded
            } catch (e: Exception) {
                Log.e("Error", e.message)
            }
            onView(withId(viewpager_main)).check(matches(isCompletelyDisplayed()))
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }

    }
}