package com.example.sotw.donationtracker.controllers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sotw.donationtracker.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MapsView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ArrayList<com.example.sotw.donationtracker.model.Location> locationList;

    private double latitude;
    private double longitude;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view);
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


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET},
                        10);
            } else {
                locationManager.requestLocationUpdates(android.location.LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                Location location = locationManager.getLastKnownLocation(android.location.LocationManager.NETWORK_PROVIDER);
                locationManager.removeUpdates(locationListener);
                if (location == null) {
                    Log.e("Maps", "Houston, we have a problem.");
                } else {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    // Add a marker in Sydney and move the camera
                    LatLng sydney = new LatLng(latitude, longitude);
                    Marker main = mMap.addMarker(new MarkerOptions().position(sydney).title("You are here"));
                    main.setTag("mainMarker");

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));

                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference();
                    locationList = new ArrayList<>();

                    reference.child("locations").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Iterable<DataSnapshot> data = dataSnapshot.getChildren();
                            for (DataSnapshot d : data) {
                                com.example.sotw.donationtracker.model.Location location = d.getValue(com.example.sotw.donationtracker.model.Location.class);
                                Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title(location.getName()));
                                marker.setTag(location.getKey());
                                locationList.add(location);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            String name = marker.getTag().toString();
                            for (com.example.sotw.donationtracker.model.Location location : locationList) {
                                if (location.getKey().equals(name)) {
                                    Intent locationObject = new Intent(getApplicationContext(), LocationView.class);
                                    locationObject.putExtra("address", location.getAddress());
                                    locationObject.putExtra("name", location.getName());
                                    locationObject.putExtra("phone", location.getPhone());
                                    locationObject.putExtra("type", location.getType());
                                    locationObject.putExtra("website", location.getWebsite());
                                    startActivity(locationObject);
                                }
                            }


                            return false;
                        }
                    });


                }
            }
        }
    }
}
