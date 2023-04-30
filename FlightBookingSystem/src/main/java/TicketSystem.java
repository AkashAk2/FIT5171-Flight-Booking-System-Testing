import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketSystem {
    Passenger passenger;
    Ticket ticket;
    Flight flight;
    Scanner in = new Scanner(System.in);

    public TicketSystem(){
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
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

    public void showTicket()
    {
        try
        {
            System.out.println("You have bought a ticket for flight " + ticket.getFlight().getDepartFrom() + " - " + ticket.getFlight().getDepartTo() + "\n\nDetails:");
            System.out.println(this.ticket.toString());
        }
        catch (NullPointerException e)
        {
            return;
        }
    }

    public BuyTicketResult buyTicket(int ticket_id) throws Exception
    //method for buying one ticket with direct flight
    {
        int flight_id = 0;

        //select ticket where ticket_id="+ticket_id"
        Ticket validTicket = TicketCollection.getTicketInfo(ticket_id);

        //if there is a valid ticket id was input then we buy it, otherwise show message
        if(validTicket == null)
        {
            System.out.println("This ticket does not exist.");
            return new BuyTicketResult(false, null);
        }
        else{
            //select flight_id from ticket where ticket_id=" + ticket_id
            flight_id = validTicket.getFlight().getFlightID();

            try
            {
                System.out.println("Enter your First Name: ");
                String firstName = in.nextLine();
                passenger.setFirstName(firstName);


                System.out.println("Enter your Second name:");
                String secondName = in.nextLine();
                passenger.setSecondName(secondName); //setting passengers info

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

                System.out.println("Enter your phone number (+61):");
                String phoneNumber = in.nextLine();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.nextLine();
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = in.nextInt();
                if (purch == 0)
                {
                    return new BuyTicketResult(false, null);
                } else
                {

                    flight = FlightCollection.getFlightInfo(flight_id);
                    int airplane_id = flight.getAirplane().getAirplaneID();
                    Airplane airplane = Airplane.getAirPlaneInfo(airplane_id);

                    ticket = TicketCollection.getTicketInfo(ticket_id);

                    ticket.setPassenger(passenger);
                    ticket.setTicket_id(ticket_id);
                    ticket.setFlight(flight);
                    ticket.setPrice(ticket.getPrice());
                    ticket.setClassVip(ticket.getClassVip());
                    ticket.setTicketStatus(true);
                    if (ticket.getClassVip())
                    {
                        airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
                    } else
                    {
                        airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
                    }

                }
                System.out.println("Your bill: " + ticket.getPrice() + "\n");

                System.out.println("Enter your card number:");
                String cardNumber = in.nextLine();
                passenger.setCardNumber(cardNumber);

                System.out.println("Enter your security code:");
                int securityCode = in.nextInt();
                passenger.setSecurityCode(securityCode);

                return new BuyTicketResult(true, ticket);
            } catch (PatternSyntaxException patternException)
            {
                patternException.printStackTrace();
                return new BuyTicketResult(false, null);
            }
        }
    }

    public void buyTicket(int ticket_id_first, int ticket_id_second) throws Exception{
        //method for buying two tickets with transfer flight
        int flight_id_first = 0;

        int flight_id_second = 0;


        System.out.println(ticket_id_first + " " + ticket_id_second);

        Ticket validTicketfirst = TicketCollection.getTicketInfo(ticket_id_first);

        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_first);


        System.out.println("Processing...");

        //if there is a valid ticket id was input then we buy it, otherwise show message

        if(validTicketfirst!=null || validTicketSecond!=null)
        {
            System.out.println("This ticket does not exist.");
            return;
        }

        else
        {
            flight_id_first = validTicketfirst.getFlight().getFlightID();

            flight_id_second = validTicketfirst.getFlight().getFlightID();


            try
            {
                System.out.println("Enter your First Name: ");
                String firstName = "";
                passenger.setFirstName(firstName);


                System.out.println("Enter your Second name:");
                String secondName = "";
                passenger.setSecondName(secondName); //setting passengers info

                System.out.println("Enter your age:");
                int age = 0;
                in.nextLine();
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = "";
                //passenger.setGender(gender));

                System.out.println("Enter your e-mail address");
                String email = "";
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = "";
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = "";
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = in.nextInt();
                if (purch == 0)
                {
                    return;
                }
                else
                {

                    //  "select * from flight, airplane where flight_id=" + flight_id_first + " and flight.airplane_id=airplane.airplane_id");
                    Flight flight_first = FlightCollection.getFlightInfo(flight_id_first);

                    int airplane_id_first = flight_first.getAirplane().getAirplaneID();

                    Airplane airplane_first = Airplane.getAirPlaneInfo(airplane_id_first);

                    Flight flight_second = FlightCollection.getFlightInfo(flight_id_second);

                    int airplane_id_second = flight_second.getAirplane().getAirplaneID();

                    Airplane airpairplane_second  = Airplane.getAirPlaneInfo(airplane_id_second);

                    Ticket ticket_first = TicketCollection.getTicketInfo(ticket_id_first);

                    Ticket ticket_second = TicketCollection.getTicketInfo(ticket_id_second);

                    ticket_first.setPassenger(passenger);
                    ticket_first.setTicket_id(ticket_id_first);
                    ticket_first.setFlight(flight_first);
                    ticket_first.setPrice(ticket_first.getPrice());
                    ticket_first.setClassVip(ticket_first.getClassVip());
                    ticket_first.setTicketStatus(true);

                    if (ticket_first.getClassVip() == true)
                    {
                        airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                    } else
                    {
                        airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);
                    }

                    System.out.println("--*-*-");

                    ticket_second.setPassenger(passenger);
                    ticket_second.setTicket_id(ticket_id_second);
                    ticket_second.setFlight(flight_first);
                    ticket_second.setPrice(ticket_second.getPrice());
                    ticket_second.setClassVip(ticket_second.getClassVip());
                    ticket_second.setTicketStatus(true);
                    if (ticket_second.getClassVip() == true)
                    {
                        airpairplane_second.setBusinessSitsNumber(airpairplane_second.getBusinessSitsNumber() - 1);
                    } else
                    {
                        airpairplane_second.setEconomySitsNumber(airpairplane_second.getEconomySitsNumber() - 1);
                    }

                    System.out.println("--*-*-");

                    ticket.setPrice(ticket_first.getPrice() + ticket_second.getPrice());

                    System.out.println("Your bill: " + ticket.getPrice() + "\n");

                    System.out.println("Enter your card number:");

                    String cardNumber = "";
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    int securityCode = 0;
                    passenger.setSecurityCode(securityCode);

                }
            } catch (PatternSyntaxException patternException)
            {
                patternException.printStackTrace();
            }
        }

    }
    public ArrayList<Ticket> chooseTicket(String city1, String city2) throws Exception
    {
        ArrayList<Ticket> chosenTickets = new ArrayList<>();
        int counter = 1;

        //search for direct flight from city1 to city2
        flight = FlightCollection.getFlightInfo(city1, city2);

        if(flight != null) {

            TicketCollection.getAllTickets();

            System.out.println("\nEnter ID of ticket you want to choose:");

            int ticket_id = in.nextInt();

            //validate ticker here

            //buy ticket here
            BuyTicketResult result = buyTicket(ticket_id);
            if (result.isPurchased()) {
                chosenTickets.add(result.getTicket());
            }
        }
        else
        //in case there is no direct ticket from city1 to city2
        {
            //SELECT a flight where depart_to = city2

            Flight depart_to = FlightCollection.getFlightInfo(city2);

            if (depart_to != null) {
                // Search for a flight with depart_from as connector city
                String connectCity = depart_to.getDepartFrom();

                // Search for flights where depart_to = connectCity and depart_from = city1
                Flight flightConnectingTwoCities = FlightCollection.getFlightInfo(city1, connectCity);

                if (flightConnectingTwoCities != null) {
                    System.out.println("There is a special way to go there. It is a transfer way.");

                    int idFirst = depart_to.getFlightID();
                    int idSecond = flightConnectingTwoCities.getFlightID();

                    buyTicket(idFirst, idSecond); // Pass two tickets and buy them

                    // Add the bought tickets to the chosenTickets list
                    chosenTickets.add(ticket);
                    chosenTickets.add(ticket);
                } else {
                    System.out.println("There are no possible variants.");
                }
            } else {
                System.out.println("There are no available flights.");
            }
        }
        return chosenTickets;
    }
}
