package com.nestnfly.event.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nestnfly.event.R;
import com.nestnfly.event.custom.CustomInfoWindowGoogleMap;
import com.nestnfly.event.ui.BaseActivity;

@SuppressLint("ValidFragment")
public class HomeFragment extends BaseFragment {

    private GoogleMap mMap;

    public HomeFragment(BaseActivity activity) {
        setBaseActivity(activity);
    }

    public static BaseFragment getFragment(BaseActivity activity) {
        BaseFragment fragment = new HomeFragment(activity);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

}
