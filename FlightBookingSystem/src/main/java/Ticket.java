public class Ticket
{
    private int ticket_id;
    private int price;
    Flight flight;
    private boolean classVip; //indicates if this is bussiness class ticket or not
    private boolean status; //indicates status of ticket: if it is bought by someone or not
    Passenger passenger;

    public Ticket(int ticket_id,int price, Flight flight, boolean classVip, Passenger passenger)
    {
        this.ticket_id=ticket_id;
        this.flight = flight;
        this.classVip = classVip;
        this.status = false;
        this.passenger=passenger;
//        setPrice(price);
        this.price = applyTaxAndDiscount(price, passenger.getAge());
    }

    public Ticket() {

    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getPrice() { return price; }

    public void setPrice(int price)
    {
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

    public void setTicketStatus(boolean status)
    {
        this.status = status;
    }

//    public void serviceTax(){
//        this.price *= 1.12;
//    } //12% service tax

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String toString()
    {
        return"Ticket{" +'\n'+
                "Price=" + getPrice() + "KZT, " + '\n' +
                getFlight() +'\n'+ "Vip status=" + getClassVip() + '\n' +
                getPassenger()+'\n'+ "Ticket was purchased=" + ticketStatus() + "\n}";
    }
}
