import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        flight = Mockito.mock(Flight.class);
        passenger = Mockito.mock(Passenger.class);
    }

    @Test
    void testTicketStatus() {
        Ticket ticket = new Ticket(1, 1000, flight, false, passenger);
        assertFalse(ticket.ticketStatus());
        ticket.setTicketStatus(true);
        assertTrue(ticket.ticketStatus());
    }

    @Test
    void testDiscountBasedOnAge() {
        Mockito.when(passenger.getAge()).thenReturn(10);
        Ticket childTicket = new Ticket(1, 1000, flight, false, passenger);
        assertEquals(560, childTicket.getPrice()); // 50% discount + 12% service tax

        Mockito.when(passenger.getAge()).thenReturn(65);
        Ticket seniorTicket = new Ticket(2, 1000, flight, false, passenger);
        assertEquals(0, seniorTicket.getPrice()); // 100% discount for elder people
    }

    @Test
    void testPriceAppliedToTicket() {
        Mockito.when(passenger.getAge()).thenReturn(30);
        Ticket adultTicket = new Ticket(3, 1000, flight, false, passenger);
        assertEquals(1120, adultTicket.getPrice()); // No discount, only 12% service tax
    }

    @Test
    void testValidPriceAndServiceTax() {
        Mockito.when(passenger.getAge()).thenReturn(30);
        Ticket ticket = new Ticket(4, 1000, flight, false, passenger);
        assertTrue(ticket.getPrice() >= 0);
        assertTrue(ticket.getPrice() % 1 == 0); // Check if the price is an integer
    }

    @Test
    void testServiceTaxAppliedWhenTicketIsSold() {
        Mockito.when(passenger.getAge()).thenReturn(30);
        Ticket ticket = new Ticket(5, 1000, flight, false, passenger);
        assertEquals(1120, ticket.getPrice()); // 12% service tax applied
    }

    @Test
    void testValidFlightAndPassengerInfo() {
        Ticket ticket = new Ticket(6, 1000, flight, false, passenger);
        assertNotNull(ticket.getFlight());
        assertNotNull(ticket.getPassenger());
    }
}
