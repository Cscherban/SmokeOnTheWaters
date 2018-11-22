package com.example.sotw.donationtracker;


import com.example.sotw.donationtracker.controllers.MapsView;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class MitashUnitTest {

    //one match between list and tag
    @Test
    public void test_findCorrectLocation() {

        String tag = "Yeet";
        ArrayList<com.example.sotw.donationtracker.model.Location> locationArrayList = new ArrayList<>();
        com.example.sotw.donationtracker.model.Location loc = new com.example.sotw.donationtracker.model.Location("Generic Name");
        loc.setKey("Wassup");
        locationArrayList.add(loc);

        com.example.sotw.donationtracker.model.Location loc1 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc.setKey("Wassup1");
        locationArrayList.add(loc1);

        com.example.sotw.donationtracker.model.Location loc2 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc.setKey("Yeet");
        locationArrayList.add(loc2);

     //   Log.d("TAG", marker1.getTag().toString());

        //assertEquals(true, MapsView.findCorrectLocation(tag, locationArrayList) instanceof com.example.sotw.donationtracker.model.Location);
        assertEquals(tag, MapsView.findCorrectLocation(tag, locationArrayList).getKey());




    }

    //no matches between list and tag
    @Test
    public void test_findCorrectLocationNoValue() {

        ArrayList<com.example.sotw.donationtracker.model.Location> locationArrayList = new ArrayList<>();
        com.example.sotw.donationtracker.model.Location loc = new com.example.sotw.donationtracker.model.Location("Generic Name");
        loc.setKey("Wassup");
        locationArrayList.add(loc);

        com.example.sotw.donationtracker.model.Location loc1 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc.setKey("Wassup1");
        locationArrayList.add(loc1);

        com.example.sotw.donationtracker.model.Location loc2 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc.setKey("Yeet");
        locationArrayList.add(loc2);

        //   Log.d("TAG", marker1.getTag().toString());


        assertEquals(null, MapsView.findCorrectLocation("No match", locationArrayList));




    }

    //tag is null
    @Test
    public void nullTag() {
        ArrayList<com.example.sotw.donationtracker.model.Location> locationArrayList = new ArrayList<>();
        com.example.sotw.donationtracker.model.Location loc = new com.example.sotw.donationtracker.model.Location("Generic Name");
        loc.setKey("Wassup");
        locationArrayList.add(loc);

        com.example.sotw.donationtracker.model.Location loc1 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc.setKey("Wassup1");
        locationArrayList.add(loc1);

        com.example.sotw.donationtracker.model.Location loc2 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc.setKey("Yeet");
        locationArrayList.add(loc2);

        //   Log.d("TAG", marker1.getTag().toString());


        assertEquals(null, MapsView.findCorrectLocation(null, locationArrayList));

    }

    //list is null
    @Test
    public void nullList() {
        assertEquals(null, MapsView.findCorrectLocation("Tag", null));
    }


    //both arguments are null
    @Test
    public void nullArguments() {
        assertEquals(null, MapsView.findCorrectLocation(null, null));
    }


    //one null key in the list
    @Test
    public void nullKey() {
        String tag = "Yeet";
        ArrayList<com.example.sotw.donationtracker.model.Location> locationArrayList = new ArrayList<>();
        com.example.sotw.donationtracker.model.Location loc = new com.example.sotw.donationtracker.model.Location("Generic Name");
        loc.setKey("Wassup");
        locationArrayList.add(loc);

        com.example.sotw.donationtracker.model.Location loc1 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc1.setKey(null);
        locationArrayList.add(loc1);

        com.example.sotw.donationtracker.model.Location loc2 = new com.example.sotw.donationtracker.model.Location("Generic Name1");
        loc.setKey("Yeet");
        locationArrayList.add(loc2);

        //   Log.d("TAG", marker1.getTag().toString());

        assertEquals(true, MapsView.findCorrectLocation(tag, locationArrayList) instanceof com.example.sotw.donationtracker.model.Location);
        assertEquals(tag, MapsView.findCorrectLocation(tag, locationArrayList).getKey());

    }
}
