package com.example.sotw.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.example.sotw.donationtracker.model.Location;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LocationDetailActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference().child("locations");
    private List<Location> locationsArrayList = new ArrayList<>();
    private RecyclerView locationList;
    private RecyclerView.Adapter listAdapter;
    private RecyclerView.LayoutManager listLayout;
    private static Location publicLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        locationList = (RecyclerView) findViewById(R.id.view);
        locationList.setHasFixedSize(true);

        listLayout = new LinearLayoutManager(this);
        locationList.setLayoutManager(listLayout);

        //add callback to populate screen
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Create an arraylist of locations

                Log.d("Firebase","EnteredCallback:success");

                //get All datasnapshotobjects from the "locations" document(aka table)
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Location boi = postSnapshot.getValue(Location.class);
                    locationsArrayList.add(boi);
                }

                //populate fields with the locations
                //populateFields(locationsArrayList);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("No huge errors","got to this step");
        locationsArrayList.add(new Location("1","nick","address",867.4f,443.1f,"dropoff","8479512285","http"));
        locationsArrayList.add(new Location("2","spoof","fake",000.0f,000.0f,"notreal","9999999999","sqlinjection.com"));
        Log.d("array sizE", "" + locationsArrayList.size());
        listAdapter = new MyAdapter(locationsArrayList);
        locationList.setAdapter(listAdapter);
    }

    public static Location getPublicLocation() {
        return publicLocation;
    }

    public class MyAdapter
            extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Location> mLocations;

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public MyAdapter(List<Location> items) {
            mLocations = items;
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
            holder.mLocation = mLocations.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
            holder.mContentView.setText(mLocations.get(position).toString());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        //on a phone, we need to change windows to the detail view
                        Context context = v.getContext();
                        //create our new intent with the new screen (activity)
                        Intent intent = new Intent(context, LocationActivity.class);
                        /*
                            pass along the id of the course so we can retrieve the correct data in
                            the next window
                         */
                        //intent.putExtra(CourseDetailFragment.ARG_COURSE_ID, holder.mLocation.getKey());
                        //intent.putExtra("key",holder.mLocation.getKey());
                        publicLocation = holder.mLocation;
                        //now just display the new window
                        context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return mLocations.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public Location mLocation;

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
/**
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<Location> locations;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView theText;
            public MyViewHolder(TextView v) {
                super(v);
                theText = v;
            }
        }

        public MyAdapter(List<Location> locations) {
            this.locations = locations;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.location_content, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.theText.setText(locations.get(position).getName());
        }

        public int getItemCount() {
            return locations.size();
        }
    }

    public void populateFields(List<Location> locations){
        //Do something
        Log.d("Firebase","PopulateViewInit:success");

    }

*/
}