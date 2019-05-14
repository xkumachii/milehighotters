package com.example.milehighotters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FlightSearch extends AppCompatActivity {

    //todo: passing in them extras is IMPERATIVE.

    TextView departSearch;
    TextView arriveSearch;

    Button search;

    Flight flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        Intent here = getIntent();
        final String currentUser = here.getStringExtra("CURRENT_USER");

        departSearch = findViewById(R.id.departure);
        arriveSearch = findViewById(R.id.arrival);

        search = findViewById(R.id.search);

        flight = flight.get(this.getApplicationContext());

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flightsFound = false;

                String departure = departSearch.getText().toString();
                String arrival = arriveSearch.getText().toString();

                if (flight.hasInstanceOf(departure, arrival)) {
                    flightsFound = true;
                }

                if (flightsFound) {
                    Intent intent = new Intent(FlightSearch.this, FlightSearchResults.class);


                    intent.putExtra("CURRENT_USER", currentUser);
                    intent.putExtra("DEPARTURE", departure);
                    intent.putExtra("ARRIVAL", arrival);

                    startActivity(intent);
                } else {
                    alert("No flights found.");
                }
            }
        });

    }

    private void alert(String s) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(FlightSearch.this);
        final AlertDialog ALERT = alertBuilder.create();

        alertBuilder.setMessage(s + "\n");

        alertBuilder.setNegativeButton("OK.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ALERT.dismiss();
            }
        });

        alertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });

        AlertDialog display = alertBuilder.create();
        display.show();
    }

}
