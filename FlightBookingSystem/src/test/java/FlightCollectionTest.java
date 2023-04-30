import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FlightCollectionTest {
    private Flight flight1;
    private Flight flight2;
    private Flight flight3;

    @BeforeEach
    public void setUp() {
        flight1 = Mockito.mock(Flight.class);
        flight2 = Mockito.mock(Flight.class);
        flight3 = Mockito.mock(Flight.class);

        when(flight1.getFlightID()).thenReturn(1);
        when(flight1.getDepartFrom()).thenReturn("New York");
        when(flight1.getDepartTo()).thenReturn("Los Angeles");

        when(flight2.getFlightID()).thenReturn(2);
        when(flight2.getDepartFrom()).thenReturn("Los Angeles");
        when(flight2.getDepartTo()).thenReturn("Tokyo");

        when(flight3.getFlightID()).thenReturn(3);
        when(flight3.getDepartFrom()).thenReturn("Tokyo");
        when(flight3.getDepartTo()).thenReturn("New York");

        FlightCollection.getFlights().clear(); // Clear the flight list before adding mocks

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);

        FlightCollection.addFlights(flights);
    }

    @Test
    public void testAddFlight() {
        Flight flight4 = Mockito.mock(Flight.class);
        when(flight4.getFlightID()).thenReturn(4);

        int initialSize = FlightCollection.getFlights().size();

        ArrayList<Flight> newFlights = new ArrayList<>();
        newFlights.add(flight4);
        FlightCollection.addFlights(newFlights);

        assertEquals(initialSize + 1, FlightCollection.getFlights().size());
        assertEquals(flight4, FlightCollection.getFlightInfo(4));
    }


    @Test
    public void testValidCityNames() {
        assertNotNull(flight1.getDepartFrom());
        assertNotNull(flight1.getDepartTo());
        assertFalse(flight1.getDepartFrom().trim().isEmpty());
        assertFalse(flight1.getDepartTo().trim().isEmpty());
    }

    @Test
    public void testGetFlightInfoDirect() {
        Flight resultFlight = FlightCollection.getFlightInfo("New York", "Los Angeles");
        assertNotNull(resultFlight);
        assertEquals(flight1, resultFlight);
    }

    @Test
    public void testGetFlightInfoDepartureCity() {
        Flight resultFlight = FlightCollection.getFlightInfo("Tokyo");
        assertNotNull(resultFlight);
        assertEquals(flight3, resultFlight);
    }

    @Test
    public void testGetFlightInfoById() {
        Flight resultFlight = FlightCollection.getFlightInfo(2);
        assertNotNull(resultFlight);
        assertEquals(flight2, resultFlight);
    }
}
