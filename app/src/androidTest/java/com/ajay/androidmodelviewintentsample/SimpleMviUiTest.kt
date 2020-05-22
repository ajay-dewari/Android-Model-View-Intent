package com.ajay.androidmodelviewintentsample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SimpleMviUiTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MviActivity::class.java)

    @Test
    fun testInitialValue() {
        onView(withId(R.id.currentValueText))
            .check(matches(withText("0")))
    }

    @Test
    fun testAdd() {
        onView(withId(R.id.numberToAdd))
            .perform(replaceText("4"))

        onView(withId(R.id.addButton))
            .perform(click())

        onView(withId(R.id.numberToAdd))
            .perform(replaceText("2"))

        onView(withId(R.id.addButton))
            .perform(click())

        onView(withId(R.id.currentValueText))
            .check(matches(withText("6")))
    }

    @Test
    fun testAddWithNoNumber() {
        onView(withId(R.id.addButton))
            .perform(click())

        onView(withText(R.string.msg_invalid_number_selected))
            .inRoot(withDecorView(not(activityTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }
}
