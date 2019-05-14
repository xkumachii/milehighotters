package com.example.milehighotters;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class FlightItem {
    private UUID flightID;
    private String number;
    private String departure;
    private String arrival;
    private String time;
    private int capacity;
    private double price;
    private int occupancy;
    private int sqlLogId;

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public FlightItem() {
        flightID = UUID.randomUUID();
    }

    public FlightItem(UUID id) {
        flightID = id;
    }

    public UUID getFlightID() {
        return flightID;
    }

    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getSqlLogId() {
        return sqlLogId;
    }

    public void setSqlLogId(int sqlLogId) {
        this.sqlLogId = sqlLogId;
    }

    //todo: implement tostring for listing flites.
}
