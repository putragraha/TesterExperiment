package com.android.nsystem.testapps

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version LoginPresenterTest, v 0.1 14/12/20 01.14 by Putra Nugraha
 */
@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @InjectMocks
    lateinit var presenter: LoginPresenter

    @Mock
    lateinit var view: LoginContract.View

    private val value by lazy { "mock" }

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
    fun submitLogin_shouldCall_notifyLoginValid_inView() {
        // given
        val account = Account(value, value)

        // when
        presenter.submitLogin(account)

        // then
        verify(view).notifyLoginValid()
    }

    @Test
    fun submitLogin_withEmpty_username_shouldNotCall_notifyLoginValid_inView() {
        // given
        val account = Account("", value)

        // when
        presenter.submitLogin(account)

        // then
        verify(view, times(0)).notifyLoginValid()
    }

    @Test
    fun submitLogin_withEmpty_password_shouldNotCall_notifyLoginValid_inView() {
        // given
        val account = Account(value, "")

        // when
        presenter.submitLogin(account)

        // then
        verify(view, times(0)).notifyLoginValid()
    }

    @Test
    fun submitLogin_shouldCall_clearLoginInput_inView() {
        // given
        val account = Account(value, value)

        // when
        presenter.submitLogin(account)

        // then
        verify(view).clearLoginInput()
    }

    @Test
    fun submitLogin_withEmpty_username_shouldNotCall_clearLoginInput_inView() {
        // given
        val account = Account("", value)

        // when
        presenter.submitLogin(account)

        // then
        verify(view, times(0)).clearLoginInput()
    }

    @Test
    fun submitLogin_withEmpty_password_shouldNotCall_clearLoginInput_inView() {
        // given
        val account = Account(value, "")

        // when
        presenter.submitLogin(account)

        // then
        verify(view, times(0)).clearLoginInput()
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
}