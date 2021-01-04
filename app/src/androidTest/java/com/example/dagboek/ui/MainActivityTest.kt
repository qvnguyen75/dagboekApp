package com.example.dagboek.ui


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dagboek.R
import com.example.dagboek.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    // rule voor iedere test
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigateOnClickList(){
        onView(withText("title post 2"))
            .perform(click())
        onView(withText("body post 2"))
            .check(matches(isDisplayed()))
    }
    @Test
    fun testAddMovieAndVerifyThatItAppearsInList() {
        val uniqueName = LocalDateTime.now().toString()

        // Navigate to add fragment from listview
        onView(withId(R.id.addButton))
            .perform(click())

        // Enter movie details
        onView(withId(R.id.titleView))
            .perform(typeText(uniqueName))

        onView(withId(R.id.bodyView))
            .perform(typeText("Test body text"))



        // Submit new movie
        onView(withId(R.id.submitButton))
            .perform(click())

        // Confirm that the new movie is in the list view
        onView(withText(uniqueName))
            .check(matches(isDisplayed()))
    }

}