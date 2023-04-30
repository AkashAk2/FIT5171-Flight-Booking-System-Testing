public class BuyTicketResult {
    private boolean successfulPurchase;
    private Ticket ticket;

    public BuyTicketResult(boolean successfulPurchase, Ticket ticket) {
        this.successfulPurchase = successfulPurchase;
        this.ticket = ticket;
    }

    public boolean isPurchased() {
        return successfulPurchase;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
