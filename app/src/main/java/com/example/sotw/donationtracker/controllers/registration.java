package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.annotation.NonNull;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Location;
import com.example.sotw.donationtracker.model.LocationEmployee;
import com.example.sotw.donationtracker.model.OurModel;
import com.example.sotw.donationtracker.model.User;
import com.example.sotw.donationtracker.model.Actor;
import com.example.sotw.donationtracker.model.Admin;
import com.example.sotw.donationtracker.model.BranchManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class registration extends AppCompatActivity {
    /**
     * UI elements
     */
    private EditText name;
    private EditText email;
    private EditText password;
    private Spinner spinner;
    private Spinner locationEmp;

    /**
     * Useful objects
     */
    public Actor user;              //User Object
    private FirebaseAuth mAuth;     //Firebase Authorization object
    private DatabaseReference ref; //Reference to the DB..to let us modify it
    private OurModel ourModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //grab the DB reference
        ref = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);
        locationEmp = findViewById(R.id.locEmpSpinner);
        ourModel = new OurModel();



        List<Location> locations = ourModel.getLocations();
        locations.add(0, new Location("Pick a Location"));
        ArrayAdapter<Location> locationArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locations);
        locationEmp.setAdapter(locationArrayAdapter);
        locationEmp.setVisibility(View.GONE);


        String[] spinnerValues = new String[]{"Pick a type of user", "User", "Location Employee", "Branch Manager", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerValues);
        spinner.setAdapter(adapter);


        Button registerButton = findViewById(R.id.button4);
        Button cancelButton = findViewById((R.id.button2));
        mAuth = FirebaseAuth.getInstance();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (spinner.getSelectedItem().toString().equals("Location Employee")) {
                    locationEmp.setVisibility(View.VISIBLE);
                } else {
                    locationEmp.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancelIntent);

            }

        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // A good principle in software engineering is the error ladder
                //Just check for errors first/incorrect inputs, and then do things

                //Grab the textview, its not expensive


                TextView failed = findViewById(R.id.failed);
                if(invalidName(name.getText().toString())){
                    failed.setText("Please Enter a Name");

                }else if(invalidEmail(email.getText().toString())){
                    failed.setText("Please Enter an Email");

                }else if(invalidPassword(password.getText().toString())) {
                    failed.setText("Please Enter a valid password");

                }else if(spinner.getSelectedItem().toString().equals("Pick a type of user")){
                    failed.setText("Please Enter a value into the Spinner");
                } else if (spinner.getSelectedItem().toString().equals("Location Employee")
                        && locationEmp.getSelectedItem().toString().equals("Pick a Location")) {
                    failed.setText("Location Employees must have a valid location");
                } else{

                    if (spinner.getSelectedItem().equals("User")) {
                        user = new User(name.getText().toString(), email.getText().toString(),
                                password.getText().toString(), spinner.getSelectedItem().toString());

                        Log.d("Success", "CreatedUser:success");

                    } else if (spinner.getSelectedItem().equals("Location Employee")) {

                            user = new LocationEmployee(name.getText().toString(), email.getText().toString(),
                                    password.getText().toString(), spinner.getSelectedItem().toString(),
                                    (Location) locationEmp.getSelectedItem());
                            Log.d("Success", "CreatedLocationEmployee:success");

                    } else if (spinner.getSelectedItem().equals("Branch Manager")) {
                        user = new BranchManager(name.getText().toString(), email.getText().toString(),
                                password.getText().toString(), spinner.getSelectedItem().toString());

                        Log.d("Success", "CreatedBranchManager:success");

                    } else {
                        user = new Admin(name.getText().toString(), email.getText().toString(),
                                password.getText().toString(), spinner.getSelectedItem().toString());

                        Log.d("Success", "CreatedAdmin:success");

                    }

                    firstAuthentication(user);

                }


            }


        });


    }


    private boolean invalidPassword(String password){
        //Validation logic from some framework
        //for now
        return password.equals("");
    }

    private boolean invalidEmail(String email){
        //Validation logic from some framework
        //for now
        return email.equals("");
    }
    private boolean invalidName(String name){
        return name.equals("") || name.length() <=2;
    }



    private boolean createUserInDB(Actor user,FirebaseUser firebaseUser){
        DatabaseReference usersRef = ref.child("users");
        Log.d("DB", "DBInteraction:started");

        //Two ways of modifying the DB... Useful for you reading over this code
        /*
        this way lets you put multiple users at the same time into the DB

            Map<String, User> users = new HashMap<>();
            users.put(firebaseUser.getUid(), user);
            usersRef.setValue(users);

        */

        //Since I only care about putting a single user in the DB
        usersRef.child(firebaseUser.getUid()).setValue(user);

        return true;
    }

    private void firstAuthentication(final Actor user){
        Log.d("Authentication", "AuthenticationStarted:success");

        mAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success", "createUserWithEmail:success");
                            FirebaseUser fireBaseUser = mAuth.getCurrentUser();

                            //Create the User in the DB
                            if(fireBaseUser != null && createUserInDB(user,fireBaseUser)){

                                Intent registeredIntent = new Intent(getApplicationContext(), RegisteredActivity.class);
                                registeredIntent.putExtra("userType", user.getUserType());
                                registeredIntent.putExtra("name", user.getName());
                                registeredIntent.putExtra("e-mail", user.getEmail());
                                startActivity(registeredIntent);
                                Log.d("Success", "DBCreationTask:success");

                            }else{
                                Log.w("Failure", "DBCreationTask:failure");
                                TextView failed = findViewById(R.id.failed);
                                failed.setText("Something Went Wrong, Please Try Again");
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Failure", "createUserWithEmail:failure", task.getException());
                            TextView failed = findViewById(R.id.failed);
                            Exception e =task.getException();
                            if(e != null){
                                failed.setText(e.toString());
                            }
                        }

                    }
                });
    }





    /**
     * Suggestion from Firebase guide
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //TODO do something
    }
}

