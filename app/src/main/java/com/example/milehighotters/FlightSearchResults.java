package com.example.milehighotters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FlightSearchResults extends AppCompatActivity {

    ListView results;

    Flight flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search_results);

        Intent here = getIntent();
        final String currentUser = here.getStringExtra("CURRENT_USER");
        final String arrival = here.getStringExtra("ARRIVAL");
        final String departure = here.getStringExtra("DEPARTURE");

        flight = Flight.get(getApplicationContext());

        //todo: make that muthafuckin listview POPPIN!!!

        results = findViewById(R.id.results);

        List<FlightItem> flights = flight.getCertainFlightInstances(departure, arrival);

//        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(FlightSearchResults.this, "FUCK YOU", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
