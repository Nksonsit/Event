package com.nestnfly.event.ui;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.nestnfly.event.R;
import com.nestnfly.event.helper.Functions;
import com.nestnfly.event.helper.PrefUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (PrefUtils.isFirstType(this)) {
            new CountDownTimer(2500, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    PrefUtils.setFirstTime(SplashActivity.this, false);
                    Functions.fireIntentWithClearFlag(SplashActivity.this, DashboardActivity.class, true);
                }
            }.start();
        } else {
            Functions.fireIntentWithClearFlag(SplashActivity.this, DashboardActivity.class, true);
        }
    }
}
