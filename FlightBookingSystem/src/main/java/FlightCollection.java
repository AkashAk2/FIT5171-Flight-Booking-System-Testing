import java.util.ArrayList;

/**
 * Represents a collection of Flight objects.
 */
public class FlightCollection {

    /**
     * A list of Flight objects.
     */
    public static ArrayList<Flight> flights = new ArrayList<>();

    /**
     * Returns the list of flights.
     *
     * @return An ArrayList of Flight objects
     */
    public static ArrayList<Flight> getFlights() {
        return flights;
    }
    /**
     * Adds a list of flights to the collection.
     *
     * @param flights The list of Flight objects to be added
     * @throws IllegalArgumentException if departTo or departFrom is null for any flight in the list
     */
    public void addFlights(ArrayList<Flight> flights) {
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
     * @return The Flight object for the direct flight or null if no direct flight is found
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
     * @return The Flight object for the flight or null if not found
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
     * @param flight_id The flight ID
     * @return The Flight object for the flight or null if not found
     */
    public static Flight getFlightInfo(int flight_id) {
        for (Flight flight : flights) {
            if (flight.getFlightID() == flight_id) {
                return flight;
            }
        }
        return null;
    }
}

