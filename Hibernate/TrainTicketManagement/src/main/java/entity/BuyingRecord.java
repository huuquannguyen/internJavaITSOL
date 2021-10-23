package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BuyingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_BR_ID")
    @SequenceGenerator(name = "GEN_BR_ID", sequenceName = "SEQ_BR", allocationSize = 1)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Ticket ticket;
    private int quantity;

    public BuyingRecord(Customer customer, Ticket ticket, int quantity) {
        this.customer = customer;
        this.ticket = ticket;
        this.quantity = quantity;
    }

    public BuyingRecord() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
