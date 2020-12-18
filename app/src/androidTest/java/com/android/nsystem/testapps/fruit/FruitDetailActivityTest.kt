package com.android.nsystem.testapps.fruit

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.LayoutAssertions.noEllipsizedText
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
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

    private val watermelon by lazy { "Watermelon" }

    @get:Rule
    val fruitListActivity = ActivityScenarioRule(FruitListActivity::class.java)

    @Before
    fun setUp() {
        fruitListActivity.scenario
        Intents.init()
        openDetailActivity()
    }

    @Test // View Assertion
    fun fruitImage_shouldShown() {
        // given

        // when

        // then
        onView(withId(R.id.aciv_fruit_image)).check(matches(isDisplayed()))
    }

    @Test // View Assertion
    fun textView_shouldShow_fruitName_from_bundle() {
        // given

        // when

        // then
        onView(withId(R.id.actv_fruit_name)).check(matches(withText(watermelon)))
    }

    @Test // View Assertion
    fun textView_shouldNotEllipsized() {
        // given

        // when

        // then
        onView(withId(R.id.actv_fruit_name)).check(noEllipsizedText())
    }

    @Test // Layout Assertion
    fun layout_noOverlaps() {
        // given

        // when

        // then
        onView(withId(R.id.cl_fruit_detail)).check(noOverlaps())
    }

    @Test
    fun backPressed_should_redirectBackTo_fruitListActivity() {
        // given

        // when
        pressBack()

        // then
        onView(withId(R.id.fab_message)).check(matches(isDisplayed()))
    }

    @Test // Position Assertion
    fun fruitImage_shouldAbove_fruitName() {
        // given

        // when

        // then
        onView(withId(R.id.aciv_fruit_image)).check(isCompletelyAbove(withId(R.id.actv_fruit_name)))
    }

    private fun openDetailActivity() {
        onView(withId(R.id.rv_fruit))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(watermelon)),
                    click()
                )
            )
    }

    @After
    fun tearDown() {
        Intents.release()
    }
}