package com.android.nsystem.testapps.webview

import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version WebviewActivityTest, v 0.1 19/12/20 15.53 by Putra Nugraha
 */
class WebviewActivityTest {

    companion object {

        private const val mockInput = "Hi"
    }

    @Rule @JvmField
    val webviewActivity = ActivityScenarioRule(WebviewActivity::class.java)

    @Test
    fun submit_shouldSend_value_toOutput() {
        // given
        onWebView().withElement(findElement(Locator.ID, "field"))
            .perform(webKeys(mockInput))

        // when
        onWebView().withElement(findElement(Locator.ID, "submit"))
            .perform(webClick())

        // then
        onWebView().withElement(findElement(Locator.ID, "output"))
            .check(webMatches(getText(), containsString(mockInput)))
    }
}