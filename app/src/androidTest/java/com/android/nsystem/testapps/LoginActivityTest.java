package com.android.nsystem.testapps;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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