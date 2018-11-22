package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;

/**
 * Main activity that is seen upon login
 */
public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;
    private Button locationButton;
    private Button maps;
    private TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.button);
        registerButton = findViewById(R.id.button3);
        locationButton = findViewById(R.id.LocationButton);
        maps = findViewById(R.id.MapView);
        forgot = findViewById(R.id.forgotPassword);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PasswordReset.class));
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsView.class));
            }
        });

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
                Intent locationIntent =
                        new Intent(getApplicationContext(),
                                LocationDetailActivity.class);

                startActivity(locationIntent);
            }
        });

        // /loadLocations.doSomething(this);

    }


}
