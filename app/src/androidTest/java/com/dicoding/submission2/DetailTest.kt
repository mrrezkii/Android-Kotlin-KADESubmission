package com.dicoding.submission2

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.dicoding.submission2.R.id.add_fav
import com.dicoding.submission2.R.id.recyclerViewLastMatch
import com.dicoding.submission2.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DetailTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test() {
        delay()
        Espresso.onView(ViewMatchers.withId(recyclerViewLastMatch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(recyclerViewLastMatch))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        delay()
        Espresso.onView(ViewMatchers.withId(R.id.ivAway))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        pressBack()

        // add fav
        delay()
        Espresso.onView(ViewMatchers.withId(add_fav)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_fav)).perform(ViewActions.click())
        delay()

        // delete fav
        delay()
        Espresso.onView(ViewMatchers.withId(add_fav)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_fav)).perform(ViewActions.click())

        Espresso.pressBack()
    }

    private fun delay() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}
