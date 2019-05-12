package com.example.milehighotters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The admin username and password are hardcoded into this system,
                // and will not be part of any database.

                if (username.getText().toString().equals("admin2") &&
                        password.getText().toString().equals("admin2")) {
                    Intent intent = new Intent(MainActivity.this, FlightManagement.class);
                    startActivity(intent);
                } else if (username.getText().toString().length() > 0 && password.getText().toString().length() > 0){
                    //TODO: IMPLEMENT FUCKING USER DATABASE SHIT YOU FUCK
                    //TODO: FOR NOW, THE SCREEN LEADS DIRECTLY INTO THIS SHITTY MAIN MENU.

                    boolean valid = true;

                    /*
                    if (user not exist) {
                        go yell at the user.
                        valid = false;
                    }

                    if (password incorrect) {
                        go yell at the user
                        valid = false;
                    }
                     */

                    if (valid) {
                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter username and password.", Toast.LENGTH_SHORT).show();
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
}
