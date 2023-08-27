package com.example.electronicsapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ElectronicasSur extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronicas_sur);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Quito = new LatLng(-0.2885091, -78.537916);
        mMap.addMarker(new MarkerOptions().position(Quito).title("JT Electrónica"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Quito));

        LatLng Quito2 = new LatLng(-0.3318101, -78.5549808);
        mMap.addMarker(new MarkerOptions().position(Quito2).title("Tecni Electrico Sur"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Quito2));

        LatLng Quito3 = new LatLng(-0.2671202, -78.5271715);
        mMap.addMarker(new MarkerOptions().position(Quito3).title("Electrónica Del Sur"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Quito3));

    }
}
