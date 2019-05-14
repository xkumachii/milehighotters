package com.example.milehighotters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button logout;
    Button reserveSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent here = getIntent();
        final String currentUser = here.getStringExtra("CURRENT_USER");

        reserveSeat = findViewById(R.id.reservation);
        logout = findViewById(R.id.logout);

        reserveSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, FlightSearch.class);
                intent.putExtra("USER_NAME", currentUser);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // leads directly into the main menu.

                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
