package com.example.milehighotters.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.milehighotters.FlightItem;

import java.util.UUID;

public class FlightCursorWrapper extends CursorWrapper {
    public FlightCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public FlightItem getFlightItem() {
        String uuidString = getString(getColumnIndex(FlightSchema.FlightTable.Cols.UUID));
        String number = getString(getColumnIndex(FlightSchema.FlightTable.Cols.NUMBER));
        String departure = getString(getColumnIndex(FlightSchema.FlightTable.Cols.DEPARTURE));
        String arrival = getString(getColumnIndex(FlightSchema.FlightTable.Cols.ARRIVAL));
        String time = getString(getColumnIndex(FlightSchema.FlightTable.Cols.TIME));
        int capacity = getInt(getColumnIndex(FlightSchema.FlightTable.Cols.CAPACITY));
        double price = getDouble(getColumnIndex(FlightSchema.FlightTable.Cols.PRICE));
        int occupancy = getInt(getColumnIndex(FlightSchema.FlightTable.Cols.OCCUPANCY));

        int sqlLogId = getInt(getColumnIndex("_id"));

        FlightItem flight = new FlightItem(UUID.fromString(uuidString));

        flight.setNumber(number);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setTime(time);
        flight.setCapacity(capacity);
        flight.setPrice(price);
        flight.setOccupancy(occupancy);
        flight.setSqlLogId(sqlLogId);

        return flight;
    }
}
