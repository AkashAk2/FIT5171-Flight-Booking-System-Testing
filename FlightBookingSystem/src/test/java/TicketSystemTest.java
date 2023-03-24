import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketSystemTest {
    TicketSystem ticketSystem;
    Passenger testPassenger;

    @BeforeEach
    void setUp() {
        ticketSystem = new TicketSystem();
        //testPassenger = new Passenger("John", "Wick", 38, "Male", "babayaga@gmail.com", "1234567890", "P1234567", "1234567812345678", 123);
    }

    /*
        ASSUMPTIONS:
            We are assuming that there is a BuyTicketResult class that acts as the return type of buyTicket().
            It contains a Boolean variable and the ticket object.
            If the ticket purchase is valid, the bool variable is set to true and a ticket object is saved and stored.
    */


    /*
        Test Case 1:
            This test case is to check the buyTicket() for a valid ticket id.
            If the Ticket is valid, the ticket will be purchased and the ticket object will be created and returned.
     */
    @Test
    void testValidBuyTicket() throws Exception {
        //  Assuming ticket with ID 1111 exists and is available.
        int ticketId = 1111;
        BuyTicketResult result = ticketSystem.buyTicket(ticketId);
        assertTrue(result.isPurchased());
        assertEquals(testPassenger, result.getTicket().getPassenger());
    }

    /*
        Test Case 2:
            This test case is to check the buyTicket() for an invalid ticket id.
            If the Ticket is invalid, the ticket will be not purchased and the ticket object will return Null.
     */
    @Test
    void testInvalidBuyTicket() throws Exception {
        // Assuming there is no ticket with ID 2222.
        int ticketId = 2222;
        BuyTicketResult result = ticketSystem.buyTicket(ticketId);
        assertFalse(result.isPurchased());
        assertNull(result.getTicket());
    }


}