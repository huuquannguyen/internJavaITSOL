package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BuyingRecord implements Serializable {
    @Id
    @ManyToOne
    private Customer customer;
    @Id
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

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public void setTicket(Ticket ticket) {
//        this.ticket = ticket;
//    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addRecord(Customer customer, Ticket ticket){
        this.customer = customer;
        this.ticket = ticket;
        customer.getBuyingRecords().add(this);
        ticket.getBuyingRecords().add(this);
    }

    @Override
    public String toString() {
        return "BuyingRecord{" +
                "customer=" + customer +
                ", ticket=" + ticket +
                ", quantity=" + quantity +
                '}';
    }
}
