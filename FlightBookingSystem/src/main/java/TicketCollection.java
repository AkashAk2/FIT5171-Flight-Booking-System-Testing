import java.util.ArrayList;

/**
 * Represents a collection of tickets with methods for accessing and modifying the collection.
 */
public class TicketCollection {

    public static ArrayList<Ticket> tickets;

    /**
     * Returns the list of all tickets.
     *
     * @return The list of all tickets
     */
    public static ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Adds a list of tickets to the collection.
     *
     * @param tickets_db The list of tickets to add
     */
    public static void addTickets(ArrayList<Ticket> tickets_db) {
        TicketCollection.tickets.addAll(tickets_db);
    }

    /**
     * Returns the list of all available tickets.
     *
     * @return The list of all available tickets
     */
    public static ArrayList<Ticket> getAllTickets() {
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
     * @param ticket_id The ID of the ticket to search for
     * @return The ticket with the given ID or null if not found
     */
    public Ticket getTicketInfo(int ticket_id) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicket_id() == ticket_id) {
                return ticket;
            }
        }
        return null;
    }
}