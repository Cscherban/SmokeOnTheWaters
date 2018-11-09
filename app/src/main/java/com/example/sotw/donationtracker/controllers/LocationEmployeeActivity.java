package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.DonationDropOff;
import com.example.sotw.donationtracker.model.OurModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LocationEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_employee);
        final String locationName = getIntent().getStringExtra("locale");

        Button logout = findViewById(R.id.logoutLocation);
        Button showDon = findViewById(R.id.seeDonations);
        Button addDon = findViewById(R.id.addDonation);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sign out
                FirebaseAuth.getInstance().signOut();

                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });

        showDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeDonations = new Intent(getApplicationContext(), DonationListActivity.class);
                seeDonations.putExtra("locale", locationName);
                OurModel model = OurModel.getInstance();
                List<DonationDropOff> donations = model.getDonations();
                if (donations.size() != 0) {
                    startActivity(seeDonations);
                }
            }
        });

        addDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addDonation = new Intent(getApplicationContext(), AddDonationActivity.class);
                addDonation.putExtra("locale", locationName);
                startActivity(addDonation);
            }
        });
    }
}
