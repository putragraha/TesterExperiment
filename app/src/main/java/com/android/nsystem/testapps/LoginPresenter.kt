package com.android.nsystem.testapps

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version LoginPresenter, v 0.1 13/12/20 23.37 by Putra Nugraha
 */
class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {

    override fun isLoginInputValid(account: Account): Boolean = with(account) {
        return username.isNotBlank() && password.isNotBlank()
    }

    override fun submitLogin(account: Account) {
        if (isLoginInputValid(account)) {
            view.run {
                notifyLoginValid()
                clearLoginInput()
            }
        }
    }

    override fun loginInputTextChanged(account: Account) {
        when {
            isLoginInputValid(account) -> view.enableLoginButton()
            else -> view.disableLoginButton()
        }
    }
}