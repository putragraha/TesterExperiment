package com.android.nsystem.testapps;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View, TextWatcher {

    private EditText mUsername;
    private EditText mPassword;

    private Button mLogin;

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

        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        mLoginPresenter.submitLogin(
            new Account(
                mUsername.getText().toString(),
                mPassword.getText().toString()
            )
        );
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
        // No implementation
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        mLoginPresenter.loginInputTextChanged(
            new Account(
                mUsername.getText().toString(),
                mPassword.getText().toString()
            )
        );
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // No implementation
    }
}
