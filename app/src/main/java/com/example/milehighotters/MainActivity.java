package com.example.milehighotters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
    TODO: fucking do shit.
    TODO: create things.
    TODO: CREATE SCHEMA AND DB FOR USERS AND FLIGHT
    TODO: CREATE A USER-SPECIFIC DB TO LIST FLIGHTS
    TODO: LOGS AND SHIT
    TODO: FLIGHT SEARCH AND RESERVE
    TODO: PERSISTENT USER INSTANCE UNTIL LOGOUT
    TODO: MAKE ACTICVITIES FOR FLIGHT SERCH, RESRVATION
    TODO: TO NOT FUCK AROUND TOO MUCH
    a whole lot of fucking shit TODO
     */

    Button login;
    TextView password;
    TextView username;
    Button createAccount;

    User user;

    Flight flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);

        user = User.get(this.getApplicationContext());

        flight = Flight.get(this.getApplicationContext());

        // insert three users into db

        if (!user.hasInstanceOf("alice5")) {
            UserItem alice = new UserItem();
            alice.setUsername("alice5");
            alice.setPassword("csumb100");
            alice.setDue(0);
            user.addUser(alice);
        }

        if (!user.hasInstanceOf("brian77")) {
            UserItem brian = new UserItem();
            brian.setUsername("brian77");
            brian.setPassword("123ABC");
            brian.setDue(0);
            user.addUser(brian);
        }

        if (!user.hasInstanceOf("chris21")) {
            UserItem chris = new UserItem();
            chris.setUsername("chris21");
            chris.setPassword("CHRIS21"); // todo: really, what kind of crappy passwords are these?
            chris.setDue(0);
            user.addUser(chris);
        }

        // insert five flights into db

        if (!flight.hasInstanceOf("Otter101")) {
            FlightItem otter101 = new FlightItem();
            otter101.setNumber("Otter101");
            otter101.setDeparture("Monterey");
            otter101.setArrival("Los Angeles");
            otter101.setTime("10:00");
            otter101.setCapacity(10);
            otter101.setPrice(150);
            otter101.setOccupancy(0);

            flight.addFlight(otter101);
        }

        if (!flight.hasInstanceOf("Otter102")) {
            FlightItem otter102 = new FlightItem();
            otter102.setNumber("Otter102");
            otter102.setDeparture("Los Angeles");
            otter102.setArrival("Monterey");
            otter102.setTime("13:00");
            otter102.setCapacity(10);
            otter102.setPrice(150);
            otter102.setOccupancy(0);

            flight.addFlight(otter102);
        }

        if (!flight.hasInstanceOf("Otter201")) {
            FlightItem otter201 = new FlightItem();
            otter201.setNumber("Otter201");
            otter201.setDeparture("Monterey");
            otter201.setArrival("Seattle");
            otter201.setTime("11:00");
            otter201.setCapacity(5);
            otter201.setPrice(200.5);
            otter201.setOccupancy(0);

            flight.addFlight(otter201);
        }

        if (!flight.hasInstanceOf("Otter205")) {
            FlightItem otter205 = new FlightItem();
            otter205.setNumber("Otter205");
            otter205.setDeparture("Monterey");
            otter205.setArrival("Seattle");
            otter205.setTime("15:00");
            otter205.setCapacity(15);
            otter205.setPrice(150);
            otter205.setOccupancy(0);

            flight.addFlight(otter205);
        }

        if (!flight.hasInstanceOf("Otter202")) {
            FlightItem otter202 = new FlightItem();
            otter202.setNumber("Otter202");
            otter202.setDeparture("Seattle");
            otter202.setArrival("Monterey");
            otter202.setTime("14:00");
            otter202.setCapacity(5);
            otter202.setPrice(200.5);
            otter202.setOccupancy(0);

            flight.addFlight(otter202);
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The admin username and password are hardcoded into this system,
                // and will not be part of any database.

                if (username.getText().toString().equals("admin2") &&
                        password.getText().toString().equals("admin2")) {
                    Intent intent = new Intent(MainActivity.this, FlightManagement.class);
                    startActivity(intent);
                } else if (!(username.getText().toString().isEmpty() ||
                           password.getText().toString().isEmpty())) {
                    boolean valid = true; // we assume login is valid until proven false.

                    if (!user.hasInstanceOf(username.getText().toString())) {
                        alert("User " + username.getText().toString() + " does not exist.");
                        valid = false;
                    }

                    if (user.hasInstanceOf(username.getText().toString())) {
                        UserItem currentUser = user.getUser(username.getText().toString());

                        if (!password.getText().toString().equals(currentUser.getPassword())) {
                            alert("Please enter your password correctly.");
                            valid = false;
                        }
                    }

                    if (valid) {
                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                        String currentUser = username.getText().toString();
                        intent.putExtra("CURRENT_USER", currentUser);
                        Log.d("LOGIN", "Successful.");
                        startActivity(intent);
                    }
                } else {
                    alert("Please enter username and password.");
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountCreation.class);
                startActivity(intent);
            }
        });
    }

    private void alert(String s) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
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
