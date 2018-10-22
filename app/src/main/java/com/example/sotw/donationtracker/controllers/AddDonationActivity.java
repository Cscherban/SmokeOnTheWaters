package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Category;
import com.example.sotw.donationtracker.model.DonationDropOff;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.model.OurModel;

import java.util.ArrayList;
import java.util.List;

public class AddDonationActivity extends AppCompatActivity {
    private  static FirebaseAuth mAuth;     //Firebase Autherization object
    private  static DatabaseReference ref =  FirebaseDatabase.getInstance().getReference();

    private Spinner categories;

    private void addDonationToDB(DonationDropOff donation){


        //get the key for locaiton in the db
        String donationTimestamp = donation.getTimestamp();

        //get a reference to where we want to write( which is making the donation a child of each location
        DatabaseReference donationsRef = ref.child("donations");
        donationsRef.child(donationTimestamp).setValue(donation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        Button addDonation = (Button) findViewById(R.id.add);
        final String locationName = getIntent().getStringExtra("locale");

        categories = (Spinner) findViewById(R.id.categories);

        String[] categs = new String[]{Category.Clothing.getItem(), Category.Electronics.getItem()
        , Category.Hat.getItem(), Category.Household.getItem(), Category.Kitchen.getItem(),
        Category.Other.getItem()};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categs);

        categories.setAdapter(adapter);

        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText shortDesc = (EditText) findViewById(R.id.editShort);
                EditText longDesc = (EditText) findViewById(R.id.editLong);
                EditText comments = (EditText) findViewById(R.id.editComments);
                EditText date = (EditText) findViewById(R.id.editDate);
                EditText time = (EditText) findViewById(R.id.editTime);
                EditText value = (EditText) findViewById(R.id.editValue);

                Location location = new Location(locationName);
                String dateString = date.getText().toString();
                String timeString = time.getText().toString();
                String dateTime = dateString + ", " + timeString;
                String shortDescription = shortDesc.getText().toString();
                String longDescription = longDesc.getText().toString();

                String categName = categories.getSelectedItem().toString();
                Category[] categoryTypes = Category.values();
                Category donationCategory = null;
                for (int i = 0; i < categoryTypes.length; i++) {
                    if (categName.equals(categoryTypes[i].getItem())) {
                        donationCategory = categoryTypes[i];
                    }
                }

                String valueString = value.getText().toString();
                double val = Double.parseDouble(valueString);


                DonationDropOff donation = new DonationDropOff(dateTime, location, shortDescription, longDescription, val, donationCategory);
                addDonationToDB(donation);

                OurModel model = OurModel.getInstance();
                List<DonationDropOff> donationList = model.getDonations();
                donationList.add(donation);
                Log.d("Addingstuff", donationList.get(0).getLocation().getName());
                model.setDonations(donationList);

                Intent goBack = new Intent(getApplicationContext(), LocationEmployeeActivity.class);
                goBack.putExtra("locale", locationName);
                startActivity(goBack);
            }
        });
    }
}
