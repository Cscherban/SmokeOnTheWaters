package com.example.sotw.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.DonationDropOff;

import org.w3c.dom.Text;

public class DonationActivity extends AppCompatActivity {

    private DonationDropOff donation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        donation = DonationListActivity.getPublicDonation();

        TextView dateTime = (TextView) findViewById(R.id.datetime);
        TextView locationName = (TextView) findViewById(R.id.locationName);
        TextView shortDesc = (TextView) findViewById(R.id.shortDesc);
        TextView longDesc = (TextView) findViewById(R.id.longDesc);
        TextView value = (TextView) findViewById(R.id.value);
        TextView category = (TextView) findViewById(R.id.category);

        dateTime.setText(donation.getTimestamp());
        locationName.setText(donation.getLocation().getName());
        shortDesc.setText(donation.getShortDescription());
        longDesc.setText(donation.getLongDescription());
        value.setText("" + donation.getValue());
        category.setText(donation.getCategory().getItem());

    }
}
