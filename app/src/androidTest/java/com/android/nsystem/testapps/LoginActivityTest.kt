package com.android.nsystem.testapps

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.android.nsystem.testapps.fruit.FruitListActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version LoginActivityTest, v 0.1 18/12/20 23.04 by Putra Nugraha
 */
class LoginActivityTest {

    private val mockInput by lazy { "INPUT" }

    @Rule @JvmField
    val loginActivity = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        loginActivity.scenario
        Intents.init()
    }

    @Test
    fun username_and_password_isEmpty_shouldDisable_loginButton() {
        // given

        // when

        // then
        onView(withId(R.id.login)).check(matches(not(isEnabled())))
    }

    @Test
    fun username_and_password_notEmpty_shouldEnable_loginButton() {
        // given

        // when
        onView(withId(R.id.username)).perform(typeText(mockInput))
        onView(withId(R.id.password)).perform(typeText(mockInput))

        // then
        onView(withId(R.id.login)).check(matches(isEnabled()))
    }

    @Test
    fun login_shouldOpen_FruitListActivity() {
        // given
        onView(withId(R.id.username)).perform(typeText(mockInput))
        onView(withId(R.id.password)).perform(typeText(mockInput))
        stubFruitListActivityIntent()

        // when
        onView(withId(R.id.login)).perform(click()) // Action

        // then
        intended(hasComponent(FruitListActivity::class.java.name))
    }

    private fun stubFruitListActivityIntent() {
        intending(hasComponent(FruitListActivity::class.java.name)).respondWith(
            Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())
        )
    }

    @After
    fun tearDown() {
        Intents.release()
    }
}