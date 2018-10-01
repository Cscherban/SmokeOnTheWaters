package com.example.sotw.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sotw.donationtracker.Account;
import com.example.sotw.donationtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;




import android.support.annotation.NonNull;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;


    /**
     * This is what success looks like:
     * @param user - the user object returned by firebase
     */
    private void success(FirebaseUser user){
        //Do Stuff with the firebase user

        Intent nextScreen = new Intent(getApplicationContext(), Account.class);
        startActivity(nextScreen);

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
