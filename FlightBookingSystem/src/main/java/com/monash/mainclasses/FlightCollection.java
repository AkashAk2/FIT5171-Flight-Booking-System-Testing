package com.monash.mainclasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of com.monash.fit5171.Flight objects.
 */
public class FlightCollection {

    /**
     * A list of com.monash.fit5171.Flight objects.
     */
    protected static final List<Flight> flights = new ArrayList<>();


    /**
     * Returns the list of flights.
     *
     * @return An ArrayList of com.monash.fit5171.Flight objects
     */
    public static List<Flight> getFlights() {
        return flights;
    }
    /**
     * Adds a list of flights to the collection.
     *
     * @param flights The list of com.monash.fit5171.Flight objects to be added
     * @throws IllegalArgumentException if departTo or departFrom is null for any flight in the list
     */
    public void addFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            // You can add validation checks here, for example:
            if (flight.getDepartTo() == null || flight.getDepartFrom() == null) {
                throw new IllegalArgumentException("Invalid flight: departTo or departFrom is null.");
            }
        }
        FlightCollection.flights.addAll(flights);
    }

    /**
     * Retrieves flight information for a direct flight between two cities.
     *
     * @param city1 The departure city
     * @param city2 The destination city
     * @return The com.monash.fit5171.Flight object for the direct flight or null if no direct flight is found
     * @throws IllegalArgumentException if city1 or city2 is null or empty
     */
    public Flight getFlightInfo(String city1, String city2) {
        if (city1 == null || city1.isEmpty() || city2 == null || city2.isEmpty()) {
            throw new IllegalArgumentException("City names must be non-empty.");
        }

        for (Flight flight : flights) {
            if (flight.getDepartTo().equals(city1) && flight.getDepartFrom().equals(city2)) {
                return flight;
            }
        }
        return null; // No direct flight found
    }

    /**
     * Retrieves flight information for a flight departing to a specific city.
     *
     * @param city The departure city
     * @return The com.monash.fit5171.Flight object for the flight or null if not found
     */
    public static Flight getFlightInfo(String city) {
        for (Flight flight : flights) {
            if (flight.getDepartFrom().equals(city)) {
                return flight;
            }
        }
        return null;

    }

    /**
     * Retrieves flight information for a flight with a specific flight ID.
     *
     * @param flightId The flight ID
     * @return The com.monash.fit5171.Flight object for the flight or null if not found
     */
    public static Flight getFlightInfo(int flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightID() == flightId) {
                return flight;
            }
        }
        return null;
    }
}

