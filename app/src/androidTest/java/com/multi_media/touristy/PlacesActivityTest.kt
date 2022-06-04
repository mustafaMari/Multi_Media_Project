package com.multi_media.touristy

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import  org.hamcrest.Matchers.anything
import com.multi_media.touristy.Constants.Companion.CITY
import com.multi_media.touristy.Constants.Companion.PLACE_TYPE
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PlacesActivityTest {

//    @Rule
//    @JvmField
//    val activityRule = ActivityScenarioRule(PlacesActivity::class.java)

    @Rule
    @JvmField
    val mActivityTestRule: ActivityTestRule<PlacesActivity> =
        object : ActivityTestRule<PlacesActivity>(PlacesActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, PlacesActivity::class.java).apply {
                    putExtra(CITY,
                        "Gdansk")
                    putExtra(
                        PLACE_TYPE,
                        "Museums")

                }
            }
        }
    @Test
    fun checkDisplay() {

        onView(withId(R.id.listView))
            .check(matches(isDisplayed()))
            .check(matches(isClickable()))
    }


}