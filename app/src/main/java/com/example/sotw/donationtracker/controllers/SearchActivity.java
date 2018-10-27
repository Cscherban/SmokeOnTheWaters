package com.example.sotw.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.model.OurModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Button search;
    private Spinner searchType;
    private Spinner category;
    private Spinner loc;
    private EditText itemName;
    private OurModel ourModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = (Button) findViewById(R.id.SearchButton);
        searchType = (Spinner) findViewById(R.id.TypeSpinner);
        category = (Spinner) findViewById(R.id.CategorySpinner);
        loc = (Spinner) findViewById(R.id.LocationSpinner);
        itemName = (EditText) findViewById(R.id.editText);
        ourModel = new OurModel();

        String[] sTypes = new String[]{"Pick a search type", "Item Name", "Category"};
        ArrayAdapter<String> sTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sTypes);
        searchType.setAdapter(sTypeAdapter);


        String[] categories = new String[]{"Pick a category", "Clothing", "Hat",
                "Kitchen", "Electronics", "Household", "Other"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        category.setAdapter(catAdapter);


        List<Location> locs = ourModel.getLocations();
        locs.add(0, new Location("Add"));
        ArrayAdapter<Location> locAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locs);
        loc.setAdapter(locAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = (Location) loc.getSelectedItem();
                if (searchType.getSelectedItem().toString().equals("Category")) {
                    String cat = category.getSelectedItem().toString();

                } else if (searchType.getSelectedItem().toString().equals("Item Name")) {
                    String item = itemName.getText().toString();

                }
            }
        });





    }
}