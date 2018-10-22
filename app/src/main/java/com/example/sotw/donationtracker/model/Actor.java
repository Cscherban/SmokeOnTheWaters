package com.example.sotw.donationtracker.model;

public class Actor {
    private String name;
    private String email;
    private String password;
    private String userType = "Actor";

    public Actor() {

    }

    public Actor(String name, String id, String password, String userType) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public void setUserType(String type){
        this.userType = type;
    }

    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getUserType(){
        return this.userType;
    }
}
