package com.android.nsystem.testapps

import android.os.SystemClock
import android.text.TextUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version LoginPresenterTest, v 0.1 14/12/20 01.14 by Putra Nugraha
 */
@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
@PrepareForTest(SystemClock::class, TextUtils::class)
class LoginPresenterTest {

    @InjectMocks
    lateinit var presenter: LoginPresenter

    @Mock
    lateinit var view: LoginContract.View

    private val value by lazy { "mock" }

    private val mockNumber by lazy { 2L }

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        stubTextUtilsIsEmpty()
        stubSystemClockUptimeMillis()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun isLoginInputValid_shouldReturn_true() {
        // given
        val account = Account(value, value)

        // when
        val isLoginInputValid = presenter.isLoginInputValid(account)

        // then
        assertTrue(isLoginInputValid)
    }

    @Test
    fun isLoginInputValid_withEmpty_username_shouldReturn_false() {
        // given
        val account = Account("", value)

        // when
        val isLoginInputValid = presenter.isLoginInputValid(account)

        // then
        assertFalse(isLoginInputValid)
    }

    @Test
    fun isLoginInputValid_withEmpty_password_shouldReturn_false() {
        // given
        val account = Account(value, "")

        // when
        val isLoginInputValid = presenter.isLoginInputValid(account)

        // then
        assertFalse(isLoginInputValid)
    }

    @Test
    fun submitLogin_shouldCall_showProgress_inView() {
        testDispatcher.runBlockingTest {
            // given
            val account = Account(value, value)

            // when
            presenter.submitLogin(account)
            advanceTimeBy(2000)

            // then
            verify(view).showProgress()
        }
    }

    @Test
    fun submitLogin_shouldCall_dismissProgress_inView() {
        testDispatcher.runBlockingTest {
            // given
            val account = Account(value, value)

            // when
            presenter.submitLogin(account)
            advanceTimeBy(2000)

            // then
            verify(view).dismissProgress()
        }
    }

    @Test
    fun submitLogin_shouldCall_notifyLoginValid_inView() {
        testDispatcher.runBlockingTest {
            // given
            val account = Account(value, value)

            // when
            presenter.submitLogin(account)
            advanceTimeBy(2000)

            // then
            verify(view).notifyLoginValid()
        }
    }

    @Test
    fun submitLogin_withEmpty_username_shouldNotCall_notifyLoginValid_inView() {
        testDispatcher.runBlockingTest {
            // given
            val account = Account("", value)

            // when
            presenter.submitLogin(account)
            advanceTimeBy(2000)

            // then
            verify(view, times(0)).notifyLoginValid()
        }
    }

    @Test
    fun submitLogin_withEmpty_password_shouldNotCall_notifyLoginValid_inView() {
        testDispatcher.runBlockingTest {
            // given
            val account = Account(value, "")

            // when
            presenter.submitLogin(account)
            advanceTimeBy(2000)

            // then
            verify(view, times(0)).notifyLoginValid()
        }
    }

    @Test
    fun loginInputTextChanged_shouldCall_enableLoginButton_inView() {
        // given
        val account = Account(value, value)

        // when
        presenter.loginInputTextChanged(account)

        // then
        verify(view).enableLoginButton()
    }

    @Test
    fun loginInputTextChanged_withEmpty_username_shouldCall_disableLoginButton_inView() {
        // given
        val account = Account("", value)

        // when
        presenter.loginInputTextChanged(account)

        // then
        verify(view).disableLoginButton()
    }

    @Test
    fun loginInputTextChanged_withEmpty_password_shouldCall_disableLoginButton_inView() {
        // given
        val account = Account(value, "")

        // when
        presenter.loginInputTextChanged(account)

        // then
        verify(view).disableLoginButton()
    }

    private fun stubTextUtilsIsEmpty() {
        mockStatic(TextUtils::class.java)
        `when`(TextUtils.isEmpty(anyString())).thenAnswer {
            val text = it.arguments[0] as String
            text.isEmpty()
        }
    }

    private fun stubSystemClockUptimeMillis() {
        mockStatic(SystemClock::class.java)
        `when`(SystemClock.uptimeMillis()).thenReturn(mockNumber)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}