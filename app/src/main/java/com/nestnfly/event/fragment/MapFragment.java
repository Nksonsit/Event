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
public class MapFragment extends BaseFragment {

    private GoogleMap mMap;

    public MapFragment(BaseActivity activity) {
        setBaseActivity(activity);
    }

    public static BaseFragment getFragment(BaseActivity activity) {
        BaseFragment fragment = new MapFragment(activity);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initilizeMap();
        return view;
    }

    private void initilizeMap() {
        try {
            final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map_home);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
//                    mMap.setMinZoomPreference(MIN_ZOOM);
                    MapStyleOptions mapStyle = MapStyleOptions.loadRawResourceStyle(getBaseActivity(), R.raw.style_json);
                    boolean check = mMap.setMapStyle(mapStyle);

                    Log.e("check", check + "");

                    View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                    RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
// position on right bottom
                    rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                    rlp.setMargins(0, 300, 180, 180);


                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            return false;
                        }
                    });


                    LatLng latLng = new LatLng(43.6641907, -79.3458402);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    mMap.clear();
                    markerOptions.title("Current Position");
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));


                    CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(getBaseActivity());
                    mMap.setInfoWindowAdapter(customInfoWindow);

                    Marker m = mMap.addMarker(markerOptions);
                    m.showInfoWindow();

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
