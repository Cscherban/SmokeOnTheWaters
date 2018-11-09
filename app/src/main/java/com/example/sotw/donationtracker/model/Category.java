package com.example.sotw.donationtracker.model;

public enum Category {
    Clothing("Clothing"), Hat("Hat"), Kitchen("Kitchen"), Electronics("Electronics"),
    Household("Household"), Other("Other");

    private final String item;

    Category(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
