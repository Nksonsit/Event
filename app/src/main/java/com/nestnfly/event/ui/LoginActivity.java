package com.nestnfly.event.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.nestnfly.event.R;
import com.nestnfly.event.custom.TfButton;
import com.nestnfly.event.custom.TfEditText;
import com.nestnfly.event.custom.TfTextView;
import com.nestnfly.event.helper.Functions;
import com.nestnfly.event.helper.PrefUtils;

public class LoginActivity extends BaseActivity {

    private com.nestnfly.event.custom.TfEditText edtEmail;
    private com.nestnfly.event.custom.TfEditText edtPassword;
    private com.nestnfly.event.custom.TfButton btnLogin;
    private com.nestnfly.event.custom.TfTextView txtSignUp;
    private android.support.v7.widget.CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.cardView = (CardView) findViewById(R.id.cardView);
        this.txtSignUp = (TfTextView) findViewById(R.id.txtSignUp);
        this.btnLogin = (TfButton) findViewById(R.id.btnLogin);
        this.edtPassword = (TfEditText) findViewById(R.id.edtPassword);
        this.edtEmail = (TfEditText) findViewById(R.id.edtEmail);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.fireIntent(LoginActivity.this,RegisterActivity.class,true);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.setLoggedIn(LoginActivity.this, true);
                Functions.fireIntentWithClearFlag(LoginActivity.this, DashboardActivity.class, true);
            }
        });
    }
}
