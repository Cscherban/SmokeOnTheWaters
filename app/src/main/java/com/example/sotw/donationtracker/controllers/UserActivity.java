package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sotw.donationtracker.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button logout = findViewById(R.id.user_logout);
        Button search = findViewById(R.id.searchButton);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sign out:
                FirebaseAuth.getInstance().signOut();

                //switch activities
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //switch to search activity
                Intent searchAct = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(searchAct);

            }
        });
    }
}
