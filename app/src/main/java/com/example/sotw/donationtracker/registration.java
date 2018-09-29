package com.example.sotw.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {
    private EditText name;
    private EditText ID;
    private EditText password;
    private Spinner spinner;

    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.name);
        ID = findViewById(R.id.id);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);
        String[] spinnerValues = new String[] {"Pick a type of user","User", "Location Employee", "Admin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerValues);
        spinner.setAdapter(adapter);


        Button registerButton = findViewById(R.id.button4);
        Button cancelButton = findViewById((R.id.button2));



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
}

