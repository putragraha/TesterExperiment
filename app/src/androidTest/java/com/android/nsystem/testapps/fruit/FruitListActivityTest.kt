package com.android.nsystem.testapps.fruit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.nsystem.testapps.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version FruitListActivityTest, v 0.1 17/12/20 21.12 by Putra Nugraha
 */
class FruitListActivityTest {

    @Rule @JvmField
    val fruitListActivity = ActivityScenarioRule(FruitListActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun floatingActionBar_should_displayed() {
        // given
        fruitListActivity.scenario

        // when

        // then
        onView(withId(R.id.fab_message)).check(matches(isDisplayed())) // View Matcher
    }

    @Test
    fun contentImage_shouldHave_contentDescription() {
        // given
        fruitListActivity.scenario

        // when

        // then
        onView(withId(R.id.aciv_content)).check(matches(
            withContentDescription(R.string.content_image)) // View Matcher
        )
    }

    @Test
    fun recyclerView_shouldContains_fruit() {
        // given
        fruitListActivity.scenario
        val mockFruit = Fruit("Banana", R.drawable.banana)

        // when

        // then
        onView(withId(R.id.rv_fruit))
            .check(matches(hasFruitDataForPosition(0, mockFruit))) // Bounded Matcher
    }

    @Test
    fun recyclerView_shouldContains_Watermelon() {
        // given
        fruitListActivity.scenario
        val watermelon = "Watermelon"

        // when
        onView(withId(R.id.rv_fruit))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(hasDescendant(withText(watermelon)))
            ) // Action

        // then
        onView(withText(watermelon)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerViewItem_clicked_shouldOpen_FruitDetailActivity() {
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
            ) // Action

        // then
        intended(hasComponent(FruitDetailActivity::class.java.name))
    }

    private fun hasFruitDataForPosition(position: Int, fruit: Fruit): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("Fruit at $position does not exist")
            }

            override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
                val viewHolder = recyclerView?.findViewHolderForAdapterPosition(position)

                return recyclerView != null && viewHolder != null &&
                    withChild(withText(fruit.name)).matches(viewHolder.itemView)
            }
        }
    }

    @After
    fun tearDown() {
        Intents.release()
    }
}