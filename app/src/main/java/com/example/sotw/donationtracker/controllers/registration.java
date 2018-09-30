package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private EditText ID;
    private EditText password;
    private Spinner spinner;

    /**
     * Usefule objects
     */
    private User user;              //User Object
    private FirebaseAuth mAuth;     //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.name);
        ID = findViewById(R.id.id);
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
                user = new User(name.getText().toString(), ID.getText().toString(),
                        password.getText().toString(), spinner.getSelectedItem().toString());
                if (!user.getName().equals("") && !user.getId().equals("") &&
                        !user.getPassword().equals("") &&
                        !user.getUserType().equals("Pick a type of user")) {
                    Intent registerIntent = new Intent(getApplicationContext(),
                            RegisteredActivity.class);
                    startActivity(registerIntent);
                } else {
                    TextView failed = findViewById(R.id.failed);
                    failed.setText("Please enter valid information in each box");
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
        //Do somethin
    }
}

