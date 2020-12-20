package com.android.nsystem.testapps

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.nsystem.testapps.databinding.ActivityMainBinding
import com.android.nsystem.testapps.fruit.FruitListActivity

/**
 * @author Putra Nugraha (putra.nugraha@dana.id)
 * @version LoginActivity, v 0.1 13/12/20 23.47 by Putra Nugraha
 */
class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val loginTextWatcher by lazy {

        object : TextWatcher {

            override fun afterTextChanged(editable: Editable) {
                // No implementation
            }

            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int,
                                           after: Int) {
                // No implementation
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                presenter?.loginInputTextChanged(getAccount())
            }
        }
    }

    private lateinit var binding: ActivityMainBinding

    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupComponent()
    }

    override fun notifyLoginValid() {
        startActivity(Intent(this, FruitListActivity::class.java))
    }

    override fun enableLoginButton() {
        binding.login.run {
            isEnabled = true
            alpha = 1.0f
        }
    }

    override fun disableLoginButton() {
        binding.login.run {
            isEnabled = false
            alpha = 0.5f
        }
    }

    override fun showProgress() {
        binding.pbLoadSubmit.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        binding.pbLoadSubmit.visibility = View.GONE
    }

    private fun setupComponent() {
        binding.run {
            presenter = LoginPresenter(this@LoginActivity)
            username.addTextChangedListener(loginTextWatcher)
            password.addTextChangedListener(loginTextWatcher)
            login.setOnClickListener {
                presenter?.submitLogin(getAccount())
            }
        }
    }

    private fun getAccount() = with(binding) {
        Account(username.text.toString(), password.text.toString())
    }
}