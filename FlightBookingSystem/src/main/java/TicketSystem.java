import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketSystem {
    FlightCollection flightCollection;
    TicketCollection ticketCollection;
    Passenger passenger;
    Ticket ticket;
    Flight flight;
    Scanner in = new Scanner(System.in);

    /**
     * Default constructor for the TicketSystem class.
     */
    public TicketSystem() {
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
        in = new Scanner(System.in);
    }

    /**
     * Constructor for the TicketSystem class with FlightCollection parameter.
     *
     * @param flightCollection The FlightCollection object for this TicketSystem
     */
    public TicketSystem(FlightCollection flightCollection) {
        this.passenger = new Passenger();
        this.ticket = new Ticket();
        this.flight = new Flight();
        this.in = new Scanner(System.in);
        this.flightCollection = flightCollection;
    }

    /**
     * Constructor for the TicketSystem class with FlightCollection and TicketCollection parameters.
     *
     * @param flightCollection The FlightCollection object for this TicketSystem
     * @param ticketCollection The TicketCollection object for this TicketSystem
     */
    public TicketSystem(FlightCollection flightCollection, TicketCollection ticketCollection) {
        this.passenger = new Passenger();
        this.ticket = new Ticket();
        this.flight = new Flight();
        this.in = new Scanner(System.in);
        this.flightCollection = flightCollection;
        this.ticketCollection = ticketCollection;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Ticket getTicket(){
        return ticket;
    }

    public Flight getFlight(){
        return flight;
    }

    /**
     * Allows a user to choose a ticket based on the departure and arrival cities.
     *
     * @param city1 The departure city
     * @param city2 The arrival city
     * @throws Exception If the ticket is already booked
     */
    public void chooseTicket(String city1, String city2) throws Exception {
        int idFirst = 0;
        int idSecond = 0;

        FlightCollection flightCollection = new FlightCollection();
        Flight directFlight = flightCollection.getFlightInfo(city1, city2);

        if (directFlight != null) {
            TicketCollection.getAllTickets();

            System.out.println("\nEnter ID of ticket you want to choose:");
            int ticket_id = in.nextInt();

            this.buyTicket(ticket_id);
        } else {
            Flight depart_to = flightCollection.getFlightInfo(city2);

            if (depart_to != null) {
                String connectCity = depart_to.getDepartFrom();

                Flight flightConnectingTwoCities = flightCollection.getFlightInfo(city1, connectCity);

                if (flightConnectingTwoCities != null) {
                    System.out.println("There is a transfer way to go there.");

                    idFirst = depart_to.getFlightID();
                    idSecond = flightConnectingTwoCities.getFlightID();

                    this.buyTicket(idFirst, idSecond);
                } else {
                    System.out.println("There is no possible variants.");
                }
            } else {
                System.out.println("There is no possible variants.");
            }
        }
    }

    /**
     * Shows the ticket information for a purchased ticket.
     */
    public void showTicket() {
        try {
            System.out.println("You have bought a ticket for flight " + ticket.flight.getDepartFrom() + " - " + ticket.flight.getDepartTo() + "\n\nDetails:");
            System.out.println(this.ticket.toString());
        } catch (NullPointerException e) {
            return;
        }
    }

    /**
     * Checks if a ticket is booked.
     *
     * @param ticket_id The ID of the ticket to check
     * @return true if the ticket is booked, false otherwise
     */
    public boolean isTicketBooked(int ticket_id) {

        Ticket selectedTicket = ticketCollection.getTicketInfo(ticket_id);
        return selectedTicket != null && selectedTicket.getPassenger() != null;
    }

    /**
     * Buys a ticket with the given ticket ID.
     *
     * @param ticket_id The ID of the ticket to purchase
     * @throws Exception If the ticket is already booked
     */
    public void buyTicket(int ticket_id) throws Exception {
        if (isTicketBooked(ticket_id)) {
            throw new Exception("This ticket is already booked.");
        }

        Ticket validTicket = ticketCollection.getTicketInfo(ticket_id);

        if (validTicket == null) {
            System.out.println("This ticket does not exist.");
            return;
        }


        getInputForPassengerDetails();

        int purch = in.nextInt();
        if (purch == 0) {
            return;
        }

        processTicketPurchase(validTicket);

        System.out.println("Your bill: " + ticket.getPrice() + "\n");
        getInputForCardDetails();
    }

    /**
     * Buys tickets with the given ticket IDs for connecting flights.
     *
     * @param ticket_id_first  The ID of the first ticket for the first flight
     * @param ticket_id_second The ID of the second ticket for the second flight
     * @throws Exception If any of the tickets are already booked
     */
    public void buyTicket(int ticket_id_first, int ticket_id_second) throws Exception {

        Ticket validTicketfirst = ticketCollection.getTicketInfo(ticket_id_first);
        Ticket validTicketSecond = ticketCollection.getTicketInfo(ticket_id_second);

        System.out.println("Processing...");

        if (validTicketfirst == null || validTicketSecond == null) {
            System.out.println("This ticket does not exist.");
            return;
        }
        getInputForPassengerDetails();

        int purch = in.nextInt();
        if (purch == 0) {
            return;
        }

        processTransferTicketPurchase(validTicketfirst, validTicketSecond);
        System.out.println("Your bill: " + ticket.getPrice() + "\n");
        getInputForCardDetails();
    }
    /**
     * Gets input from the user for passenger details and stores them in the passenger object.
     */
    private void getInputForPassengerDetails() {
        System.out.println("Enter your First Name: ");
        String firstName = in.nextLine();
        passenger.setFirstName(firstName);

        System.out.println("Enter your Second name:");
        String secondName = in.nextLine();
        passenger.setSecondName(secondName);

        System.out.println("Enter your age:");
        int age = in.nextInt();
        in.nextLine();
        passenger.setAge(age);

        System.out.println("Enter your gender: ");
        String gender = in.nextLine();
        passenger.setGender(gender);

        System.out.println("Enter your e-mail address");
        String email = in.nextLine();
        passenger.setEmail(email);

        System.out.println("Enter your phone number (+7):");
        String phoneNumber = in.nextLine();
        passenger.setPhoneNumber(phoneNumber);

        System.out.println("Enter your passport number:");
        String passportNumber = in.nextLine();
        passenger.setPassport(passportNumber);

        System.out.println("Do you want to purchase?\n 1-YES 0-NO");
    }

    /**
     * Gets input from the user for card details and stores them in the passenger object.
     */
    private void getInputForCardDetails() {
        System.out.println("Enter your card number:");
        String cardNumber = in.nextLine();
        passenger.setCardNumber(cardNumber);

        System.out.println("Enter your security code:");
        int securityCode = in.nextInt();
        passenger.setSecurityCode(securityCode);
    }

    /**
     * Processes the purchase of a single ticket and updates airplane seat availability.
     *
     * @param validTicket The ticket to be processed
     */
    private void processTicketPurchase(Ticket validTicket) {
        flight = FlightCollection.getFlightInfo(validTicket.getFlight().getFlightID());

        assert flight != null;
        int airplane_id = flight.getAirplane().getAirplaneID();

        Airplane airplane = Airplane.getAirPlaneInfo(airplane_id);

        ticket = validTicket;

        ticket.setPassenger(passenger);
        ticket.setTicketStatus(true);

        if (ticket.getClassVip()) {
            airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
        } else {
            airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
        }
    }

    /**
     * Processes the purchase of tickets for connecting flights and updates airplane seat availability.
     *
     * @param validTicketFirst  The first ticket to be processed
     * @param validTicketSecond The second ticket to be processed
     */
    private void processTransferTicketPurchase(Ticket validTicketFirst, Ticket validTicketSecond) {
        Flight flight_first = FlightCollection.getFlightInfo(validTicketFirst.getFlight().getFlightID());
        assert flight_first != null;
        int airplane_id_first = flight_first.getAirplane().getAirplaneID();
        Airplane airplane_first = Airplane.getAirPlaneInfo(airplane_id_first);

        Flight flight_second = FlightCollection.getFlightInfo(validTicketSecond.getFlight().getFlightID());
        assert flight_second != null;
        int airplane_id_second = flight_second.getAirplane().getAirplaneID();
        Airplane airplane_second = Airplane.getAirPlaneInfo(airplane_id_second);

        processIndividualTransferTicket(validTicketFirst, airplane_first, flight_first);
        processIndividualTransferTicket(validTicketSecond, airplane_second, flight_second);

        ticket.setPrice(validTicketFirst.getPrice() + validTicketSecond.getPrice());
    }

    /**
     * Processes the purchase of an individual ticket for a connecting flight and updates airplane seat availability.
     *
     * @param transferTicket The ticket to be processed
     * @param airplane       The airplane associated with the ticket
     * @param flight         The flight associated with the ticket
     */
    private void processIndividualTransferTicket(Ticket transferTicket, Airplane airplane, Flight flight) {
        transferTicket.setPassenger(passenger);
        transferTicket.setTicketStatus(true);

        if (transferTicket.getClassVip()) {
            airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
        } else {
            airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
        }
    }
}
