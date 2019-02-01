package com.nestnfly.event.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.nestnfly.event.R;
import com.nestnfly.event.custom.TfTextView;
import com.nestnfly.event.fragment.BaseFragment;
import com.nestnfly.event.fragment.ContactUsFragment;
import com.nestnfly.event.fragment.HomeFragment;
import com.nestnfly.event.fragment.HostFragment;
import com.nestnfly.event.fragment.MapFragment;
import com.nestnfly.event.fragment.ScheduleFragment;
import com.nestnfly.event.helper.ProgressBarHelper;

import java.util.Stack;

public class BaseActivity extends AppCompatActivity {
    public int screenWidth, screenHeight;
    private Stack<Fragment> fragmentBackStack;
    private boolean showBackMessage = false;
    private boolean doubleBackToExitPressedOnce;
    private ProgressDialog dialog;
    public static int CALL_PERMISSION_REQUEST_CODE = 1225;
    public static int Location_PERMISSION_REQUEST_CODE = 1210;
    public int CURRENT_FRAGMENT = 0;
    private ProgressBarHelper progressBarHelper;
    public boolean isDialogOpen = false;

    public void setShowBackMessage(boolean showBackMessage) {
        this.showBackMessage = showBackMessage;
    }


    public Stack<Fragment> getFragments() {
        return fragmentBackStack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentBackStack = new Stack<>();
        getWidthAndHeight();

        progressBarHelper = new ProgressBarHelper(BaseActivity.this);
    }

    public void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    public synchronized void pushAddFragments(Fragment fragment) {
        try {
            if (fragment != null) {
                fragmentBackStack.push(fragment);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();

                ft.replace(R.id.container, fragment, String.valueOf(fragmentBackStack.size()));
                ft.commit();
                manager.executePendingTransactions();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void popFragments(boolean shouldAnimate) {
        Fragment fragment = null;
        if (fragmentBackStack.size() > 1) {
            fragment = fragmentBackStack.elementAt(fragmentBackStack.size() - 2);
        } else if (!fragmentBackStack.isEmpty()) {
            fragment = fragmentBackStack.elementAt(fragmentBackStack.size() - 1);
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (fragment != null) {
            if (fragment.isAdded()) {
                ft.remove(fragmentBackStack.elementAt(fragmentBackStack.size() - 1));
                if (fragmentBackStack.size() > 1) {
                    ft.show(fragment).commit();
                }
            } else {
                ft.replace(R.id.container, fragment).commit();
            }
            fragmentBackStack.pop();
            manager.executePendingTransactions();
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentBackStack.size() <= 1) {
//            if (showBackMessage) {
//                doubleTapOnBackPress();
//            } else {
            finish();
//            }
        } else {
            if (!((BaseFragment) fragmentBackStack.get(fragmentBackStack.size() - 1)).onFragmentBackPress()) {
                Fragment currentFragment = fragmentBackStack.get(fragmentBackStack.size() - 1);
                if (currentFragment instanceof HomeFragment) {
//                    doubleTapOnBackPress();
                    finish();
                } else {
                    popFragments(false);
                    if (fragmentBackStack.get(fragmentBackStack.size() - 1) instanceof HomeFragment) {
                        loadBottomUI(1);
                    } else if (fragmentBackStack.get(fragmentBackStack.size() - 1) instanceof HostFragment) {
                        loadBottomUI(1);
                    } else if (fragmentBackStack.get(fragmentBackStack.size() - 1) instanceof ScheduleFragment) {
                        loadBottomUI(1);
                    } else if (fragmentBackStack.get(fragmentBackStack.size() - 1) instanceof ContactUsFragment) {
                        loadBottomUI(1);
                    }

                }

            }
        }
    }

    void doubleTapOnBackPress() {
        if (doubleBackToExitPressedOnce) {
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 10000);
    }

    public void showProgressDialog(boolean isCancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait..");
        dialog.setCancelable(isCancelable);
        dialog.show();
    }

    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * this method load dashboard fragment
     */
    /*public void loadHomeFragment() {
        ((TfTextView) findViewById(R.id.txtCurrentLocation)).setText(Functions.getLocationOfaddressHeader(context, Preferences.getInstance(context).getString(Preferences.KEY_LATITUDE),Preferences.getInstance(context).getString(Preferences.KEY_LONGITUDE)));
        isVisibleLocation(true);
        isVisibleChangePassword(false);
        isVisibleSearch(true);
        isVisibleHeaderImage(false);
        setHeaderFRomBaseActivity(getString(R.string.app_name));
        getFragments().clear();
        Fragment fragmentToPush = DashBoardFragment.getFragment(this);
        pushAddFragments(fragmentToPush, true, true);
    }*/
    public void loadBottomUI(int selectedPosition) {
        switch (selectedPosition) {
            case 1:
                CURRENT_FRAGMENT = 0;
                ((TfTextView) findViewById(R.id.txtTitle)).setText("Nest N Fly");
                pushAddFragments(new HomeFragment(BaseActivity.this));
                break;
            case 2:
                ((TfTextView) findViewById(R.id.txtTitle)).setText("Host");
                CURRENT_FRAGMENT = 1;
                pushAddFragments(new HostFragment(BaseActivity.this));
                break;
            case 3:
                CURRENT_FRAGMENT = 2;
                ((TfTextView) findViewById(R.id.txtTitle)).setText("Schedule");
                pushAddFragments(new ScheduleFragment(BaseActivity.this));
                break;
            case 4:
                ((TfTextView) findViewById(R.id.txtTitle)).setText("Contact Us");
                CURRENT_FRAGMENT = 3;
                pushAddFragments(new ContactUsFragment(BaseActivity.this));
                break;
            case 5:
                ((TfTextView) findViewById(R.id.txtTitle)).setText("Location");
                CURRENT_FRAGMENT = 4;
                pushAddFragments(new MapFragment(BaseActivity.this));
                break;

        }
    }

    public void requestFocus(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void hideKeyBoard(EditText editText) {
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!getFragments().empty()) {
            Fragment fragment = getFragments().get(getFragments().size() - 1);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }


    /*public void hideBackBtn() {
        ((ImageView) findViewById(R.id.imgBack)).setVisibility(View.GONE);
    }

    public void showBackBtn() {
        ((ImageView) findViewById(R.id.imgBack)).setVisibility(View.VISIBLE);
    }

    public void setHeaderTitle(String title) {
        ((TfTextView) findViewById(R.id.txtTitle)).setText(title);
    }*/


    public void showProgress() {

        progressBarHelper.showProgressDialog();
    }


    public void closeProgress() {

        progressBarHelper.hidProgressDialog();
    }
}
