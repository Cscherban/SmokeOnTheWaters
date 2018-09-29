package com.example.sotw.donationtracker;

public class User {
    private String name;
    private String id;
    private String password;
    //might have to change this to an enum later
    private String userType;

    public User(String name, String id, String password, String userType) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.userType = userType;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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
