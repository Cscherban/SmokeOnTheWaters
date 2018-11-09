package com.example.sotw.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Location;

/**
 * Activity to control what we see in activity
 */
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

        this.name = findViewById(R.id.name);
        this.address = findViewById(R.id.address);
        this.latitude = findViewById(R.id.latitude);
        this.longitude = findViewById(R.id.longitude);
        this.type = findViewById(R.id.type);
        this.phone = findViewById(R.id.phone);

        //Location location = new Location("0","ATL Food Bank", "120 North Ave NW",
        //        869.111f, 404.345f, "Drop Off", "(404) 584 9989","site.com");
        Location location = LocationDetailActivity.getPublicLocation();
        populateFields(location);

    }

    /**
     * populate the fields of a label
     * @param location - location
     */
    public void populateFields(Location location) {
        this.name.setText(location.getName());
        this.address.setText(location.getAddress());
        this.latitude.setText(((Float) location.getLatitude()).toString());
        this.longitude.setText(((Float) location.getLongitude()).toString());
        this.type.setText(location.getType());
        this.phone.setText(location.getPhone());

    }
}
