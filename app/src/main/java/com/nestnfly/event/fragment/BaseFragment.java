package com.nestnfly.event.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nestnfly.event.ui.BaseActivity;

//import com.nestnfly.event.ui.BaseActivity;


public class BaseFragment extends Fragment {
    private ProgressDialog dialog;
    protected View view;
    private BaseActivity baseActivity;

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public boolean onFragmentBackPress() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void showProgressDialog(boolean isCancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
//        dialog = new ProgressDialog(baseActivity);
        dialog.setMessage("Please wait..");
        dialog.setCancelable(isCancelable);
        dialog.show();
    }

    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
