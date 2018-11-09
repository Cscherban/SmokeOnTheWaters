package com.example.sotw.donationtracker.model;

import com.example.sotw.donationtracker.model.Actor;

public class BranchManager extends Actor {
    private String name;
    private String email;
    private String password;
    private String userType = "Branch Manager";

    public BranchManager(){
        //Required for a firebase thing called Data Snapshot
    }

    public BranchManager(String name, String id, String password, String userType) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
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


    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
