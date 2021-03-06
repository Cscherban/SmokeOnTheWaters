package com.example.sotw.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Category;
import com.example.sotw.donationtracker.model.DonationDropOff;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.model.OurModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that lets you view things in list form for donations
 */
public class DonationListActivity extends AppCompatActivity {

    private List<DonationDropOff> donations;
    private RecyclerView donationList;
    private ArrayList<DonationDropOff> filteredDonations;
    private RecyclerView.Adapter listAdapter;
    private RecyclerView.LayoutManager listLayout;
    private static DonationDropOff publicDonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_list);
        donationList = findViewById(R.id.donations);
        listLayout = new LinearLayoutManager(this);
        donationList.setLayoutManager(listLayout);

        OurModel model = OurModel.getInstance();
        donations = model.getDonations();
        String locationName = getIntent().getStringExtra("locale");
        String newLocationName = getIntent().getStringExtra("location");
        String typeOfSearch = getIntent().getStringExtra("type");
        String searchName = getIntent().getStringExtra("searchName");
        filteredDonations = filterer(
                donations, locationName, newLocationName, typeOfSearch, searchName);

        listAdapter = new MyAdapter(filteredDonations);
        donationList.setAdapter(listAdapter);

    }

    /**
     * Filterer for displaying  a list of donations
     * @param donations the list of donations
     * @param locationName the check to make sure searching is wanted
     * @param newLocationName the name of the location, or all
     * @param typeOfSearch the type of search, category or name
     * @param searchName the category to search for, or the name to search for
     * @return an arraylist of the filtered items
     */
    public static ArrayList<DonationDropOff> filterer(List<DonationDropOff> donations,
                                                      String locationName, String newLocationName,
                                                      String typeOfSearch, String searchName) {
        ArrayList<DonationDropOff> filteredDonations = new ArrayList<>();
        if ("searchForDonation".equals(locationName)) {

            if ("All".equals(newLocationName)) {
                if ("category".equals(typeOfSearch)) {
                    for (int i = 0; i < donations.size(); i++) {
                        if (donations.get(i).getCategory().getItem().equals(searchName)) {
                            filteredDonations.add(donations.get(i));
                        }
                    }
                    if (filteredDonations.isEmpty()) {
                        filteredDonations.add(new DonationDropOff(null,
                                null, "Nothing Found",
                                null, 0, null));
                    }
                } else if ("name".equals(typeOfSearch)) {
                    for (int i = 0; i < donations.size(); i++) {
                        if (donations.get(i).getShortDescription().equals(searchName)) {
                            filteredDonations.add(donations.get(i));
                        }
                    }
                    if (filteredDonations.isEmpty()) {
                        filteredDonations.add(new DonationDropOff(null,
                                null, "Nothing Found",
                                null, 0, null));
                    }
                }
            } else {
                if ("category".equals(typeOfSearch)) {
                    for (int i = 0; i < donations.size(); i++) {
                        if (donations.get(i).getCategory().getItem().equals(searchName)
                                && donations.get(i).getLocation().getName().equals(newLocationName))
                        {
                            filteredDonations.add(donations.get(i));
                        }
                    }
                    if (filteredDonations.isEmpty()) {
                        filteredDonations.add(new DonationDropOff("",
                                new Location(""), "Nothing Found",
                                "", 0, Category.Other));
                    }
                } else {
                    for (int i = 0; i < donations.size(); i++) {
                        if (donations.get(i).getShortDescription().equals(searchName)
                                && donations.get(i).getLocation().getName().equals(newLocationName))
                        {
                            filteredDonations.add(donations.get(i));
                        }
                    }
                    if (filteredDonations.isEmpty()) {
                        filteredDonations.add(new DonationDropOff("", new Location(""),
                                "Nothing Found", "",
                                0, Category.Other));
                    }
                }
            }
        } else {
            filteredDonations.add(new DonationDropOff("", new Location(""),
                    "Search Failure", "",
                    0, Category.Other));
        }
        return filteredDonations;
    }

    /**
     *
     * @return the static variable
     */
    public static DonationDropOff getPublicDonation() {
        return publicDonation;
    }

    public class MyAdapter
            extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<DonationDropOff> mDonations;

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public MyAdapter(List<DonationDropOff> items) {
            mDonations = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.location_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            //final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list
            (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mDonation = mDonations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              text view and the string rep of a course in the other.
             */
            holder.mContentView.setText(mDonations.get(position).getShortDescription());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //on a phone, we need to change windows to the detail view
                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(context, DonationActivity.class);
                        /*
                            pass along the id of the course so we can retrieve the correct data in
                            the next window
                         */
                    //intent.putExtra(CourseDetailFragment.ARG_COURSE_ID,
                    // holder.mLocation.getKey());
                    //intent.putExtra("key",holder.mLocation.getKey());
                    publicDonation = holder.mDonation;
                    //now just display the new window
                    context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return mDonations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public DonationDropOff mDonation;

            /**
             * constructor for inner class
             * @param view that is the view used by the recycler
             */
            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = view.findViewById(R.id.locationName);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
