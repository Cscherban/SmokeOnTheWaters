package com.example.sotw.donationtracker.model;

import android.media.Image;

/**
 * You got Class. It is a Donation Class
 */
public class DonationDropOff {
    private String timestamp;
    private Location location;
    private String shortDescription;
    private String longDescription;
    private double value;
    private Category category;
    private String comments;
    private Image image;

    /**
     * Empty constructor for Firebase
     */
    private DonationDropOff(){

    }

    /**
     *
     * @param timestamp - time of donation
     * @param location - location for the donation
     * @param shortDescription - short description of donation
     * @param longDescription - description of donation
     * @param value - value of donation
     * @param category - category of donation
     * @param comments - comments from donor
     * @param image - image for the donation
     */
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

    /**
     * Constructor for Donation Dropoff
     * @param timestamp - time of donation
     * @param location - location for the donation
     * @param shortDescription - short description of donation
     * @param longDescription - description of donation
     * @param value - value of donation
     * @param category - category of donation
     */
    public DonationDropOff(String timestamp, Location location,
                    String shortDescription, String longDescription,
                    double value, Category category) {
        this(timestamp, location, shortDescription, longDescription, value, category,
                null, null);
    }

    /**
     *
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @return money value of donation
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @return the image of donation
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @return get timestamp of the donation
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @return get the location of donation
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @return the comments for it
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @return get the long description of donation
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     *
     * @return get the short description of donation
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     *
     * @param category - set the category of the boi
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *
     * @param comments - set comments for boi
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @param image - set the image of the boi
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     *
     * @param location - set the donation's location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @param longDescription - set the long description
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     *
     * @param shortDescription - set the short description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     *
     * @param timestamp - set the timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @param value - set the money value of the boi
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     *
     * @return a string of object values
     */
    @Override
    public String toString() {
        String ret = "";

        if(shortDescription == null && longDescription != null && longDescription.length() != 0){
            ret += longDescription;
        }else if (shortDescription == null || shortDescription.length() == 0){
            ret += "Unknown item";
        }else{
            ret += shortDescription;
        }
        ret += " found at ";

        if(location != null) {
            ret += location;
        }else{
            ret += "unspecified location";
        }
        ret += " at ";

        if (timestamp !=  null){
            ret += timestamp;
        }else{
            ret +="00:00:00 UTC";
        }

        ret += ". It is worth ";

        if (value >= 0){
            ret += "$" + value + " Dollars";
        }else{
            ret +="$0 Dollars";
        }
        ret += ". It is a";


        if (category == null){
            ret += " non-category";
        }else{
           char first_letter = category.toString().toLowerCase().charAt(0);

           if(first_letter == 'a' || first_letter == 'e'
                   ||first_letter == 'i' || first_letter == 'o'){
               ret += "n";
           }

           ret += " " + category;
        }


        return ret + ".";
    }
}
