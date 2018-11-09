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

import java.util.List;

/**
 * Activity to Add Donation
 */
public class AddDonationActivity extends AppCompatActivity {
    private  static FirebaseAuth mAuth;     //Firebase Authorization object
    private  static final DatabaseReference ref =  FirebaseDatabase.getInstance().getReference();

    private Spinner categories;

    private void addDonationToDB(DonationDropOff donation){


        //get the key for location in the db
        String donationTimestamp = donation.getTimestamp();

        //get a reference to where we want to write
        // ( which is making the donation a child of each location)
        DatabaseReference donationsRef = ref.child("donations");
        donationsRef.child(donationTimestamp).setValue(donation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        Button addDonation = findViewById(R.id.add);
        final String locationName = getIntent().getStringExtra("locale");

        categories = findViewById(R.id.categories);

        String[] categs = new String[]{Category.Clothing.getItem(), Category.Electronics.getItem()
        , Category.Hat.getItem(), Category.Household.getItem(), Category.Kitchen.getItem(),
        Category.Other.getItem()};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categs);

        categories.setAdapter(adapter);

        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText shortDesc = findViewById(R.id.editShort);
                EditText longDesc = findViewById(R.id.editLong);
                EditText comments = findViewById(R.id.editComments);
                EditText date = findViewById(R.id.editDate);
                EditText time = findViewById(R.id.editTime);
                EditText value = findViewById(R.id.editValue);

                Location location = new Location(locationName);
                String dateString = date.getText().toString();
                String timeString = time.getText().toString();
                String dateTime = dateString + ", " + timeString;
                String shortDescription = shortDesc.getText().toString();
                String longDescription = longDesc.getText().toString();

                String categName = categories.getSelectedItem().toString();
                Category[] categoryTypes = Category.values();
                Category donationCategory = null;
                for (Category cat : categoryTypes) {
                    if (categName.equals(cat.getItem())) {
                        donationCategory = cat;
                    }
                }

                String valueString = value.getText().toString();
                double val = Double.parseDouble(valueString);


                DonationDropOff donation =
                        new DonationDropOff(dateTime, location, shortDescription,
                                            longDescription, val, donationCategory);
                addDonationToDB(donation);

                OurModel model = OurModel.getInstance();
                List<DonationDropOff> donationList = model.getDonations();
                donationList.add(donation);
                Log.d("Adding Stuff", donationList.get(0).getLocation().getName());
                model.setDonations(donationList);

                Intent goBack = new Intent(getApplicationContext(), LocationEmployeeActivity.class);
                goBack.putExtra("locale", locationName);
                startActivity(goBack);
            }
        });
    }
}
