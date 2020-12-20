package com.android.nsystem.testapps

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version LoginContract, v 0.1 13/12/20 23.35 by Putra Nugraha
 */
interface LoginContract {

    interface View {

        fun notifyLoginValid()

        fun enableLoginButton()

        fun disableLoginButton()

        fun showProgress()

        fun dismissProgress()
    }

    interface Presenter {

        fun isLoginInputValid(account: Account): Boolean

        fun submitLogin(account: Account)

        fun loginInputTextChanged(account: Account)
    }
}