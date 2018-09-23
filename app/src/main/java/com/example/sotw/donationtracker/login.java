package com.example.sotw.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);
                TextView status = (TextView) findViewById(R.id.status);

                Intent nextScreen = new Intent(getApplicationContext(), Account.class);
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("bobwaters") && pass.equals("Cs2340")) {
                    startActivity(nextScreen);
                } else {
                    status.setText("Incorrect username or password");
                }
            }
        });
    }
}
