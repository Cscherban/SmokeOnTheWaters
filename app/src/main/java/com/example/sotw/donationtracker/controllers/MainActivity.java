package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sotw.donationtracker.DBLoader.loadLocations;
import com.example.sotw.donationtracker.R;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;
    private Button locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.button);
        registerButton = (Button) findViewById(R.id.button3);
        locationButton = (Button) findViewById(R.id.LocationButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext(), login.class);

                startActivity(loginIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(getApplicationContext(), registration.class);

                startActivity(registrationIntent);
            }
        });

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent locationIntent = new Intent(getApplicationContext(), LocationDetailActivity.class);

                startActivity(locationIntent);
            }
        });

        // /loadLocations.doSomething(this);

    }


}
