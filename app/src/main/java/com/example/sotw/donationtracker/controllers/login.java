package com.example.sotw.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Actor;
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

/**
 * controller to filter input for when login is done
 */
public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference ref; //Reference to the DB..to let us modify it
    private boolean flag;



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

                //get All datasnapshot objects from the "locations" document(aka table)

                Actor actor = dataSnapshot.getValue(Actor.class);
                if(actor == null){
                    return;
                }
                Log.d("Firebase",actor.getClass().toString());

                Log.d("ACOTOAIHFDHSFAKHGS",actor.toString());

                if("Location Employee".equals((actor).getUserType())){
                    Intent LocationEmployeeScreen =
                            new Intent(getApplicationContext(), LocationEmployeeActivity.class);
                    LocationEmployeeScreen.putExtra("locale", actor.getLocation().getName());
                    startActivity(LocationEmployeeScreen);
                }else{
                    Intent nextScreen = new Intent(getApplicationContext(), UserActivity.class);
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
        EditText username = findViewById(R.id.username);
        username.setText("Invalid E-mail or Password");
      //  TextView status = findViewById(R.id.status);
      //  status.setText("Incorrect username or password");

    }

    private void failureWithValidEmail() {
        EditText username = findViewById(R.id.username);
        final String email = username.getText().toString().trim();
        final String NEWtempEmail = email.hashCode() + "";
        ref.child("LoginAttempts").child(NEWtempEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView status = findViewById(R.id.status);
                if (dataSnapshot.getValue() == null) {
                    ref.child("LoginAttempts").child(NEWtempEmail).setValue(2);
                    status.setText("Incorrect username or password." + "\n" + "Attempts Remaining: " + 2);
                } else {
                    ref.child("LoginAttempts").child(NEWtempEmail).setValue((long) (dataSnapshot.getValue()) - 1);
                    long newAttempt = (long)(dataSnapshot.getValue()) - 1;
                    status.setText("Incorrect username or password." + "\n" +"Attempts Remaining: " +
                            newAttempt);
                    if (newAttempt == 0) {
                        status.setText("User locked out");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void attemptLogin(final String email, final String pass){
        flag = true;
        int code = email.hashCode();
        ref.child("LoginAttempts").child(code + "").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView status = findViewById(R.id.status);
                if (dataSnapshot.getValue() != null && ((long) dataSnapshot.getValue()) == 0) {
                    status.setText("User locked out");
                    flag = false;

                } else {
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Success Message", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            success(user);
                                        }
                                        ref.child("LoginAttempts").child(email.hashCode() + "").setValue(3);

                                    } else {
                                        failureWithValidEmail();
                                    }

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();


        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);

                Intent nextScreen = new Intent(getApplicationContext(), Account.class);
                String email = username.getText().toString();
                String pass = password.getText().toString();
                if ("".equals(email) && "".equals(pass)) {
                    failure();
                } else {
                    attemptLogin(email, pass);
                }
            }
        });
    }


}
