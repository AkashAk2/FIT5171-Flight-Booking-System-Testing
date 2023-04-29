import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class FlightTest {

    private Airplane mockAirplane;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Flight flight;


    @BeforeEach
    public void setUp() {
        // Create mock airplane object
        mockAirplane = mock(Airplane.class);
        Flight newFlight = new Flight(2, "Sydney", "Melbourne", "VA002",
                "Virgin Australia", new Timestamp(Calendar.getInstance().getTimeInMillis()),
                new Timestamp(Calendar.getInstance().getTimeInMillis()), mockAirplane);

        // Create valid timestamps for tests
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.MAY, 1, 12, 0, 0);
        dateFrom = new Timestamp(calendar.getTimeInMillis());
        calendar.set(2023, Calendar.MAY, 1, 14, 0, 0);
        dateTo = new Timestamp(calendar.getTimeInMillis());
    }

    @Test
    public void testFlightCreation() {
        // Test valid flight creation
        Flight flight = new Flight(1, "Perth", "Melbourne", "JS123",
                "Jetstar", dateFrom, dateTo, mockAirplane);
        Assertions.assertEquals(1, flight.getFlightID());
        Assertions.assertEquals("Perth", flight.getDepartTo());
        Assertions.assertEquals("Melbourne", flight.getDepartFrom());
        Assertions.assertEquals("JS123", flight.getCode());
        Assertions.assertEquals("Jetstar", flight.getCompany());
        Assertions.assertEquals(dateFrom, flight.getDateFrom());
        Assertions.assertEquals(dateTo, flight.getDateTo());
        Assertions.assertEquals(mockAirplane, flight.getAirplane());

        // Test invalid flight creation with missing required fields
        Assertions.assertThrows(IllegalArgumentException.class, Flight::new);
    }

    @Test
    public void testInvalidDateFormat() {
        // Test invalid date format
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1, "Canberra", "Perth", "VA123", "Virgin Australia",
                    Timestamp.valueOf("2023-05-01 12:00:00"), Timestamp.valueOf("2023-05-01"), mockAirplane);
        });
    }

    @Test
    public void testInvalidTimeFormat() {
        // Test invalid time format
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Flight(1, "Sydney", "Perth", "JS123", "Jetstar",
                    Timestamp.valueOf("2023-05-01"), Timestamp.valueOf("12:00:00"), mockAirplane);
        });
    }

    @Test
    public void testAddFlight() {
        // Create a new flight to add
        Flight newFlight = new Flight(2, "Brisbane", "Sydney", "VA002",
                "Virgin Australia", new Timestamp(Calendar.getInstance().getTimeInMillis()),
                new Timestamp(Calendar.getInstance().getTimeInMillis()), mockAirplane);

        // Verify that the flight is not already in the system
        assertFalse(flight.equals(newFlight));

        // Add the flight to the system
        // ...implementation of adding the flight to the system...
    }

}
