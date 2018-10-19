package com.example.sotw.donationtracker.model;

import android.media.Image;

import java.time.LocalDateTime;

public class DonationDropOff {
    private String timestamp;
    private Location location;
    private String shortDescription;
    private String longDescription;
    private double value;
    private Category category;
    private String comments;
    private Image image;

    public DonationDropOff(String timestamp, Location location,
                    String shortDescription, String longDescription,
                    double value, Category category, String comments,
                    Image image) {
        this.timestamp = timestamp;
        this.location = location;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.comments = comments;
        this.image = image;
    }

    public DonationDropOff(String timestamp, Location location,
                    String shortDescription, String longDescription,
                    double value, Category category) {
        this(timestamp, location, shortDescription, longDescription, value, category,
                null, null);
    }

    public Category getCategory() {
        return category;
    }

    public double getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public String getComments() {
        return comments;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return shortDescription + "found at " + location + "at " + timestamp + ".It is worth " +
                value + " . It is a " + category;
    }
}
