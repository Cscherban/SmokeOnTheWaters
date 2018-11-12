package com.example.sotw.donationtracker.model;

import com.example.sotw.donationtracker.model.Actor;

/**
 * Branch manager model file
 */
public class BranchManager extends Actor {
    private String name;
    private String email;
    private String password;
    private String userType = "Branch Manager";

    /**
     * Constructor for firebase
     */
    public BranchManager(){
        //Required for a firebase thing called Data Snapshot
    }

    /**
     *
     * @param name of branch manager
     * @param id in DB
     * @param password of manager
     * @param userType get type without reflections
     */
    public BranchManager(String name, String id, String password, String userType) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
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

}
