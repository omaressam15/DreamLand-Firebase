package com.omaressam.mydreamland.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.annotations.NotNull;
import com.omaressam.mydreamland.R;

import java.util.Objects;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private Context context;
    private GoogleMap map;
    private SupportMapFragment mapFragment;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        setUpMap();
        checkLocationPermission();
        return view;
    }



    private void checkLocationPermission() {
        int permission = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) requireContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION} ,1);
        }
    }

    public void onRequestPermissionsResult (int requestCode, @NonNull @NotNull String []permissions, @NonNull @NotNull int[]grantResults) {
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "permission_denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void setUpMap() {

        context = Objects.requireNonNull(requireActivity().getApplicationContext());

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.FragmentMap);

        assert mapFragment != null;

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        final LatLng latLng = new LatLng(29.970371, 31.037150);

        map.addMarker(new MarkerOptions().position(latLng).title("Dream Land"));


        final LatLng latLng2 = new LatLng(29.974537, 31.051417);

        map.addMarker(new MarkerOptions().position(latLng2).title("مسجد الستار"));



        final LatLng latLng3 = new LatLng(29.970567, 31.037792);

        map.addMarker(new MarkerOptions().position(latLng3).title("Misr Pharmacies"));



        final LatLng latLng4 = new LatLng(29.975676, 31.051405);

        map.addMarker(new MarkerOptions().position(latLng4).title("اStar Market"));




        final LatLng latLng5 = new LatLng(29.975156,31.050934);

        map.addMarker(new MarkerOptions().position(latLng5).title("Rania Pharmacy"));



       final LatLng latLng6= new LatLng(29.975347,31.051253);

        map.addMarker(new MarkerOptions().position(latLng6).title("Dream cafe"));



        final LatLng latLng7= new LatLng(29.975414,31.051692);

        map.addMarker(new MarkerOptions().position(latLng7).title("Freddy's cafe"));



       final LatLng latLng8= new LatLng(29.975568,31.051538);

        map.addMarker(new MarkerOptions().position(latLng8).title("TK BURGERS N FRIES"));



       final LatLng latLng9= new LatLng(29.975353, 31.051165);

        map.addMarker(new MarkerOptions().position(latLng9).title("Friend's laundry"));




        final LatLng latLng10= new LatLng(29.975706, 31.052878);

        map.addMarker(new MarkerOptions().position(latLng10).title("Dream International Schools"));




        final LatLng latLng11= new LatLng(29.967725, 31.052900);

        map.addMarker(new MarkerOptions().position(latLng11).title("مسجد التلاوي"));



        final LatLng latLng12= new LatLng(29.958395, 31.059681);

        map.addMarker(new MarkerOptions().position(latLng12).title("السعودي ماركت"));




        final LatLng latLng13= new LatLng(29.959896,31.059881 );

        map.addMarker(new MarkerOptions().position(latLng13).title("Cinema Dream"));




        final LatLng latLng14= new LatLng(29.961645,31.048736 );

        map.addMarker(new MarkerOptions().position(latLng14).title("Pegasus dreamland club"));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng14, 14));



        final LatLng latLng15= new LatLng(29.970364,31.038055 );

        map.addMarker(new MarkerOptions().position(latLng15).title("Al Bahga Market"));




       final LatLng latLng16= new LatLng(29.971094,31.037836);

        map.addMarker(new MarkerOptions().position(latLng16).title("مسجد المروة"));




        final LatLng latLng17= new LatLng(29.970207,31.037744);

        map.addMarker(new MarkerOptions().position(latLng17).title("مطعم زيت وليمون"));




        final LatLng latLng18= new LatLng(29.972750,31.038369);

        map.addMarker(new MarkerOptions().position(latLng18).title("Dream Hospital Emergency"));




        //  map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
     /*   Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            }
        }, 1000);*/
    }


}