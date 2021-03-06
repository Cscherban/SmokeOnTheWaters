package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
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

/**
 * Class that lets us search boi
 */
public class SearchActivity extends AppCompatActivity {


    private Spinner searchType;
    private Spinner category;
    private Spinner loc;
    private EditText itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button search;
        OurModel ourModel;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = findViewById(R.id.SearchButton);
        searchType = findViewById(R.id.TypeSpinner);
        category = findViewById(R.id.CategorySpinner);
        loc = findViewById(R.id.LocationSpinner);
        itemName = findViewById(R.id.editText);
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
        locs.add(0, new Location("All"));
        ArrayAdapter<Location> locAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locs);
        loc.setAdapter(locAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = (Location) loc.getSelectedItem();
                if ("Category".equals(searchType.getSelectedItem())) {
                    String cat = category.getSelectedItem().toString();

                    Intent categorySearch
                            = new Intent(getApplicationContext(), DonationListActivity.class);

                    categorySearch.putExtra("locale", "searchForDonation");
                    categorySearch.putExtra("location", location.getName());
                    categorySearch.putExtra("type", "category");
                    categorySearch.putExtra("searchName", cat);
                    startActivity(categorySearch);
                } else if ("Item Name".equals(searchType.getSelectedItem())) {
                    String item = itemName.getText().toString();

                    Intent nameSearch
                            = new Intent(getApplicationContext(), DonationListActivity.class);

                    nameSearch.putExtra("locale", "searchForDonation");
                    nameSearch.putExtra("location", location.getName());
                    nameSearch.putExtra("type", "name");
                    nameSearch.putExtra("searchName", item);
                    startActivity(nameSearch);
                }
            }
        });





    }
}
