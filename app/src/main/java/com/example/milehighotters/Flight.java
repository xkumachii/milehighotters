package com.example.milehighotters;

import android.content.Context;

import com.example.milehighotters.Database.FlightHelper;
import com.example.milehighotters.Database.UserHelper;

import java.util.List;

public class Flight {
    private static Flight sFlight;
    private Context mContext;
    private FlightHelper mFlightHelper;

    public static Flight get(Context context){
        if(sFlight == null){
            sFlight = new Flight(context);
        }
        return sFlight;
    }

    private Flight(Context context){
        mContext = context.getApplicationContext();
        mFlightHelper = new FlightHelper(mContext);
    }

    public long addFlight(FlightItem flight){
        return mFlightHelper.addFlightItem(flight);
    }

    public boolean hasInstanceOf(String num) {
        return mFlightHelper.hasInstanceOf(num);
    }

    public boolean hasInstanceOf(String departure, String arrival) {
        return mFlightHelper.hasInstanceOf(departure, arrival);
    }

    public FlightItem getFlight(String num) {
        return mFlightHelper.getFlight(num);
    }

    public List<FlightItem> getCertainFlightInstances(String departure, String arrival) {
        return mFlightHelper.getCertainFlightInstances(departure, arrival);
    }

}
