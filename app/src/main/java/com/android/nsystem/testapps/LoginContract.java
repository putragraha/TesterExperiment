package com.android.nsystem.testapps;

public interface LoginContract {

    interface View {
        void notifyLoginValid();
        void clearLoginInput();
        void enableLoginButton();
        void disableLoginButton();
    }

    interface Presenter {
        boolean isLoginInputValid(Account account);
        void submitLogin(Account account);
        void loginInputTextChanged(Account account);
    }
}
