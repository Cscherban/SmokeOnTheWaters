package com.example.sotw.donationtracker;

import com.example.sotw.donationtracker.model.DonationDropOff;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.model.Category;


import org.junit.Test;

import static org.junit.Assert.*;

public class ScherbanTest {

    @Test
    public void null_everything(){
        DonationDropOff donationDropOff = new DonationDropOff(null, null,
                null, null, -1, null);
        String predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());

    }

    @Test
    public void test_locations(){
        Location locOnlyName = new Location("Name");

        DonationDropOff donationDropOff = new DonationDropOff(null, locOnlyName,
                null, null, -1, null);
        String predictedToString = "Unknown item found at Name at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());

        Location fullLocation = new Location("RealKey", "name",
                "Address 123 St", (float)12.1,
                (float)13.2, "type", "218-999-9999","www.website.com");

        donationDropOff = new DonationDropOff(null, fullLocation,
                null, null, -1, null);
        predictedToString = "Unknown item found at RealKey: name at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());



    }

    @Test
    public void test_value() {
        DonationDropOff donationDropOff = new DonationDropOff(null, null,
                null, null, -1, null);
        String predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());


        donationDropOff = new DonationDropOff(null, null,
                null, null, 0, null);
        predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0.0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());



        donationDropOff = new DonationDropOff(null, null,
                null, null, 10, null);
        predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $10.0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());


        donationDropOff = new DonationDropOff(null, null,
                null, null, 10.64, null);
        predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $10.64 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());
    }

    @Test
    public void test_vowel_cat(){



        Category cat = Category.Other;
        DonationDropOff donationDropOff = new DonationDropOff(null, null,
                null, null, -1, cat);
        String predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is an Other.";

        assertEquals(predictedToString, donationDropOff.toString());
    }

    @Test
    public void test_consonant_cat(){
        Category cat = Category.Clothing;
        DonationDropOff donationDropOff = new DonationDropOff(null, null,
                null, null, -1, cat);
        String predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a Clothing.";

        assertEquals(predictedToString, donationDropOff.toString());
    }

    @Test
    public void test_descriptions(){


        DonationDropOff donationDropOff = new DonationDropOff(null, null,
                null, "", -1, null);

        String predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());

        //Test everything at zero
        donationDropOff = new DonationDropOff(null, null,
                "", "", -1, null);
        predictedToString = "Unknown item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());

        //Swapping long description for the short one
        donationDropOff = new DonationDropOff(null, null,
                null, "Not Null Long", -1, null);
        predictedToString = "Not Null Long found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());

        //Both are valid
        donationDropOff = new DonationDropOff(null, null,
                "I'm an item", "Not Null Long", -1, null);

        predictedToString = "I'm an item found at unspecified location at 00:00:00 UTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());




    }

    @Test
    public void test_timestamp(){
        DonationDropOff donationDropOff = new DonationDropOff("123:123:123 GTC", null,
                null, null, -1, null);
        String predictedToString = "Unknown item found at unspecified location at 123:123:123 GTC. " +
                "It is worth $0 Dollars. It is a non-category.";

        assertEquals(predictedToString, donationDropOff.toString());

    }




}
