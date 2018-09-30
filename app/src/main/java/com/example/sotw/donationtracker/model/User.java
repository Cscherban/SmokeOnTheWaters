package com.example.sotw.donationtracker.model;

public class User {
    private String name;
    private String email;
    private String password;

    //might have to change this to an enum later
    private String userType;

    public User(String name, String id, String password, String userType) {
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

    public void setUserType(String userType) {
        this.userType = userType;
    }


}
