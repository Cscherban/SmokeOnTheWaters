package com.example.sotw.donationtracker.model;

/**
 * He is an Actor, blessed be his name. Also
 * The supertype for all of our bois
 */
public class Actor {
    private String name;
    private String email;
    private String password;
    private String userType = "Actor";
    private Location location;

    /**
     * Used for firebase
     */
    public Actor() {}

    /**
     * Constructor For actor with no location
     * @param name - name of the actor
     * @param id - id in the database
     * @param password - persons password
     * @param userType - their userType
     */
    public Actor(String name, String id, String password, String userType) {
        this(name, id, password, userType, null);
    }

    /**
     * Constructor for Actor with Location
     * @param name - name of person
     * @param id - id of person
     * @param password - their password
     * @param userType - their type
     * @param location - their location
     */
    public Actor(String name, String id, String password, String userType, Location location) {
        this.name = name;
        this.email = id;
        this.password = password;
        this.userType = userType;
        this.location = location;
    }

    /**
     * set the Password
     * @param password - to set
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     *
     * @param name that person is to have
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     *
     * @param email that person wants
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     *
     * @param type - set their type
     */
    public void setUserType(String type){
        this.userType = type;
    }

    /**
     *
     * @param location that the person belongs to
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return persons password
     */
    public String getPassword(){
        return password;
    }

    /**
     *
     * @return persons name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return persons email
     */
    public String getEmail(){
        return email;
    }

    /**
     *
     * @return the type of user
     */
    public String getUserType(){
        return this.userType;
    }

    /**
     *
     * @return the location object for the location they are at
     */
    public Location getLocation() {
        return location;
    }
}
