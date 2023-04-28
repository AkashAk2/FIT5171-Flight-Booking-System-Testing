import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TicketCollectionTest {

    public Flight mockFlight;
    public Passenger mockPassenger;
    public Ticket mockTicket;

    @BeforeEach
    void setUp() {
        mockFlight = new Flight(); // Create a mock Flight object
        mockPassenger = new Passenger(); // Create a mock Passenger object
        mockTicket = new Ticket(1, 1000, mockFlight, true, mockPassenger); // Create a mock Ticket object
    }

    @BeforeEach
    void cleanTicketCollection() {
        TicketCollection.getTickets().clear();
    }


    @Test
    void testAddTickets() {
        System.out.println("TicketCollection size before adding tickets: " + TicketCollection.getTickets().size());
        TicketCollection.addTickets(new ArrayList<>());
        assertTrue(TicketCollection.getTickets().isEmpty());

        ArrayList<Ticket> ticketsToAdd = new ArrayList<>();
        ticketsToAdd.add(mockTicket);
        TicketCollection.addTickets(ticketsToAdd);
        System.out.println("TicketCollection size after adding tickets: " + TicketCollection.getTickets().size());
        assertFalse(TicketCollection.getTickets().isEmpty());
        assertEquals(1, TicketCollection.getTickets().size());
        assertEquals(mockTicket, TicketCollection.getTickets().get(0));
    }


    @Test
    void testGetTicketInfo() {
        assertNull(TicketCollection.getTicketInfo(1));

        ArrayList<Ticket> ticketsToAdd = new ArrayList<>();
        ticketsToAdd.add(mockTicket);
        TicketCollection.addTickets(ticketsToAdd);
        assertNotNull(TicketCollection.getTicketInfo(1));
        assertEquals(mockTicket, TicketCollection.getTicketInfo(1));
    }
}
