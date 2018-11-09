package com.example.sotw.donationtracker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds locations for us in a list and donations
 */
public class OurModel {
    //holds some static objects for us
    private static final OurModel instance = new OurModel();
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    /**
     * A singleton
     * @return an instance of the outmodel
     */
    public static OurModel getInstance() { return instance; }
    private boolean firstLoadDonations;

    private List<Location> locations;
    private List<DonationDropOff> donations;

    /**
     * Grabs all the locations from DB and puts them into list
     */
    private void setLocationsFromDB(){

        DatabaseReference ref = reference.child("locations");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Create an arraylist of locations

                Log.d("Firebase","EnteredCallback:success");

                //get All data snapshot objects from the "locations" document(aka table)
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

    /**
     * Grabs all the donations from DB and puts them into list
     */
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

                //get All data snapshot objects from the "locations" document(aka table)
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


    /**
     * Constructor for our model, initializes the lists
     */
    public OurModel() {
        this.locations = new ArrayList<>();
        this.donations = new ArrayList<>();
        firstLoadDonations = true;
        setDonationsFromDB();
        setLocationsFromDB();
    }

    /**
     *
     * @return the locations list
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     *
     * @param locations set the locations list
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    /**
     *
     * @return get the donations as list
     */
    public List<DonationDropOff> getDonations() {
        return donations;
    }

    /**
     *
     * @param donations set the donations list
     */
    public void setDonations(List<DonationDropOff> donations) {
        this.donations = donations;
    }
}
