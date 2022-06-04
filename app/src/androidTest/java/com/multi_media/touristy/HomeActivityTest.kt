package com.multi_media.touristy

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.BundleMatchers.hasEntry
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


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
                matches(isDisplayed())) //assert view  is  displayed
    }

    @Test
    fun checkExploreButtonClickAndNewActivityEnterted() {
        Intents.init()
        onView(withId(R.id.id_explore))
            .perform(click()) //  perform a click on the explore button
        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.name))  // should take us to main activity
        Intents.release()


    }

}