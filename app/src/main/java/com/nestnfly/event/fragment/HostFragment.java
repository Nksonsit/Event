package com.nestnfly.event.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nestnfly.event.R;
import com.nestnfly.event.helper.Functions;
import com.nestnfly.event.ui.BaseActivity;

@SuppressLint("ValidFragment")
public class HostFragment extends BaseFragment {

    public HostFragment(BaseActivity activity) {
        setBaseActivity(activity);
    }

    public static BaseFragment getFragment(BaseActivity activity) {
        BaseFragment fragment = new HostFragment(activity);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_host, container, false);
        ImageView img1 = (ImageView) view.findViewById(R.id.img1);
        ImageView img2 = (ImageView) view.findViewById(R.id.img2);
        ImageView img3 = (ImageView) view.findViewById(R.id.img3);
        ImageView img4 = (ImageView) view.findViewById(R.id.img4);
        ImageView img5 = (ImageView) view.findViewById(R.id.img5);

        Functions.loadCircularImage(getBaseActivity(),R.drawable.img1,img1,null);
        Functions.loadCircularImage(getBaseActivity(),R.drawable.img2,img2,null);
        Functions.loadCircularImage(getBaseActivity(),R.drawable.img3,img3,null);
        Functions.loadCircularImage(getBaseActivity(),R.drawable.img4,img4,null);
        Functions.loadCircularImage(getBaseActivity(),R.drawable.img5,img5,null);

        return view;
    }
}
