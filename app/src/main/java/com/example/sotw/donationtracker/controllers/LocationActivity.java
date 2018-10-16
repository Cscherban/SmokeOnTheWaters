package com.example.sotw.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Location;

public class LocationActivity extends AppCompatActivity {

    private TextView name;
    private TextView address;
    private TextView latitude;
    private TextView longitude;
    private TextView type;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        this.name = (TextView) findViewById(R.id.name);
        this.address = (TextView) findViewById(R.id.address);
        this.latitude = (TextView) findViewById(R.id.latitude);
        this.longitude = (TextView) findViewById(R.id.longitude);
        this.type = (TextView) findViewById(R.id.type);
        this.phone = (TextView) findViewById(R.id.phone);

        //Location location = new Location("0","ATL Food Bank", "120 North Ave NW",
        //        869.111f, 404.345f, "Drop Off", "(404) 584 9989","site.com");
        Location location = LocationDetailActivity.getPublicLocation();
        populateFields(location);

    }

    public void populateFields(Location location) {
        this.name.setText(location.getName());
        this.address.setText(location.getAddress());
        this.latitude.setText(((Float) location.getLatitude()).toString());
        this.longitude.setText(((Float) location.getLongitude()).toString());
        this.type.setText(location.getType());
        this.phone.setText(location.getPhone());

    }
}
