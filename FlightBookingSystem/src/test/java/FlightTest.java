import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// This class is responsible for testing the Flight class.
public class FlightTest {
    private Airplane mockAirplane;
    private Flight flight1;
    private Flight flight2;

    @BeforeEach
    void setUp() {
        mockAirplane = Mockito.mock(Airplane.class);
        flight1 = new Flight(1, "Melbourne", "Sydney", "QF400", "Qantas", "29/04/23", "12:00:00", "30/04/23", "14:00:00", mockAirplane);
        flight2 = new Flight(2, "Sydney", "Melbourne", "QF401", "Qantas", "28/04/23", "14:00:00", "29/04/23", "16:00:00", mockAirplane);
    }

    // Test that all fields are required and not null.
    @Test
    public void testAllFieldsRequired() {
        // Testing if any of the field is null
        Assertions.assertNotNull(flight1.getFlightID());
        Assertions.assertNotNull(flight1.getDepartTo());
        Assertions.assertNotNull(flight1.getDepartFrom());
        Assertions.assertNotNull(flight1.getCode());
        Assertions.assertNotNull(flight1.getCompany());
        Assertions.assertNotNull(flight1.getDateFrom());
        Assertions.assertNotNull(flight1.getTimeFrom());
        Assertions.assertNotNull(flight1.getDateTo());
        Assertions.assertNotNull(flight1.getTimeTo());
        Assertions.assertNotNull(flight1.getAirplane());
    }

    // Test that the date format is valid.
    @Test
    public void testDateFormat() {
        String dateTo = flight1.getDateTo();
        String dateFrom = flight1.getDateFrom();

        // using regex to check the pattern
        String pattern = "\\d{2}/\\d{2}/\\d{2}";

        assertTrue(dateTo.matches(pattern));
        assertTrue(dateFrom.matches(pattern));
    }

    // Test that the time format is valid.
    @Test
    public void testTimeFormat() {
        String timeTo = flight1.getTimeTo();
        String timeFrom = flight1.getTimeFrom();

        // using regex to check the pattern
        String pattern = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";

        assertTrue(timeTo.matches(pattern));
        assertTrue(timeFrom.matches(pattern));
    }

    // Test that two flights are unique.
    @Test
    public void testUniqueFlight() {
        Assertions.assertNotEquals(flight1, flight2);
    }

    // Test the toString() method of the Flight class.
    @Test
    public void testToString() {

        Flight flight = new Flight(1, "Melbourne", "Sydney", "QF400", "Qantas", "29/04/23", "12:00:00", "30/04/23", "14:00:00", mockAirplane);

        String expectedString = "Flight{" +
                "flightID=1" +
                ", departTo='Melbourne'" +
                ", departFrom='Sydney'" +
                ", code='QF400'" +
                ", company='Qantas'" +
                ", dateFrom='29/04/23'" +
                ", timeFrom='12:00:00'" +
                ", dateTo='30/04/23'" +
                ", timeTo='14:00:00'" +
                ", airplane=" + mockAirplane.toString() +
                '}';

        String actualString = flight.toString();

        Assertions.assertEquals(expectedString, actualString);
    }

    // Integration test to verify assigning an airplane to a flight.
    @Test
    void testAssignAirplaneToFlight() {
        // Set up
        Airplane mockAirplane = Mockito.mock(Airplane.class);
        Flight flight = new Flight(2, "Sydney", "Melbourne", "QF401", "Qantas", "28/04/23", "14:00:00", "29/04/23", "16:00:00", mockAirplane);

        // Assign the airplane to the flight
        flight.setAirplane(mockAirplane);

        // Verify that the airplane is assigned to the flight
        assertEquals(mockAirplane, flight.getAirplane());
    }
    // Integration test to verify updating the assigned airplane for a flight.
    @Test
    void testUpdateAssignedAirplane() {
        // Set up
        Airplane initialAirplane = new Airplane(
                1,
                "Boeing 737",
                30,
                150,
                10);
        Airplane updatedAirplane = new Airplane(
                2,
                "Boeing 606",
                40,
                160,
                15);
        Flight flight = new Flight(1, "Melbourne", "Sydney", "QF400", "Qantas", "29/04/23", "12:00:00", "30/04/23", "14:00:00", mockAirplane);

        // Assign the initial airplane to the flight
        flight.setAirplane(initialAirplane);

        // Update the assigned airplane
        flight.setAirplane(updatedAirplane);

        // Verify that the airplane is updated in the flight
        assertEquals(updatedAirplane, flight.getAirplane());
    }
}