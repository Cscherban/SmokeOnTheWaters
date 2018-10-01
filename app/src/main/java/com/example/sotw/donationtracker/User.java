package com.example.sotw.donationtracker;

import com.example.sotw.donationtracker.Actor;

public class User extends Actor {
    private String name;
    private String email;
    private String password;

    private String userType = "User";

    public User(){
        //Required for a firebase thing called Data Snapshot
    }

    public User(String name, String id, String password, String userType) {
        this.name = name;
        this.email = id;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() { return name; }

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
