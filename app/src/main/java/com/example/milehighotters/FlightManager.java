package com.example.milehighotters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FlightManager extends AppCompatActivity {

    Button addFlight;
    TextView flightNum;
    TextView departure;
    TextView arrival;
    TextView departureTime;
    TextView capacity;
    TextView price;

    Flight flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_manager);

        addFlight = findViewById(R.id.addFlight);

        flightNum = findViewById(R.id.flightNum);
        departure = findViewById(R.id.departure);
        arrival = findViewById(R.id.arrival);
        departureTime = findViewById(R.id.departTime);
        capacity = findViewById(R.id.flightCapacity);
        price = findViewById(R.id.flightPrice);

        flight = Flight.get(this.getApplicationContext());

        addFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true; // assume valid flight until proven invalid.

                if (flightNum.getText().toString().isEmpty() &&
                    departure.getText().toString().isEmpty() &&
                    arrival.getText().toString().isEmpty() &&
                    departureTime.getText().toString().isEmpty() &&
                    capacity.getText().toString().isEmpty() &&
                    price.getText().toString().isEmpty()) {
                    alert("Please fill in the flight information.");
                    valid = false;
                }

                if (valid) {
                    //todo: hasInstanceOf(num) to prevent dooplicates.
                    if (flight.hasInstanceOf(flightNum.getText().toString())) {
                        alert("Flight " + flightNum.getText().toString() + " already exists.");
                        valid = false;
                    }
                }

                if (valid) {
                    if (departure.getText().toString().isEmpty()) {
                        alert("Please enter departure.");
                        valid = false;
                    }
                }

                if (valid) {
                    if (arrival.getText().toString().isEmpty()) {
                        alert("Please enter arrival.");
                        valid = false;
                    }
                }

                if (valid) {
                    if (departureTime.getText().toString().isEmpty()) {
                        alert("Please enter departure time.");
                        valid = false;
                    }
                }

                if (valid) {
                    if (capacity.getText().toString().isEmpty()) {
                        alert("Please enter capacity.");
                        valid = false;
                    }
                }

                if (valid) {
                    if (price.getText().toString().isEmpty()) {
                        alert("Please enter the price.");
                        valid = false;
                    }
                }

                if (valid) {
                    if (Double.parseDouble(price.getText().toString()) <= 0 ||
                        Integer.parseInt(capacity.getText().toString()) <= 0) {
                        alert("Why?");
                        valid = false;
                    }
                }

                if (valid) {
                    //todo: log it to log mon niggeur.

                    alert("Flight " + flightNum.getText().toString()+ " has been created successfully.");

                    Log.i("flightItem " + flightNum.getText().toString(), "Successfully created.");

                    FlightItem flightToAdd = new FlightItem();
                    flightToAdd.setNumber(flightNum.getText().toString());
                    flightToAdd.setDeparture(departure.getText().toString());
                    flightToAdd.setArrival(arrival.getText().toString());
                    flightToAdd.setTime(departureTime.getText().toString());
                    flightToAdd.setCapacity(Integer.parseInt(capacity.getText().toString()));
                    flightToAdd.setPrice(Double.parseDouble(price.getText().toString()));
                    flightToAdd.setOccupancy(0);

                    flight.addFlight(flightToAdd);
                }

            }
        });

    }

    private void alert(String s) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(FlightManager.this);
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
