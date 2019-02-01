package com.nestnfly.event.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nestnfly.event.R;
import com.nestnfly.event.ui.BaseActivity;

@SuppressLint("ValidFragment")
public class ContactUsFragment extends BaseFragment {

    public ContactUsFragment(BaseActivity activity) {
        setBaseActivity(activity);
    }

    public static BaseFragment getFragment(BaseActivity activity) {
        BaseFragment fragment = new ContactUsFragment(activity);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        return view;
    }
}
