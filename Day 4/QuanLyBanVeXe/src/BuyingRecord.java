import java.time.LocalDate;

public class BuyingRecord {
    private Passenger passenger;
    private Ticket ticket;
    private LocalDate date;

    public BuyingRecord(Passenger passenger, Ticket ticket){
        this.passenger = passenger;
        this.ticket = ticket;
        this.date = LocalDate.now();
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LocalDate getDate() {
        return date;
    }  

    @Override
    public String toString() {
        return passenger.getId() + " | " + passenger.getName() + " | " + ticket.getDescription() + " | " + date;
    }
}
