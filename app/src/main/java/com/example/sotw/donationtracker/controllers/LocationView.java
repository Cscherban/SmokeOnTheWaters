package com.example.sotw.donationtracker.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sotw.donationtracker.R;
import com.example.sotw.donationtracker.model.Location;

/**
 * View for location
 */
public class LocationView extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_view);

        textView = findViewById(R.id.infoOnLocation);
        button = findViewById(R.id.direction);

        String address = getIntent().getStringExtra("address");
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String type = getIntent().getStringExtra("type");
        String website = getIntent().getStringExtra("website");

        String everything = address + "\n\n\n" + name + "\n\n\n"
                            + "Phone: " + phone + "\n\n\n" + "Type: " + type
                            + "\n\n\n" + "Website: " + website;
        textView.setText(everything);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final float lattitude = getIntent().getFloatExtra("lattitude", 5);
                final float longitude1 = getIntent().getFloatExtra("longitude", 5);

                final AlertDialog.Builder builder = new AlertDialog.Builder(LocationView.this);
                builder.setMessage("Open Google Maps?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                String latitude = "" + lattitude;
                                String longitude = "" + longitude1;
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setPackage("com.google.android.apps.maps");

                                try{
                                    if (mapIntent.resolveActivity(LocationView.this.getPackageManager()) != null) {
                                        startActivity(mapIntent);
                                    }
                                }catch (NullPointerException e){
                                    Log.e("MAPERROR", "onClick: NullPointerException: Couldn't open map." + e.getMessage() );
                                    Toast.makeText(LocationView.this, "Couldn't open map", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                dialog.cancel();
                            }
                        });

                final AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }
}
