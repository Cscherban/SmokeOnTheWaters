package com.example.sotw.donationtracker.model;

import com.example.sotw.donationtracker.model.User;

/**
 * Location Employee Model Class
 */
public class LocationEmployee extends User {
    private String name;
    private String email;
    private String password;
    private String userType;
    private Location location;


    /**
     *
     * @param name of location employee
     * @param id of employee in db
     * @param password of person
     * @param userType - so we don't need reflections
     */
    public LocationEmployee(String name, String id, String password, String userType) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
    }

    /**
     *
     * @param name of location employee
     * @param id of employee in db
     * @param password of person
     * @param userType - so we don't need reflections
     * @param location of employee
     */
    public LocationEmployee(String name, String id,
                            String password, String userType,
                            Location location) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
        this.location = location;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
