package com.example.sotw.donationtracker.DBLoader;

import com.example.sotw.donationtracker.model.Location;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class loadLocations {

    static List<Location> parseCSV(String filename){
        List<Location> locations = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(filename));
            line = br.readLine(); //read/ignore first line
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                String name = data[1];
                String address = data[4] + " " + data[5] + " " + data[6] + " " + data[7];
                float latitude = Float.parseFloat(data[2]);
                float longitude= Float.parseFloat(data[3]);
                String type = data[8];
                String phone= data[9];
                String website = data[10];


                locations.add(new Location(name,address,latitude,longitude,type,phone));


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return locations;
    }

    static void putIntoFirebase(List<Location> locations){

        DatabaseReference ref; //Reference to the DB..to let us modify it
        ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationsRef = ref.child("locations");


        for(Location location : locations){
            //Firebase magic
            //locationsRef.child(location.getKey()).setValue(location);

            System.out.println(location);
        }

    }

    public static void main(String[] args){
        //Absolute path, ugly yes. but idk any other way for now
        String location = "/Users/scherban/Desktop/school/2340/SmokeOnTheWaters/SmokeOnTheWaters/app/src/main/java/com/example/sotw/donationtracker/DBLoader/LocationData.csv";
        List<Location> locations = parseCSV(location);
        putIntoFirebase(locations);

    }
}
