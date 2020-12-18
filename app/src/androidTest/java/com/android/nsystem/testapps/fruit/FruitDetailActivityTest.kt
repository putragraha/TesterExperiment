package com.android.nsystem.testapps.fruit

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.nsystem.testapps.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version FruitDetailActivityTest, v 0.1 18/12/20 14.36 by Putra Nugraha
 */
class FruitDetailActivityTest {

    @get:Rule
    val fruitListActivity = ActivityScenarioRule(FruitListActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun textView_shouldShow_fruitName_from_bundle() {
        // given
        fruitListActivity.scenario
        val watermelon = "Watermelon"

        // when
        onView(withId(R.id.rv_fruit))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(watermelon)),
                    click()
                )
            )

        // then
        onView(withId(R.id.actv_fruit_name)).check(matches(withText(watermelon)))
    }

    @Test
    fun backPressed_should_redirectBackTo_fruitListActivity() {
        // given
        fruitListActivity.scenario
        val watermelon = "Watermelon"
        onView(withId(R.id.rv_fruit))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(watermelon)),
                    click()
                )
            )

        // when
        pressBack()

        // then
        onView(withId(R.id.fab_message)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        Intents.release()
    }
}