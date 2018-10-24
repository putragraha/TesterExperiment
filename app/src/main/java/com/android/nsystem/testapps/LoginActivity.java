package com.android.nsystem.testapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View, TextWatcher {

    private EditText mUsername, mPassword;
    private Button mLogin;

    private Account mAccount;
    private LoginContract.Presenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.username);
        mUsername.addTextChangedListener(this);

        mPassword = findViewById(R.id.password);
        mPassword.addTextChangedListener(this);

        mLogin = findViewById(R.id.login);
        mLogin.setOnClickListener(this);

        mAccount = new Account();
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        mLoginPresenter.submitLogin(mAccount);
    }

    @Override
    public void notifyLoginValid() {
        Toast.makeText(this, "Welcome....", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearLoginInput() {
        mUsername.getText().clear();
        mPassword.getText().clear();
    }

    @Override
    public void enableLoginButton() {
        if (!mLogin.isEnabled()) {
            mLogin.setEnabled(true);
            mLogin.setAlpha(1.0f);
        }
    }

    @Override
    public void disableLoginButton() {
        if (mLogin.isEnabled()) {
            mLogin.setEnabled(false);
            mLogin.setAlpha(0.5f);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        mAccount.setUsername(mUsername.getText().toString());
        mAccount.setPassword(mPassword.getText().toString());
        mLoginPresenter.loginInputTextChanged(mAccount);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void setLoginPresenter(LoginContract.Presenter loginPresenter) {
        this.mLoginPresenter = loginPresenter;
    }
}
