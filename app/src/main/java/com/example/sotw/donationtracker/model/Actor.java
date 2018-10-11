package com.example.sotw.donationtracker.model;

public abstract class Actor {
    private String name;
    private String email;
    private String password;

    public Actor() {

    }

    public Actor(String name, String id, String password) {
        this.name = name;
        this.email = id;
        this.password = password;
    }

    public abstract void setPassword(String password);
    public abstract void setName(String name);
    public abstract void setEmail(String email);

    public abstract String getPassword();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getUserType();
}
