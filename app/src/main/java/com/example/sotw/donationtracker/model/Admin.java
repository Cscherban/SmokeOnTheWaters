package com.example.sotw.donationtracker.model;

import com.example.sotw.donationtracker.model.Actor;

/**
 *
 */
public class Admin extends Actor {
    private String name;
    private String email;
    private String password;
    private String userType = "Admin";

    /**
     * Empty Method used by Firebase
     */
    public Admin(){
        //Required for a firebase thing called Data Snapshot
    }

    /**
     * Constructor for Admin
     * @param name - name of Person
     * @param id - id in db
     * @param password - their password
     * @param userType - the type as string
     */
    public Admin(String name, String id, String password, String userType) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
    }

    /**
     *
     * @return their email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return an Admin's name
     */
    @Override
    public String getName() { return name; }

    /**
     *
     * @return the password of the person
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return get their User Type as string
     */
    @Override
    public String getUserType() {
        return userType;
    }

    /**
     *
     * @param email that we want the person to have
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param name that we want them to have
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param password that we want them to be set to
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
