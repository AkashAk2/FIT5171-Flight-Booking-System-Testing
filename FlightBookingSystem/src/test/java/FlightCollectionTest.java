import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// This class is responsible for testing the FlightCollection class.
class FlightCollectionTest {


    // Set up method to run before each test case.
    @BeforeEach
    public void setUp() {
        // Clear the flights ArrayList before each test
        FlightCollection.getFlights().clear();
    }

    // Test adding a flight to the flight collection.
    @Test
    public void testAddFlight() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getDepartTo()).thenReturn("New York");
        when(flight.getDepartFrom()).thenReturn("Los Angeles");

        FlightCollection flightCollection = new FlightCollection();

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight);
        flightCollection.addFlights(flights);

        Flight result = flightCollection.getFlightInfo("New York", "Los Angeles");
        assertEquals(flight, result);
    }

    // Test adding multiple flights to the flight collection.
    @Test
    public void testMultipleFlights() {
        Flight flight1 = Mockito.mock(Flight.class);
        when(flight1.getDepartTo()).thenReturn("New York");
        when(flight1.getDepartFrom()).thenReturn("Los Angeles");

        Flight flight2 = Mockito.mock(Flight.class);
        when(flight2.getDepartTo()).thenReturn("Los Angeles");
        when(flight2.getDepartFrom()).thenReturn("San Francisco");

        FlightCollection flightCollection = new FlightCollection();

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flightCollection.addFlights(flights);

        Flight result1 = flightCollection.getFlightInfo("New York", "Los Angeles");
        Flight result2 = flightCollection.getFlightInfo("Los Angeles", "San Francisco");

        assertEquals(flight1, result1);
        assertEquals(flight2, result2);
    }

    // Test if a flight in the collection conforms to the specified requirements.
    @Test
    public void testValidFlight() {
        Flight flight = Mockito.mock(Flight.class);
        when(flight.getFlightID()).thenReturn(1);
        when(flight.getDepartTo()).thenReturn("New York");
        when(flight.getDepartFrom()).thenReturn("Los Angeles");
        when(flight.getCode()).thenReturn("AA123");
        when(flight.getCompany()).thenReturn("American Airlines");
        when(flight.getDateFrom()).thenReturn("2023-05-01");
        when(flight.getDateTo()).thenReturn("2023-05-02");

        FlightCollection flightCollection = new FlightCollection();
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight);
        flightCollection.addFlights(flights);

        Flight result = flightCollection.getFlightInfo("New York", "Los Angeles");

        // Requirement 1: Flight ID should be a positive integer
        assertTrue(result.getFlightID() > 0);

        // Requirement 2: departTo and departFrom city names should be non-empty and not equal to each other
        assertNotNull(result.getDepartTo());
        assertNotNull(result.getDepartFrom());
        assertNotEquals(result.getDepartTo(), result.getDepartFrom());

        // Requirement 3: code and company should be non-empty
        assertNotNull(result.getCode());
        assertNotNull(result.getCompany());

        // Requirement 4: dateFrom and dateTo should be non-empty, and dateTo should be after dateFrom
        assertNotNull(result.getDateFrom());
        assertNotNull(result.getDateTo());
        assertTrue(result.getDateTo().compareTo(result.getDateFrom()) > 0);
    }


    // Test getting flight information from the flight collection.
    @Test
    public void testGetFlightInformation() {
        Flight mockFlight = Mockito.mock(Flight.class);
        when(mockFlight.getFlightID()).thenReturn(1);
        when(mockFlight.getDepartTo()).thenReturn("New York");
        when(mockFlight.getDepartFrom()).thenReturn("Los Angeles");
        when(mockFlight.getCode()).thenReturn("AA123");
        when(mockFlight.getCompany()).thenReturn("American Airlines");
        when(mockFlight.getDateFrom()).thenReturn("2023-05-01");
        when(mockFlight.getDateTo()).thenReturn("2023-05-02");


        FlightCollection flightCollection = new FlightCollection();
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(mockFlight);
        flightCollection.addFlights(flights);

        Flight result = flightCollection.getFlightInfo("New York", "Los Angeles");

        assertNotNull(result, "Flight information should not be null.");
        assertEquals(mockFlight.getFlightID(), result.getFlightID(), "Flight IDs should match.");
        assertEquals(mockFlight.getDepartTo(), result.getDepartTo(), "Departure cities should match.");
        assertEquals(mockFlight.getDepartFrom(), result.getDepartFrom(), "Destination cities should match.");
        assertEquals(mockFlight.getCode(), result.getCode(), "Flight codes should match.");
        assertEquals(mockFlight.getCompany(), result.getCompany(), "Airline companies should match.");
        assertEquals(mockFlight.getDateFrom(), result.getDateFrom(), "Departure dates should match.");
        assertEquals(mockFlight.getDateTo(), result.getDateTo(), "Arrival dates should match.");
    }

    // Test getting flight information with invalid city names.
    @Test
    public void testGetFlightInfoInvalidCityNames() {
        // Test with an empty departTo city name

        FlightCollection flightCollection = new FlightCollection();
        assertThrows(IllegalArgumentException.class, () -> {
            flightCollection.getFlightInfo("", "Los Angeles");
        });

        // Test with an empty departFrom city name
        assertThrows(IllegalArgumentException.class, () -> {
            flightCollection.getFlightInfo("New York", "");
        });

        // Test with null departTo city name
        assertThrows(IllegalArgumentException.class, () -> {
            flightCollection.getFlightInfo(null, "Los Angeles");
        });

        // Test with null departFrom city name
        assertThrows(IllegalArgumentException.class, () -> {
            flightCollection.getFlightInfo("New York", null);
        });
    }

}