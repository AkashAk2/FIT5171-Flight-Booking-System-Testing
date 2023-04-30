import java.util.ArrayList;

public class FlightCollection {

    public static ArrayList<Flight> flights = new ArrayList<>();

    // Get the ArrayList list of flights
    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    // Add flights to the ArrayList
    public static void addFlights(ArrayList<Flight> flights) {
        FlightCollection.flights.addAll(flights);
    }

    // Get the flight information for a direct flight from city1 to city2
    public static Flight getFlightInfo(String city1, String city2) {
        for (Flight flight : flights) {
            if (flight.getDepartFrom().equals(city1) && flight.getDepartTo().equals(city2)) {
                return flight;
            }
        }
        return null;
    }

    // Get the flight information where the flight departs from the specified city
    public static Flight getFlightInfo(String city) {
        for (Flight flight : flights) {
            if (flight.getDepartFrom().equals(city)) {
                return flight;
            }
        }
        return null;

    }

    // Get the flight information for a flight with a specific flight_id
    public static Flight getFlightInfo(int flight_id) {
        for (Flight flight : flights) {
            if (flight.getFlightID() == flight_id) {
                return flight;
            }
        }
        return null;
    }
}

