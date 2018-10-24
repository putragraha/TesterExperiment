package com.android.nsystem.testapps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    private static final String INPUT = "input";

    @Mock
    private LoginContract.View mLoginView;
    private LoginContract.Presenter mLoginPresenter;

    private Account mAccountEmpty = new Account();
    private Account mAccountNormal = new Account();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mLoginPresenter = new LoginPresenter(mLoginView);

        mAccountNormal.setUsername(INPUT);
        mAccountNormal.setPassword(INPUT);
    }

    @Test
    public void submitLogin_NullInput_LoginInvalid() {
        mLoginPresenter.submitLogin(null);
        verify(mLoginView, never()).notifyLoginValid();
    }

    @Test
    public void submitLogin_EmptyInput_LoginInvalid() {
        mLoginPresenter.submitLogin(mAccountEmpty);
        verify(mLoginView, never()).notifyLoginValid();
    }

    @Test
    public void submitLogin_NormalInput_LoginValid() {
        mLoginPresenter.submitLogin(mAccountNormal);
        verify(mLoginView).notifyLoginValid();
        verify(mLoginView).clearLoginInput();
    }

    @Test
    public void loginInputTextChanged_NullInput_DisabledLoginButton() {
        mLoginPresenter.loginInputTextChanged(null);
        verify(mLoginView).disableLoginButton();
    }

    @Test
    public void loginInputTextChanged_EmptyInput_DisabledLoginButton() {
        mLoginPresenter.loginInputTextChanged(mAccountEmpty);
        verify(mLoginView).disableLoginButton();
    }

    @Test
    public void loginInputTextChanged_NormalInput_EnabledLoginButton() {
        mLoginPresenter.loginInputTextChanged(mAccountNormal);
        verify(mLoginView).enableLoginButton();
    }

    @Test
    public void isLoginInputValid_NullInput_InvalidInput() {
        assertFalse(mLoginPresenter.isLoginInputValid(null));
    }

    @Test
    public void isLoginInputValid_EmptyInput_InvalidInput() {
        assertFalse(mLoginPresenter.isLoginInputValid(mAccountEmpty));
    }

    @Test
    public void isLoginInputValid_NormalInput_InvalidInput() {
        assertTrue(mLoginPresenter.isLoginInputValid(mAccountNormal));
    }
}