package com.multi_media.touristy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test


class HomeActivityTest {
    @Rule
    @JvmField
    public var activityActivityTestRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun checkWelcomeTextDisplay(){
        onView(withId(R.id.id_welcome))
            .check(matches(isCompletelyDisplayed()))  // none of the welcome text ahoudl be hidden
            .check(matches(isNotClickable()))   // it is a welcome view so shouldnt be clickable
    }

    @Test
    fun checkExploreButtonDisplay() {
        onView(withId(R.id.id_explore))
            .check(
                matches(isDisplayed())
            )
            .check((matches(isClickable()))) //assert view  is  displayed

    }

    @Test
    fun checkExploreButtonClickAndNewActivityEnterted() {

        onView(withId(R.id.id_explore))
            .perform(click()) //  perform a click on the explore button



    }

}