package com.example.milehighotters.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.milehighotters.Database.FlightSchema.FlightTable;
import com.example.milehighotters.FlightItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlightHelper extends SQLiteOpenHelper {

    private static final String TAG = "FLIGHTS";

    private static final int VERSION            = 1;
    public static final String DATABASE_NAME    = "flights.db";

    private SQLiteDatabase db;

    public FlightHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + FlightTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                FlightTable.Cols.ARRIVAL + ","+
                FlightTable.Cols.CAPACITY + ","+
                FlightTable.Cols.DEPARTURE + ","+
                FlightTable.Cols.NUMBER + ","+
                FlightTable.Cols.OCCUPANCY + ","+
                FlightTable.Cols.PRICE + ","+
                FlightTable.Cols.TIME + ","+
                FlightTable.Cols.UUID +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    private long insertFlight(FlightItem flight) {

        ContentValues cv = getContentValues(flight);

        db = this.getWritableDatabase();

        return db.insert(FlightTable.NAME, null, cv);

    }

    public long addFlightItem(FlightItem flight) {

        if (this.getFlightItem(flight.getFlightID()) == null) {
            return insertFlight(flight);
        } else {
            return updateFlightItem(flight);
        }
    }

    private int updateFlightItem(FlightItem flight){
        db = this.getWritableDatabase();
        ContentValues cv = FlightHelper.getContentValues(flight);
        String whereClause = FlightTable.Cols.UUID + " = ? ";
        String[] whereArgs = {flight.getFlightID().toString()};
        try{
            return db.update(FlightTable.NAME, cv, whereClause, whereArgs);
        } catch (Exception e){
            Log.d(TAG, "something is wrong in updateFlightItem");
            return -1;
        }
    }

    private FlightItem getFlightItem(UUID flightUUID){
        String whereClause = FlightTable.Cols.UUID + " = ? ";
        String[] whereArgs = {flightUUID.toString()};

        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryDB(FlightTable.NAME,whereClause,whereArgs));

        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "No results from getFlightItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getFlightItem();
        } finally {
            cursor.close();
        }
    }

    public List<FlightItem> getFlights() {
        List<FlightItem> flights = new ArrayList<>();

        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryDB(FlightTable.NAME,null,null));

        try {
            if(cursor.getCount() == 0){
                Log.d(TAG, "getFlights returned nothing.");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                flights.add(cursor.getFlightItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return flights;
    }

    public FlightItem getFlight(String num) {
        FlightItem flight = new FlightItem();

        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryDB(FlightTable.NAME,null,null));

        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "getFlights returned nothing.");
                return null;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (cursor.getFlightItem().getNumber().equals(num)) {
                    flight = cursor.getFlightItem();
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return flight;
    }

    public List<FlightItem> getCertainFlightInstances(String departure, String arrival) {
        List<FlightItem> flights = new ArrayList<>();

        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryDB(FlightTable.NAME,null,null));

        try {
            if (cursor.getCount() == 0) {
                Log.d(TAG, "getCertainUserInstanceOf returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (cursor.getFlightItem().getDeparture().equals(departure) &&
                    cursor.getFlightItem().getArrival().equals(arrival)) {
                    flights.add(cursor.getFlightItem());
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return flights;
    }

    public boolean hasInstanceOf(String num) {
        boolean hasInstance = false;

        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryDB(FlightTable.NAME,null,null));

        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "Nothing to scan here.");
                return hasInstance;
            }

            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                if (cursor.getFlightItem().getNumber().equals(num)) {
                    hasInstance = true;
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return hasInstance;
    }

    public boolean hasInstanceOf(String departure, String arrival) {
        boolean hasInstance = false;

        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryDB(FlightTable.NAME,null,null));

        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "Nothing to scan here.");
                return hasInstance;
            }

            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                if (cursor.getFlightItem().getDeparture().equals(departure) &&
                    cursor.getFlightItem().getArrival().equals(arrival)) {
                    hasInstance = true;
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return hasInstance;
    }

    private Cursor queryDB(String DBName, String whereClause, String[] whereArgs){
        db = this.getWritableDatabase();

        try {
            return db.query(FlightTable.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null);
        } catch (Exception e){
            Log.d(TAG, "Problem in queryDB!!");
            return null;
        }
    }

    public static ContentValues getContentValues(FlightItem flight){
        ContentValues cv = new ContentValues();

        cv.put(FlightTable.Cols.ARRIVAL, flight.getArrival());
        cv.put(FlightTable.Cols.CAPACITY, flight.getCapacity());
        cv.put(FlightTable.Cols.DEPARTURE, flight.getDeparture());
        cv.put(FlightTable.Cols.NUMBER, flight.getNumber());
        cv.put(FlightTable.Cols.OCCUPANCY, flight.getOccupancy());
        cv.put(FlightTable.Cols.PRICE, flight.getPrice());
        cv.put(FlightTable.Cols.TIME, flight.getTime());
        cv.put(FlightTable.Cols.UUID, flight.getFlightID().toString());


        return cv;
    }


}
