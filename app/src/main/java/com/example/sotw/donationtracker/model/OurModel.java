package com.example.sotw.donationtracker.model;

import java.util.ArrayList;
import java.util.List;

public class OurModel {
    //holds some static objects for us
    private static final OurModel instance = new OurModel();

    public static OurModel getInstance() { return instance; }

    private List<Location> locations;
    private List<DonationDropOff> donations;

    public OurModel() {
        this.locations = new ArrayList<Location>();
        this.donations = new ArrayList<DonationDropOff>();
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
