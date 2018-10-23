package com.example.sotw.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.DonationDropOff;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.model.OurModel;

import java.util.ArrayList;
import java.util.List;

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
        Log.d("NickLog0", "Did it run?");
        donationList = (RecyclerView) findViewById(R.id.donations);
        Log.d("NickLog0.1", "why");
        listLayout = new LinearLayoutManager(this);
        Log.d("NickLog0.2", "android is agony");
        donationList.setLayoutManager(listLayout);
        Log.d("NickLog0.3", "layouts are agony");

        OurModel model = OurModel.getInstance();
        Log.d("NickLog0.4", "models are agony");
        List<DonationDropOff> donations = model.getDonations();
        Log.d("NickLog0.5", donations.get(0).getLocation().getName());
        String locationName = getIntent().getStringExtra("locale");
        Log.d("NickLog", locationName);
        filteredDonations = new ArrayList<>();
        for (int i = 0; i < donations.size(); i++) {
            if (donations.get(i).getLocation().getName().equals(locationName)) {
                filteredDonations.add(donations.get(i));
            }
        }
        Log.d("NickLog2", filteredDonations.get(0).getShortDescription());
        listAdapter = new MyAdapter(filteredDonations);
        donationList.setAdapter(listAdapter);

    }

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
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        public void onBindViewHolder(final ViewHolder holder, int position) {

            //final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mDonation = mDonations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
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
                    //intent.putExtra(CourseDetailFragment.ARG_COURSE_ID, holder.mLocation.getKey());
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

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.locationName);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}