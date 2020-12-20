package com.android.nsystem.testapps

import com.nhaarman.mockitokotlin2.times
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
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version LoginPresenterTest, v 0.1 14/12/20 01.14 by Putra Nugraha
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @InjectMocks
    lateinit var presenter: LoginPresenter

    @Mock
    lateinit var view: LoginContract.View

    private val value by lazy { "mock" }

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
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

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}