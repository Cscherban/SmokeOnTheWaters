package com.example.sotw.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;

public class RegisteredActivity extends AppCompatActivity {
    private TextView userType;
    private TextView name;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        String userTypestr = getIntent().getStringExtra("userType");
        String namestr = getIntent().getStringExtra("name");
       String emailstr = getIntent().getStringExtra("e-mail");

        name = findViewById(R.id.name);
       name.setText("Name: " + namestr);

       userType = findViewById(R.id.userType);
       userType.setText("User Type: " + userTypestr);

        email = findViewById(R.id.email);
        email.setText("E-mail: " + emailstr);


    }
}
