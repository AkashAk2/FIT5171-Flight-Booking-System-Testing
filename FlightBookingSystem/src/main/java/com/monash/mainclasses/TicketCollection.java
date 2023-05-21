package com.monash.mainclasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of tickets with methods for accessing and modifying the collection.
 */
public class TicketCollection {

    public static List<Ticket> tickets = new ArrayList<>();


    /**
     * Returns the list of all tickets.
     *
     * @return The list of all tickets
     */
    public static List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Adds a list of tickets to the collection.
     *
     * @param ticketsDb The list of tickets to add
     */
    public static void addTickets(List<Ticket> ticketsDb) {
        TicketCollection.tickets.addAll(ticketsDb);
    }

    /**
     * Returns the list of all available tickets.
     *
     * @return The list of all available tickets
     */
    public static List<Ticket> getAllTickets() {
        ArrayList<Ticket> availableTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (!ticket.getTicketStatus()) { // Only add available tickets
                availableTickets.add(ticket);
            }
        }
        return availableTickets;
    }

    /**
     * Returns the ticket information for a given ticket ID.
     *
     * @param ticketId The ID of the ticket to search for
     * @return The ticket with the given ID or null if not found
     */
    public Ticket getTicketInfo(int ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.ticketId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }


}