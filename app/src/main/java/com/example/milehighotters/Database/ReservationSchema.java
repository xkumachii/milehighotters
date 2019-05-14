package com.example.milehighotters.Database;

public class ReservationSchema {
    public static final class ReservationTable {
        public static final String NAME = "RESERVATIONS";

        public static final class Cols{
            public static final String UUID      = "uuid";
            public static final String USERUUID    = "useruuid";
            public static final String FLIGHTUUID = "flightuuid";
            public static final String USER   = "user";
            public static final String FLIGHTNUMBER      = "flightnumber";
            public static final String NUMTICKETS  = "numtickets";
        }
    }
}
