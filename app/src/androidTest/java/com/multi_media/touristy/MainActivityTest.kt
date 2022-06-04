package com.multi_media.touristy

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.BundleMatchers.hasEntry
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.IsAnything.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var signInIdlingResource: IdlingResource


//    @Before
//    fun setUp() {
//        val activity = activityRule.activity as MainActivity
//        // Get the IdlingResource directly from the activity
//        IdlingRegistry.getInstance().register(signInIdlingResource)
//    }
//
//    @After
//    fun tearDown() {
//        IdlingRegistry.getInstance().unregister(signInIdlingResource)
//    }

    @Test
    fun checkDisplay() {
        Thread.sleep(1000)
        onView(withId(R.id.autoCompleteTextView_city))
            .check(
                matches(
                    isDisplayed()))
    }

    @Test
    fun checkSelectCityDisplay() {
       onView(withId(R.id.autoCompleteTextView_city))
            .check(matches(isDisplayed()))
           .inRoot(RootMatchers.isPlatformPopup())
           .perform(click())    //click on drop down men u
        //assert view  is  displayed

        onData(anything())
            .inAdapterView(
                withId(R.id.autoCompleteTextView_city))
            .atPosition(1)
            .perform(click())
    }

    @Test
    fun checkSelectPlaceTypesDisplay(){
        onView(withId(R.id.autoCompleteTextView_placeType))
            .check(matches(isDisplayed()))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())    //click on drop down men u
        //assert view  is  displayed

        onData(anything())
            .inAdapterView(
                withId(R.id.autoCompleteTextView_placeType))
            .atPosition(1)
            .perform(click())
    }

    @Test
    fun checkForGoButtonPressedAndNewActivityEntered() {
        Intents.init()
        onView(withId(R.id.id_search))
            .check(matches(isDisplayed())) //assert view  if button is  displayed
            .perform(click())
        Intents.intended(hasComponent(PlacesActivity::class.java.name))
        Intents.release()

        //check if  intent  was started
    }

}