package com.android.nsystem.testapps;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class LoginActivityTest {

    private static final String INPUT = "input";

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void setUp(){
    }

    @Test
    public void testNormalInputScenario() {
        Espresso.onView(withId(R.id.username)).perform(typeText(INPUT));
        Espresso.onView(withId(R.id.password)).perform(typeText(INPUT));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.login)).perform(click());
    }

    @Test
    public void testEmptyInputScenario() {
        Espresso.onView(withId(R.id.login)).check(matches(not(isEnabled())));
    }

    @After
    public void tearDown() throws Exception {
    }
}