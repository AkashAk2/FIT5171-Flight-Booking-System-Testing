package com.monash.mainclasses;

import java.util.Scanner;
import java.util.logging.Logger;

public class TicketSystem {
    FlightCollection flightCollection;
    TicketCollection ticketCollection;
    Passenger passenger;
    Ticket ticket;
    Flight flight;
    Scanner in = new Scanner(System.in);

    /**
     * Default constructor for the com.monash.fit5171.TicketSystem class.
     */
    public TicketSystem() {
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
        in = new Scanner(System.in);
    }

    /**
     * Constructor for the com.monash.fit5171.TicketSystem class with com.monash.fit5171.FlightCollection parameter.
     *
     * @param flightCollection The com.monash.fit5171.FlightCollection object for this com.monash.fit5171.TicketSystem
     */
    public TicketSystem(FlightCollection flightCollection) {
        this.passenger = new Passenger();
        this.ticket = new Ticket();
        this.flight = new Flight();
        this.in = new Scanner(System.in);
        this.flightCollection = flightCollection;
    }

    /**
     * Constructor for the com.monash.fit5171.TicketSystem class with com.monash.fit5171.FlightCollection and com.monash.fit5171.TicketCollection parameters.
     *
     * @param flightCollection The com.monash.fit5171.FlightCollection object for this com.monash.fit5171.TicketSystem
     * @param ticketCollection The com.monash.fit5171.TicketCollection object for this com.monash.fit5171.TicketSystem
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

    private static final Logger logger = Logger.getLogger(TicketSystem.class.getName());


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

        FlightCollection newFlightCollection = new FlightCollection();
        Flight directFlight = newFlightCollection.getFlightInfo(city1, city2);

        if (directFlight != null) {
            TicketCollection.getAllTickets();

            logger.info("Enter ID of the ticket you want to choose:");
            int ticketId = in.nextInt();

            this.buyTicket(ticketId);
        } else {
            Flight departTo = FlightCollection.getFlightInfo(city2);

            if (departTo != null) {
                String connectCity = departTo.getDepartFrom();

                Flight flightConnectingTwoCities = newFlightCollection.getFlightInfo(city1, connectCity);

                if (flightConnectingTwoCities != null) {
                    logger.info("There is a transfer way to go there.");

                    idFirst = departTo.getFlightID();
                    idSecond = flightConnectingTwoCities.getFlightID();

                    this.buyTicket(idFirst, idSecond);
                } else {
                    logger.info("There is no possible variants.");
                }
            } else {
                logger.info("There is no possible variants.");
            }
        }
    }

    /**
     * Shows the ticket information for a purchased ticket.
     */
    public void showTicket() {
        try {
            logger.info("You have bought a ticket for flight " + ticket.flight.getDepartFrom() + " - " + ticket.flight.getDepartTo());
        } catch (NullPointerException e) {
        }
    }

    /**
     * Checks if a ticket is booked.
     *
     * @param ticketId The ID of the ticket to check
     * @return true if the ticket is booked, false otherwise
     */
    public boolean isTicketBooked(int ticketId) {

        Ticket selectedTicket = ticketCollection.getTicketInfo(ticketId);
        return selectedTicket != null && selectedTicket.getPassenger() != null;
    }

    /**
     * Buys a ticket with the given ticket ID.
     *
     * @param ticketId The ID of the ticket to purchase
     * @throws Exception If the ticket is already booked
     */
    public void buyTicket(int ticketId) throws IllegalArgumentException {
        if (isTicketBooked(ticketId)) {
            throw new IllegalArgumentException("Invalid ticket ID: " + ticketId);
        }

        Ticket validTicket = ticketCollection.getTicketInfo(ticketId);

        if (validTicket == null) {
            logger.info("This ticket does not exist.");
            return;
        }


        getInputForPassengerDetails();

        int purch = in.nextInt();
        if (purch == 0) {
            return;
        }

        processTicketPurchase(validTicket);

        logger.info("Your bill: " + ticket.getPrice() + "\n");
        getInputForCardDetails();
    }

    /**
     * Buys tickets with the given ticket IDs for connecting flights.
     *
     * @param ticketIdFirst  The ID of the first ticket for the first flight
     * @param ticketIdSecond The ID of the second ticket for the second flight
     * @throws Exception If any of the tickets are already booked
     */
    public void buyTicket(int ticketIdFirst, int ticketIdSecond) throws IllegalArgumentException {

        Ticket validTicketfirst = ticketCollection.getTicketInfo(ticketIdFirst);
        Ticket validTicketSecond = ticketCollection.getTicketInfo(ticketIdSecond);

        logger.info("Processing...");

        if (validTicketfirst == null || validTicketSecond == null) {
            throw new IllegalArgumentException("This ticket does not exist:" + ticketIdFirst + "," + ticketIdSecond);
        }
        getInputForPassengerDetails();

        int purch = in.nextInt();
        if (purch == 0) {
            return;
        }

        processTransferTicketPurchase(validTicketfirst, validTicketSecond);
        logger.info("Your bill: " + ticket.getPrice() + "\n");
        getInputForCardDetails();
    }
    /**
     * Gets input from the user for passenger details and stores them in the passenger object.
     */
    private void getInputForPassengerDetails() {
        logger.info("Enter your First Name: ");
        String firstName = in.nextLine();
        passenger.setFirstName(firstName);

        logger.info("Enter your Second name:");
        String secondName = in.nextLine();
        passenger.setSecondName(secondName);

        logger.info("Enter your age:");
        int age = in.nextInt();
        in.nextLine();
        passenger.setAge(age);

        logger.info("Enter your gender: ");
        String gender = in.nextLine();
        passenger.setGender(gender);

        logger.info("Enter your e-mail address");
        String email = in.nextLine();
        passenger.setEmail(email);

        logger.info("Enter your phone number (+7):");
        String phoneNumber = in.nextLine();
        passenger.setPhoneNumber(phoneNumber);

        logger.info("Enter your passport number:");
        String passportNumber = in.nextLine();
        passenger.setPassport(passportNumber);

        logger.info("Do you want to purchase?\n 1-YES 0-NO");
    }

    /**
     * Gets input from the user for card details and stores them in the passenger object.
     */
    private void getInputForCardDetails() {
        logger.info("Enter your card number:");
        String cardNumber = in.nextLine();
        passenger.setCardNumber(cardNumber);

        logger.info("Enter your security code:");
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
        int airplaneId = flight.getAirplane().getAirplaneID();

        Airplane airplane = Airplane.getAirPlaneInfo(airplaneId);

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
        Flight flightFirst = FlightCollection.getFlightInfo(validTicketFirst.getFlight().getFlightID());
        assert flightFirst != null;
        int airplaneIdFirst = flightFirst.getAirplane().getAirplaneID();
        Airplane airplaneFirst = Airplane.getAirPlaneInfo(airplaneIdFirst);

        Flight flightSecond = FlightCollection.getFlightInfo(validTicketSecond.getFlight().getFlightID());
        assert flightSecond != null;
        int airplaneIdSecond = flightSecond.getAirplane().getAirplaneID();
        Airplane airplaneSecond = Airplane.getAirPlaneInfo(airplaneIdSecond);

        processIndividualTransferTicket(validTicketFirst, airplaneFirst, flightFirst);
        processIndividualTransferTicket(validTicketSecond, airplaneSecond, flightSecond);

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
