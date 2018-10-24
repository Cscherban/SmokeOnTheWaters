package com.example.sotw.donationtracker.model;

public class Actor {
    private String name;
    private String email;
    private String password;
    private String userType = "Actor";
    private Location location;

    public Actor() {

    }

    public Actor(String name, String id, String password, String userType) {
        this(name, id, password, userType, null);
    }

    public Actor(String name, String id, String password, String userType, Location location) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
        this.location = location;
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

    public void setLocation(Location location) {
        this.location = location;
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

    public Location getLocation() {
        return location;
    }
}
