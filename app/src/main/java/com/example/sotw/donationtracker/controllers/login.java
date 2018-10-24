package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.controllers.Account;
import com.example.sotw.donationtracker.model.Actor;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.model.LocationEmployee;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.support.annotation.NonNull;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference ref; //Reference to the DB..to let us modify it



    /**
     * This is what success looks like:
     * @param user - the user object returned by firebase
     */
    private void success(FirebaseUser user){
        //Do Stuff with the firebase user
        String uid = user.getUid();
        DatabaseReference users = ref.child("users").child(uid);

        Log.d("Firebase",uid);

        users.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Create an arraylist of locations

                Log.d("Firebase","EnteredCallback:success");

                //get All datasnapshotobjects from the "locations" document(aka table)

                Actor actor = dataSnapshot.getValue(Actor.class);
                Log.d("Firebase",actor.getClass().toString());

                Log.d("ACOTOAIHFDHSFAKHGS",actor.toString());

                if((actor).getUserType().equals("Location Employee")){
                    Intent LocationEmployeeScreen = new Intent(getApplicationContext(), LocationEmployeeActivity.class);
                    LocationEmployeeScreen.putExtra("locale", actor.getLocation().getName());
                    startActivity(LocationEmployeeScreen);
                }else{
                    Intent nextScreen = new Intent(getApplicationContext(), Account.class);
                    startActivity(nextScreen);
                }
                //populate fields with the locations
                //populateFields(locationsArrayList);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        //

    }

    /**
     * Function to handle login failure
     */
    private void failure(){

        TextView status = (TextView) findViewById(R.id.status);
        status.setText("Incorrect username or password");

    }

    private void attemptLogin(String email, String pass){
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success Message", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            success(user);
                        }else {
                            failure();
                        }

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();


        Button loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                Intent nextScreen = new Intent(getApplicationContext(), Account.class);
                String email = username.getText().toString();
                String pass = password.getText().toString();
                if (email.equals("") && pass.equals("")) {
                    failure();
                } else {
                    attemptLogin(email, pass);
                }
            }
        });
    }

    //TODO write an onStart to check if the user already logged in

}
