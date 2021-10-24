package entity;

import util.AppUtil;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
public class Ticket {
    private static int nextId = 100001;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_TICKET_ID")
    @SequenceGenerator(name = "GEN_TICKET_ID", sequenceName = "SEQ_TICKET", allocationSize = 1)
    private Long id;
    private String seatType;
    private double price;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuyingRecord> buyingRecords;

    public Ticket() {

    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Fill in ticket information:");
        System.out.print("Seat type: ");
        this.seatType  = sc.nextLine();
        System.out.print("Price: ");
        this.price = AppUtil.type(sc, 1, 10000);
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    public List<BuyingRecord> getBuyingRecords() {
        return buyingRecords;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", seatType='" + seatType + '\'' +
                ", price=" + price +
                '}';
    }
}
