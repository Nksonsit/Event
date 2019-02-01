package com.nestnfly.event.ui;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.nestnfly.event.R;
import com.nestnfly.event.custom.TfButton;
import com.nestnfly.event.custom.TfEditText;
import com.nestnfly.event.custom.TfTextView;
import com.nestnfly.event.helper.Functions;
import com.nestnfly.event.helper.PrefUtils;

public class RegisterActivity extends BaseActivity {

    private TfEditText edtYourName;
    private TfEditText edtEmail;
    private TfEditText edtNumber;
    private TfEditText edtCompanyName;
    private TfEditText edtCompanyProfile;
    private TfEditText edtChallenge;
    private TfButton btnRegister;
    private TfTextView txtSignIn;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.cardView = (CardView) findViewById(R.id.cardView);
        this.txtSignIn = (TfTextView) findViewById(R.id.txtSignIn);
        this.btnRegister = (TfButton) findViewById(R.id.btnRegister);
        this.edtChallenge = (TfEditText) findViewById(R.id.edtChallenge);
        this.edtCompanyProfile = (TfEditText) findViewById(R.id.edtCompanyProfile);
        this.edtCompanyName = (TfEditText) findViewById(R.id.edtCompanyName);
        this.edtNumber = (TfEditText) findViewById(R.id.edtNumber);
        this.edtEmail = (TfEditText) findViewById(R.id.edtEmail);
        this.edtYourName = (TfEditText) findViewById(R.id.edtYourName);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.setLoggedIn(RegisterActivity.this, true);
                Functions.fireIntentWithClearFlag(RegisterActivity.this, DashboardActivity.class, true);
            }
        });
    }
}
