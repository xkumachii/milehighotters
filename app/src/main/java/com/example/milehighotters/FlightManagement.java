package com.example.milehighotters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FlightManagement extends AppCompatActivity {

    Button logView;
    Button manageFlights;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_management);

        logView = findViewById(R.id.viewLogs);
        manageFlights = findViewById(R.id.manageFlights);
        logout = findViewById(R.id.logout);

        logView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightManagement.this, LogViewer.class);
                startActivity(intent);
            }
        });

        manageFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightManagement.this, FlightManager.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightManagement.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
