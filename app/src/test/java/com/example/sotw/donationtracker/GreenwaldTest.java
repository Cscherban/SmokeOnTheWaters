package com.example.sotw.donationtracker;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.sotw.donationtracker.model.Category;
import com.example.sotw.donationtracker.model.DonationDropOff;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.controllers.DonationListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * unit test for the class
 */
public class GreenwaldTest {

    @Test
    public void test_filterer() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("word"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "word", "words", "category", "Other");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getCategory().getItem(), "Other");
    }
}
