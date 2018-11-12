package com.example.sotw.donationtracker.model;

/**
 * Enum to represent donation categories
 */
public enum Category {
    Clothing("Clothing"), Hat("Hat"), Kitchen("Kitchen"), Electronics("Electronics"),
    Household("Household"), Other("Other");

    private final String item;

    /**
     *
     * @param item - the name of the item donated
     */
    Category(String item) {
        this.item = item;
    }

    /**
     *
     * @return the name of the item/category
     */
    public String getItem() {
        return item;
    }
}
