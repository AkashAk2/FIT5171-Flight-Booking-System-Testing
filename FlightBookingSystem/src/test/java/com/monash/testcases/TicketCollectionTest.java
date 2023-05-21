package com.monash.testcases;

import com.monash.mainclasses.Flight;
import com.monash.mainclasses.Passenger;
import com.monash.mainclasses.Ticket;
import com.monash.mainclasses.TicketCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;

public class TicketCollectionTest {

    Flight flight;
    Passenger passenger;

    @BeforeEach
    public void setUp() {
        flight = Mockito.mock(Flight.class);
        passenger = Mockito.mock(Passenger.class);
        TicketCollection.tickets = new ArrayList<>();
    }

    @Test
    public void testAddTicket_validTicket_success() {
        // Prepare data
        TicketCollection ticketCollection = new TicketCollection();
        Ticket ticket = new Ticket(1, 1000, flight, false, passenger);
        ArrayList<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        // Add ticket to collection
        TicketCollection.addTickets(ticketList);

        // Verify the ticket was added
        assertEquals(ticket, ticketCollection.getTicketInfo(1));
    }

    @Test
    public void testGetTicketInfo_validTicketId_success() {
        // Prepare data
        Ticket ticket1 = new Ticket(1, 1000, flight, false, passenger);
        Ticket ticket2 = new Ticket(2, 2000, flight, true, passenger);
        ArrayList<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        TicketCollection ticketCollection = new TicketCollection();
        // Add tickets to collection
        TicketCollection.addTickets(ticketList);

        // Get ticket by id
        Ticket fetchedTicket = ticketCollection.getTicketInfo(2);

        // Verify the correct ticket is returned
        assertEquals(ticket2, fetchedTicket);
    }

    @Test
    public void testGetTicketInfo_invalidTicketId_null() {
        // Prepare data
        Ticket ticket = new Ticket(1, 1000, flight, false, passenger);
        ArrayList<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);
        TicketCollection ticketCollection = new TicketCollection();
        // Add ticket to collection
        TicketCollection.addTickets(ticketList);

        // Get ticket by id
        Ticket fetchedTicket = ticketCollection.getTicketInfo(999);

        // Verify null is returned for invalid id
        assertNull(fetchedTicket);
    }
}