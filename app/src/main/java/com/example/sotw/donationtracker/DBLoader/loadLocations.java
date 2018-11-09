package com.example.sotw.donationtracker.DBLoader;

import android.content.Context;
import android.os.Environment;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Location;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class loadLocations {
    private  static FirebaseAuth mAuth;     //Firebase Authorization object
    private  static DatabaseReference ref;

    private  static List<Location> parseCSV(Context context, String filename){
        List<Location> locations = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            InputStream is = context.getResources().openRawResource(R.raw.locationdata);

            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            br.readLine();
            line = br.readLine(); //read/ignore first linebr.readLine();
            while (line != null) {
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                String key = data[0];
                String name = data[1];
                String address = data[4] + " " + data[5] + " " + data[6] + " " + data[7];
                float latitude = Float.parseFloat(data[2]);
                float longitude= Float.parseFloat(data[3]);
                String type = data[8];
                String phone= data[9];
                String website = data[10];


                locations.add(new Location(key,name,address,latitude,longitude,type,phone,website));

                line = br.readLine();
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

    private static void putIntoFirebase(List<Location> locations){

        //Reference to the DB..to let us modify it
        DatabaseReference locationsRef = ref.child("locations");

        for(Location location : locations){
            //Firebase magic
            locationsRef.child(location.getKey()).setValue(location);
        }

    }

    public static void doSomething(Context context){
        ref = FirebaseDatabase.getInstance().getReference();


        //Absolute path, ugly yes. but idk any other way for now
        String location = Environment.getExternalStorageDirectory().getPath()
                                                + "/Download/locationdata.csv";
        List<Location> locations = parseCSV(context, location);
        putIntoFirebase(locations);

    }
}
