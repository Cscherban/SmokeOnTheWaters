package com.example.sotw.donationtracker.model;

/**
 * Object to represent the location
 */
public class Location {

    private String key;
    private String name;
    private String address;
    private float latitude;
    private float longitude;
    private String type;
    private String phone;
    private String website;


    /**
     * empty location constructor
     */
    public Location(){

    }

    /**
     * constructor when only a name is provided
     * @param name - name of object
     */
    public Location(String name) {
        this(null, name, null, 0.0f, 0.0f, null, null, null);
    }

    /**
     * Constructor of boi
     * @param key - key in db
     * @param name - name of location
     * @param address - address of location
     * @param latitude - latitude
     * @param longitude - longitude
     * @param type - type of location
     * @param phone - phone number of location
     * @param website - site at location
     */
    public Location(String key,String name, String address, float latitude,
                    float longitude, String type, String phone,String website) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.phone = phone;
        this.key = key;
        this.website = website;
    }

    /**
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address of the location
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude - latitude to set
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return get the longitude of the location
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude the longitude of location
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return get the type of the object thing
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type set the type of location
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return get the phone of location
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return the database id
     */
    public String getKey(){return this.key;}

    /**
     *
     * @param newKey new database key
     */
    public void setKey(String newKey){ this.key = newKey; }

    /**
     *
     * @param website of the location
     */
    public void setWebsite(String website){this.website = website;}

    /**
     *
     * @return the website of the guy
     */
    public String getWebsite(){return this.website;}


    /**
     *
     * @return the string value of this location
     */
    @Override
    public String toString() {
        if (key != null) {
            return key + ": " + name;
        } else {
            return name;
        }
    }
}
