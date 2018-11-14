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
    public void test_filterer_category_one_location() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "words", "category", "Other");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getCategory().getItem(), "Other");
    }

    @Test
    public void test_filterer_category_all_locations() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        DonationDropOff newDonation2 = new DonationDropOff("2:50", new Location("numbers"), "evenLess", "nothing", 20, Category.Other);
        donations.add(newDonation2);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "All", "category", "Other");
        assertEquals(filteredList.size(), 2);
        assertEquals(filteredList.get(0).getCategory().getItem(), "Other");
        assertEquals(filteredList.get(1).getCategory().getItem(), "Other");
    }

    @Test
    public void test_filterer_category_one_location_none_found() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "words", "category", "Hat");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "Nothing Found");
    }

    @Test
    public void test_filterer_category_all_locations_none_found() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        DonationDropOff newDonation2 = new DonationDropOff("2:50", new Location("numbers"), "evenLess", "nothing", 20, Category.Other);
        donations.add(newDonation2);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "All", "category", "Hat");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "Nothing Found");
    }

    @Test
    public void test_filterer_name_one_location() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "words", "name", "evenMore");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "evenMore");
    }

    @Test
    public void test_filterer_name_all_locations() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        DonationDropOff newDonation2 = new DonationDropOff("2:50", new Location("number"), "evenMore", "nothing", 20, Category.Hat);
        donations.add(newDonation2);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "All", "name", "evenMore");
        assertEquals(filteredList.size(), 2);
        assertEquals(filteredList.get(0).getShortDescription(), "evenMore");
        assertEquals(filteredList.get(1).getShortDescription(), "evenMore");
    }

    @Test
    public void test_filterer_name_one_location_none_found() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "words", "name", "wrong");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "Nothing Found");
    }

    @Test
    public void test_filterer_name_all_locations_none_found() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        DonationDropOff newDonation2 = new DonationDropOff("2:50", new Location("number"), "evenMore", "nothing", 20, Category.Hat);
        donations.add(newDonation2);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "searchForDonation", "All", "name", "lol");
        assertEquals(filteredList.get(0).getShortDescription(), "Nothing Found");

    }

    @Test
    public void test_filterer_failure() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "lol", "words", "name", "wrong");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "Search Failure");
    }

    @Test
    public void test_filterer_empty_strings() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "", "", "", "");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "Search Failure");
    }

    @Test
    public void test_filterer_null_strings() {
        List<DonationDropOff> donations = new ArrayList<>();
        DonationDropOff newDonation = new DonationDropOff("2:00", new Location("words"), "evenMore", "nothing", 20, Category.Other);
        donations.add(newDonation);
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, null, null, null, null);
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "Search Failure");
    }

    @Test
    public void test_filterer_empty_list() {
        List<DonationDropOff> donations = new ArrayList<>();
        ArrayList<DonationDropOff> filteredList = DonationListActivity.filterer(donations, "", "", "", "");
        assertEquals(filteredList.size(), 1);
        assertEquals(filteredList.get(0).getShortDescription(), "Search Failure");
    }
}
