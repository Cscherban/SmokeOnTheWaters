package com.example.sotw.donationtracker.controllers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Location;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LocationDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView address;
    private TextView latitude;
    private TextView longitude;
    private TextView type;
    private TextView phone;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference().child("locations");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        this.name = (TextView) findViewById(R.id.name);
        this.address = (TextView) findViewById(R.id.address);
        this.latitude = (TextView) findViewById(R.id.latitude);
        this.longitude = (TextView) findViewById(R.id.longitude);
        this.type = (TextView) findViewById(R.id.type);
        this.phone = (TextView) findViewById(R.id.phone);

        Location location = new Location("0","ATL Food Bank", "120 North Ave NW",
                869.111f, 404.345f, "Drop Off", "(404) 584 9989","site.com");



        //add callback to populate screen
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Create an arraylist of locations
                List<Location> locationsArrayList = new ArrayList<>();

                Log.d("Firebase","EnteredCallback:success");

                //get All datasnapshotobjects from the "locations" document(aka table)
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Location boi = postSnapshot.getValue(Location.class);
                    locationsArrayList.add(boi);
                }

                //populate fields with the locations
                populateFields(locationsArrayList);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        populateFields(location);

    }



    public void populateFields(List<Location> locations){
        //Do something
        Log.d("Firebase","PopulateViewInit:success");

    }

    public void populateFields(Location location) {
        this.name.setText(location.getName());
        this.address.setText(location.getAddress());
        this.latitude.setText(((Float) location.getLatitude()).toString());
        this.longitude.setText(((Float) location.getLongitude()).toString());
        this.type.setText(location.getType());
        this.phone.setText(location.getPhone());

    }
}
