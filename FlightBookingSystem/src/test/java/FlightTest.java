import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    private Flight flight;
    private Flight flight1;
    private Flight flight2;
    private Airplane airplane;

    @BeforeEach
    public void setUp() throws ParseException {
        airplane = Mockito.mock(Airplane.class);

        int flightId = 1;
        String departTo = "Sydney";
        String departFrom = "Melbourne";
        String code = "VA720";
        String company = "Virgin Australia";
        Timestamp dateFrom = createTimestamp("12/08/2023 08:00:00");
        Timestamp dateTo = createTimestamp("12/08/2023 16:00:00");

        flight = new Flight(flightId, departTo, departFrom, code, company, dateFrom, dateTo, airplane);
        flight1 = new Flight(2, departTo, departFrom, code, company, dateFrom, dateTo, airplane);
        flight2 = new Flight(3, departTo, departFrom, code, company, dateFrom, dateTo, airplane);
    }

    private Timestamp createTimestamp(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = dateFormat.parse(dateString);
        return new Timestamp(date.getTime());
    }

    @Test
    public void testAllFieldsRequired() {
        assertNotNull(flight.getFlightID());
        assertNotNull(flight.getDepartTo());
        assertNotNull(flight.getDepartFrom());
        assertNotNull(flight.getCode());
        assertNotNull(flight.getCompany());
        assertNotNull(flight.getDateFrom());
        assertNotNull(flight.getDateTo());
        assertNotNull(flight.getAirplane());
    }

    @Test
    public void testDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals("12/08/2023", dateFormat.format(flight.getDateFrom()));
        assertEquals("12/08/2023", dateFormat.format(flight.getDateTo()));
    }

    @Test
    public void testTimeFormat() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        assertEquals("08:00:00", timeFormat.format(flight.getDateFrom()));
        assertEquals("16:00:00", timeFormat.format(flight.getDateTo()));
    }

    @Test
    void testNoDuplicateFlights() {
        FlightCollection.getFlights().clear();
        FlightCollection.addFlights(new ArrayList<Flight>() {{
            add(flight1);
            add(flight2);
        }});

        FlightCollection.addFlights(new ArrayList<Flight>() {{
            add(flight1); // attempt to add duplicate
        }});

        assertEquals(2, FlightCollection.getFlights().size());
        assertTrue(FlightCollection.getFlights().contains(flight1));
        assertTrue(FlightCollection.getFlights().contains(flight2));
    }


}