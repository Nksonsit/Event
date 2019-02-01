package com.nestnfly.event.ui;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nestnfly.event.R;
import com.nestnfly.event.custom.MDToast;
import com.nestnfly.event.helper.Functions;
import com.nestnfly.event.helper.PrefUtils;

public class DashboardActivity extends BaseActivity {

    private View customNavView;
    private LinearLayout navView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private LinearLayout llHome;
    private LinearLayout llHost;
    private LinearLayout llSchedule;
    private LinearLayout llContactUs;
    private LinearLayout llRegister;
    private LinearLayout llLogin;
    private LinearLayout llMap;
    private LinearLayout llLoginView;
    private LinearLayout llCheckInView;
    private LinearLayout llCheckIn;
    private LinearLayout llLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (LinearLayout) findViewById(R.id.nav_view);
//        navView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth - 80, ViewGroup.LayoutParams.MATCH_PARENT));
        DrawerLayout.LayoutParams ll = new DrawerLayout.LayoutParams(screenWidth - 100, ViewGroup.LayoutParams.MATCH_PARENT);
        ll.gravity = Gravity.START;
        navView.setLayoutParams(ll);

        navView.removeAllViews();

        customNavView = LayoutInflater.from(this).inflate(R.layout.nav_drawer, null, false);

        navView.addView(customNavView);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        setupNavigationView();

        loadBottomUI(1);
    }

    private void setupNavigationView() {
        llHome = (LinearLayout) customNavView.findViewById(R.id.llHome);
        llHost = (LinearLayout) customNavView.findViewById(R.id.llHost);
        llSchedule = (LinearLayout) customNavView.findViewById(R.id.llSchedule);
        llContactUs = (LinearLayout) customNavView.findViewById(R.id.llContactUs);
        llRegister = (LinearLayout) customNavView.findViewById(R.id.llRegister);
        llLogin = (LinearLayout) customNavView.findViewById(R.id.llLogin);
        llMap = (LinearLayout) customNavView.findViewById(R.id.llMap);
        llLoginView = (LinearLayout) customNavView.findViewById(R.id.llLoginView);
        llCheckInView = (LinearLayout) customNavView.findViewById(R.id.llCheckInView);
        llCheckIn = (LinearLayout) customNavView.findViewById(R.id.llCheckIn);
        llLogout = (LinearLayout) customNavView.findViewById(R.id.llLogout);

        if (PrefUtils.isUserLoggedIn(this)) {
            llLoginView.setVisibility(View.GONE);
            llCheckInView.setVisibility(View.VISIBLE);
            llLogout.setVisibility(View.VISIBLE);
        } else {
            llLoginView.setVisibility(View.VISIBLE);
            llCheckInView.setVisibility(View.GONE);
            llLogout.setVisibility(View.GONE);
        }

        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.openYesNoDialog(DashboardActivity.this, "Are you sure you want to logout?", new Functions.OnDialogClick() {
                    @Override
                    public void OnClick(boolean yesNO) {
                        if (yesNO) {
                            Functions.logout(DashboardActivity.this);
                        }
                    }
                });
            }
        });

        llCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                Functions.showToast(DashboardActivity.this, "Successfully Check In", MDToast.TYPE_SUCCESS);
            }
        });

        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                if (CURRENT_FRAGMENT != 0) {
                    loadBottomUI(1);
                }
            }
        });

        llHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                if (CURRENT_FRAGMENT != 1)
                    loadBottomUI(2);
            }
        });

        llSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                if (CURRENT_FRAGMENT != 2)
                    loadBottomUI(3);
            }
        });

        llContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                if (CURRENT_FRAGMENT != 3)
                    loadBottomUI(4);
            }
        });
        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                Functions.fireIntent(DashboardActivity.this, LoginActivity.class, true);
            }
        });
        llRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                Functions.fireIntent(DashboardActivity.this, RegisterActivity.class, true);
            }
        });
        llMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.START);
                if (CURRENT_FRAGMENT != 4)
                    loadBottomUI(5);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
/*            isMyEVFragment = false;
            CURRENT_FRAGMENT = AppConstants.FIND_STATION;*/
            super.onBackPressed();
        }
    }
}
