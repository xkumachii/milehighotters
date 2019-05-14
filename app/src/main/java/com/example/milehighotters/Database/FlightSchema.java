package com.example.milehighotters.Database;

public class FlightSchema {
    public static final class FlightTable {
        public static final String NAME = "FLIGHT";

        public static final class Cols{
            public static final String UUID      = "uuid";
            public static final String NUMBER    = "number";
            public static final String DEPARTURE = "departure";
            public static final String ARRIVAL   = "arrival";
            public static final String TIME      = "time";
            public static final String CAPACITY  = "capacity";
            public static final String PRICE     = "price";
            public static final String OCCUPANCY = "occupancy";
        }
    }

}
