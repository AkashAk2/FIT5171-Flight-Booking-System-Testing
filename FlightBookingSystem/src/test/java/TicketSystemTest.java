import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketSystemTest {
    TicketSystem ticketSystem;
    Passenger testPassenger;

    @BeforeEach
    void setUp() {
        ticketSystem = new TicketSystem();
    }

    /*
        ASSUMPTIONS:
            We are assuming that there is a BuyTicketResult class that acts as the return type of buyTicket().
            It contains a Boolean variable and the ticket object.
            If the ticket purchase is valid, the bool variable is set to true and a ticket object is saved and stored.
            We are assuming that there is a getter method called getPassenger() to return the passenger details.
            We are assuming that the buyTicket() returns BuyTicketResult object

        CLASS DEFINITION: BuyTicketResult

            public class BuyTicketResult {
                private boolean successfulPurchase;
                private Ticket ticket;

                public BuyTicketResult(boolean successfulPurchase, Ticket ticket) {
                    this.successfulPurchase = successfulPurchase;
                    this.ticket = ticket;
                }

                public boolean isPurchased() {
                    return successfulPurchase;
                }

                public Ticket getTicket() {
                    return ticket;
                }
            }
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
        assertEquals(ticketSystem.getPassenger(), result.getTicket().getPassenger());
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

    /*
        Test Case 3:
            This test case is to check the buyTicket() for an already purchased ticket.
            If the Ticket is already purchased, the ticket object will not be created and returns NULL.
     */
    @Test
    void testBuyTicketAlreadyPurchased() throws Exception {
        // Assuming the ticket with id:3333 exists and already purchased
        int ticketId = 3333;
        BuyTicketResult result = ticketSystem.buyTicket(ticketId);
        assertFalse(result.isPurchased());
        assertNull(result.getTicket());
    }

    /*
        Test Case 4:
            This test case is to check the buyTicket() for available seats.
            If the Ticket is valid but no seats are available the ticket object will be created without seat allocation.
     */
    @Test
    void testBuyTicketNoSeatsAvailable() throws Exception {
        // Assuming that there are no seats left but the ticket with id:4444 can be purchased by a passenger.
        int ticketId = 4444;
        BuyTicketResult result = ticketSystem.buyTicket(ticketId);
        assertTrue(result.isPurchased());
        assertNotNull(result.getTicket());
    }


}