package com.monash.mainclasses;

public class Ticket
{
    private int ticketId;
    private int price;
    Flight flight;
    private boolean classVip; //indicates if this is bussiness class ticket or not
    private boolean status; //indicates status of ticket: if it is bought by someone or not
    Passenger passenger;

    /**
     * Default constructor.
     */
    public Ticket() {
    }

    /**
     * Constructor with parameters.
     *
     * @param ticketId  The ticket ID
     * @param price      The price of the ticket
     * @param flight     The flight associated with the ticket
     * @param classVip   Indicates if the ticket is for business class or not
     * @param passenger  The passenger associated with the ticket
     */
    public Ticket(int ticketId, int price, Flight flight, boolean classVip, Passenger passenger) {
        this.ticketId = ticketId;
        this.price = price;
        this.flight = flight;
        this.classVip = classVip;
        this.status = false;
        this.passenger = passenger;
    }

    public int ticketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getPrice() { return price; }

    public void setPrice(int price)
    {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        int discountedPrice;
        discountedPrice = applyTaxAndDiscount(price, passenger.getAge());
        this.price = discountedPrice;
    }

    public int applyTaxAndDiscount(int price, int age) {
        int discountedPrice = price;
        if(age < 15) {
            discountedPrice -= (int)(price * 0.5); // 50% discount for passenger aged under 15.
        }
        else if(age >= 60) {
            discountedPrice = 0; // full discount.
        }
        discountedPrice = applyServiceTax(discountedPrice);
        return discountedPrice;
    }

    public int applyServiceTax(int price) {
        int priceAfterTax;
        priceAfterTax = (int) (price * 1.12);
        return priceAfterTax;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean getClassVip() {
        return classVip;
    }

    public void setClassVip(boolean classVip) {
        this.classVip = classVip;
    }

    public boolean ticketStatus()
    {
        return status;
    }

    public boolean getTicketStatus() {
        return status;
    }

    public void setTicketStatus(boolean status)
    {
        this.status = status;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String toString()
    {
        return"com.monash.fit5171.Ticket{" +'\n'+
                "Price=" + getPrice() + "KZT, " + '\n' +
                getFlight() +'\n'+ "Vip status=" + getClassVip() + '\n' +
                getPassenger()+'\n'+ "com.monash.fit5171.Ticket was purchased=" + ticketStatus() + "\n}";
    }
}
