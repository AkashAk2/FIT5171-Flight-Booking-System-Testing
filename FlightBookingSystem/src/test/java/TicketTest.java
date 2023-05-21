import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// This class is responsible for testing the Ticket class.
class TicketTest {
    private Flight mockFlight;
    private Passenger mockPassenger;
    private Ticket ticket;

    // Set up method to run before each test case.
    @BeforeEach
    void setUp() {
        mockFlight = Mockito.mock(Flight.class);
        mockPassenger = Mockito.mock(Passenger.class);
        when(mockPassenger.getAge()).thenReturn(30);

        this.ticket = new Ticket(1, 1000, mockFlight, true, mockPassenger);
    }

    // Test case for checking the ticket status.
    @Test
    void testTicketStatus() {
        assertFalse(this.ticket.getTicketStatus());
        this.ticket.setTicketStatus(true);
        assertTrue(this.ticket.getTicketStatus());
    }

    // Test case for toggling the ticket status between true and false.
    @Test
    void testTicketStatusTrueFalse() {
        this.ticket.setTicketStatus(true);
        assertTrue(this.ticket.getTicketStatus());

        this.ticket.setTicketStatus(false);
        assertFalse(this.ticket.getTicketStatus());
    }

    // Test case for checking the ticket discount based on passenger's age.
    @Test
    void testTicketDiscount() {
    }


    // Test case for checking the ticket price.
    @Test
    void testTicketPrice() {
        assertEquals(1000, this.ticket.getPrice());
    }

    // Test case for checking if an exception is thrown when an invalid ticket price is set.
    @Test
    void testTicketPriceValid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> this.ticket.setPrice(-100));
        String expectedMessage = "Price cannot be negative";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test case for checking ticket price and service tax.
    @Test
    void testTicketPriceAndServiceTax() {
    }

    // Test case for checking if service tax is appliedd
    @Test
    void testServiceTaxApplied() {
        this.ticket.setPrice(1000);
        assertEquals(1120, this.ticket.getPrice());
    }
    // Test case for checking the ticket service tax calculation.
    @Test
    void testTicketServiceTax() {
    }

    // Test case for checking if a ticket has a valid flight and passenger.
    @Test
    void testTicketValidFlightAndPassenger() {
        assertEquals(mockFlight, this.ticket.getFlight());
        assertEquals(mockPassenger, this.ticket.getPassenger());
    }

    @Test
        // Test case for checking the ticket ID.
    void testTicketID() {
        Ticket ticket = new Ticket(7890, 1000, new Flight(), false, mockPassenger);
        int expectedId = 7890;
        int actualId = ticket.getTicket_id();
        assertEquals(expectedId, actualId);
    }
}