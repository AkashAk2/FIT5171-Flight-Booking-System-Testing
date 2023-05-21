package com.monash.testcases;

import com.monash.mainclasses.Flight;
import com.monash.mainclasses.Passenger;
import com.monash.mainclasses.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// This class is responsible for testing the com.monash.fit5171.Ticket class.
class TicketTest {
    private Flight flight;
    private Passenger passenger;
    private Ticket ticket;

    // Set up method to run before each test case.
    @BeforeEach
    void setUp() {
        flight = Mockito.mock(Flight.class);
        passenger = Mockito.mock(Passenger.class);
        when(passenger.getAge()).thenReturn(30);

        this.ticket = new Ticket(1, 1000, flight, true, passenger);
    }

    // Test case for toggling the ticket status between true and false.
    @Test
    void testTicketStatusTrueFalse() {
        this.ticket.setTicketStatus(true);
        assertTrue(this.ticket.getTicketStatus());

        this.ticket.setTicketStatus(false);
        assertFalse(this.ticket.getTicketStatus());
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

    // Test case for checking if service tax is appliedd
    @Test
    void testServiceTaxApplied() {
        this.ticket.setPrice(1000);
        assertEquals(1120, this.ticket.getPrice());
    }
    // Test case for checking if a ticket has a valid flight and passenger.
    @Test
    void testTicketValidFlightAndPassenger() {
        assertEquals(flight, this.ticket.getFlight());
        assertEquals(passenger, this.ticket.getPassenger());
    }

    @Test
        // Test case for checking the ticket ID.
    void testTicketID() {
        Ticket ticket = new Ticket(7890, 1000, new Flight(), false, passenger);
        int expectedId = 7890;
        int actualId = ticket.ticketId();
        assertEquals(expectedId, actualId);
    }

    @Test
    void testTicketStatus() {
        Ticket ticket = new Ticket(1, 1000, flight, false, passenger);
        assertFalse(ticket.ticketStatus());
        ticket.setTicketStatus(true);
        assertTrue(ticket.ticketStatus());
    }

    @Test
    void testValidPriceAndServiceTax() {
        Mockito.when(passenger.getAge()).thenReturn(30);
        Ticket ticket = new Ticket(4, 1000, flight, false, passenger);
        assertTrue(ticket.getPrice() >= 0);
        assertTrue(ticket.getPrice() % 1 == 0); // Check if the price is an integer
    }

    @Test
    void testValidFlightAndPassengerInfo() {
        Ticket ticket = new Ticket(6, 1000, flight, false, passenger);
        assertNotNull(ticket.getFlight());
        assertNotNull(ticket.getPassenger());
    }
}