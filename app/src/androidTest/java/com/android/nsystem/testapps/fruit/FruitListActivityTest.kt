package com.android.nsystem.testapps.fruit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.nsystem.testapps.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version FruitListActivityTest, v 0.1 17/12/20 21.12 by Putra Nugraha
 */
class FruitListActivityTest {

    @get:Rule
    val fruitListActivity = ActivityScenarioRule(FruitListActivity::class.java)

    @Test
    fun floatingActionBar_should_displayed() {
        // given

        // when
        fruitListActivity.scenario

        // then
        onView(withId(R.id.fab_message)).check(matches(isDisplayed())) // View Matcher
    }

    @Test
    fun contentImage_shouldHave_contentDescription() {
        // given

        // when
        fruitListActivity.scenario

        // then
        onView(withId(R.id.aciv_content)).check(matches(
            withContentDescription(R.string.content_image)) // View Matcher
        )
    }

    @Test
    fun recyclerView_shouldContains_fruit() {
        // given
        val mockFruit = Fruit("Banana", R.drawable.banana)

        // when
        fruitListActivity.scenario

        // then
        onView(withId(R.id.rv_fruit))
            .check(matches(hasFruitDataForPosition(0, mockFruit))) // Bounded Matcher
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
}