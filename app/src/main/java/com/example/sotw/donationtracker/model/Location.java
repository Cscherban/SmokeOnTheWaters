package com.example.sotw.donationtracker.model;

public class Location {

    private String key;
    private String name;
    private String address;
    private float latitude;
    private float longitude;
    private String type;
    private String phone;
    private String website;

    public Location(String key, String name, String address, float latitude, float longitude, String type, String phone, String website) {
        this.key = key;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Latitude: " + latitude + ", Longitude: " + longitude
                + ", Type: " + type + ", Phone Number: " + phone + ".";
    }
}
