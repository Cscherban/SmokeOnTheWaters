package com.example.sotw.donationtracker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sotw.donationtracker.controllers.LocationDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OurModel {
    //holds some static objects for us
    private static final OurModel instance = new OurModel();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    public static OurModel getInstance() { return instance; }
    private boolean firstLoadDonations;

    private List<Location> locations;
    private List<DonationDropOff> donations;

    private void setLocationsFromDB(){

        DatabaseReference ref = reference.child("locations");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Create an arraylist of locations

                Log.d("Firebase","EnteredCallback:success");

                //get All datasnapshotobjects from the "locations" document(aka table)
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Location boi = postSnapshot.getValue(Location.class);
                    locations.add(boi);
                }

                Log.d("array sizE", "" + locations.size());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setDonationsFromDB(){

        DatabaseReference ref = reference.child("donations");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Create an arraylist of locations
                if(!firstLoadDonations){
                    return;
                }

                Log.d("Firebase","EnteredCallback:success");

                //get All datasnapshotobjects from the "locations" document(aka table)
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    DonationDropOff boi = postSnapshot.getValue(DonationDropOff.class);
                    donations.add(boi);
                }
                firstLoadDonations = false;

                Log.d("array sizE", "" + donations.size());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public OurModel() {
        this.locations = new ArrayList<Location>();
        this.donations = new ArrayList<DonationDropOff>();
        firstLoadDonations = true;
        setDonationsFromDB();
        setLocationsFromDB();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<DonationDropOff> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationDropOff> donations) {
        this.donations = donations;
    }
}
