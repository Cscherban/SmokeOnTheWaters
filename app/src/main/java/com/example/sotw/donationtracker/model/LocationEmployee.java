package com.example.sotw.donationtracker.model;

import com.example.sotw.donationtracker.model.User;

public class LocationEmployee extends User {
    private String name;
    private String email;
    private String password;
    private String userType = "Location Empolyee";
    private Location location;


    public LocationEmployee(String name, String id, String password, String userType) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
    }

    public LocationEmployee(String name, String id, String password, String userType, Location location) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
        this.location = location;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public Location getLocation() {
        return location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
