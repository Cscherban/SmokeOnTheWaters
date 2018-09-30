package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registration extends AppCompatActivity {
    /**
     * UI elements
     */
    private EditText name;
    private EditText email;
    private EditText password;
    private Spinner spinner;

    /**
     * Usefule objects
     */
    private User user;              //User Object
    private FirebaseAuth mAuth;     //Firebase Autherization object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);

        String[] spinnerValues = new String[] {"Pick a type of user","User", "Location Employee", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerValues);
        spinner.setAdapter(adapter);


        Button registerButton = findViewById(R.id.button4);
        Button cancelButton = findViewById((R.id.button2));
        mAuth = FirebaseAuth.getInstance();


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
                if(invalidName(name.getText().toString())){
                    TextView failed = findViewById(R.id.failed);
                    failed.setText("Please Enter a Valid Name");

                }else if(invalidEmail(email.getText().toString())){

                    TextView failed = findViewById(R.id.failed);
                    failed.setText("Please Enter a Valid Email");

                }else if(invalidPassword(password.getText().toString())){
                    TextView failed = findViewById(R.id.failed);
                    failed.setText("Please Enter a Valid Password");
                }else if(spinner.getSelectedItem().toString().equals("Pick a type of user")){
                    TextView failed = findViewById(R.id.failed);
                    failed.setText("Please Select a User Type");
                } else{
                    //create a new user
                    //Now time for firebase Stuff=
                    user = new User(name.getText().toString(), email.getText().toString(),
                            password.getText().toString(), spinner.getSelectedItem().toString());

                    Intent registerIntent = new Intent(getApplicationContext(),
                            RegisteredActivity.class);
                    startActivity(registerIntent);
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





    /**
     * Suggestion from Firebase guide
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Do somethin
    }
}

