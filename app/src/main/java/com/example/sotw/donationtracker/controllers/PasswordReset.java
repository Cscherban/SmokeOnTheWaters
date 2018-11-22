package com.example.sotw.donationtracker.controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sotw.donationtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {
    private EditText email;
    private Button button;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        email = findViewById(R.id.emailResetPassword);
        button = findViewById(R.id.resetPasswordButton);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(PasswordReset.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAdd = email.getText().toString().trim();

                if (emailAdd.equals("")) {
                    Toast.makeText(PasswordReset.this, "Enter a valid e-mail, ya goofball", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.show();
                    mAuth.sendPasswordResetEmail(emailAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(PasswordReset.this, "We sent you the e-mail, ya goofball", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(PasswordReset.this, "Wrong e-mail, ya goofball!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }
}
