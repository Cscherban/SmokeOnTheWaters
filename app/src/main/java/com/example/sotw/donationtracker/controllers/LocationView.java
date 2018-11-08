package com.example.sotw.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;

public class LocationView extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_view);

        textView = findViewById(R.id.infoOnLocation);

        String address = getIntent().getStringExtra("address");
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String type = getIntent().getStringExtra("type");
        String website = getIntent().getStringExtra("website");

        String everything = address + "\n\n\n" + name + "\n\n\n" + "Phone: " + phone + "\n\n\n" + "Type: " + type + "\n\n\n" + "Website: " + website;
        textView.setText(everything);

    }
}
