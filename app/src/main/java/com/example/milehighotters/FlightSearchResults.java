package com.example.milehighotters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class FlightSearchResults extends AppCompatActivity {

    ListView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search_results);

        Intent here = getIntent();
        final String currentUser = here.getStringExtra("CURRENT_USER");
        final String arrival = here.getStringExtra("ARRIVAL");
        final String departure = here.getStringExtra("DEPARTURE");

        //todo: make that muthafuckin listview POPPIN!!!

    }
}
