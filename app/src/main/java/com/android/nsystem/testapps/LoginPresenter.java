package com.android.nsystem.testapps;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mLoginView;

    LoginPresenter(LoginContract.View view) {
        this.mLoginView = view;
    }

    @Override
    public boolean isLoginInputValid(Account account) {
        if (account == null) {
            return false;
        } else {
            String username = account.getUsername();
            String password = account.getPassword();

            return username != null && !username.isEmpty() && password != null && !password.isEmpty();
        }
    }

    @Override
    public void submitLogin(Account account) {
        if (account != null) {
            if (isLoginInputValid(account)) {
                mLoginView.notifyLoginValid();
                mLoginView.clearLoginInput();
            }
        }
    }

    @Override
    public void loginInputTextChanged(Account account) {
        if (account == null) {
            mLoginView.disableLoginButton();
        } else {
            if (isLoginInputValid(account))
                mLoginView.enableLoginButton();
            else
                mLoginView.disableLoginButton();
        }
    }
}
