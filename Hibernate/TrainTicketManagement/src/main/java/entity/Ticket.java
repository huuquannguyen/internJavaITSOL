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
    private String category;
    private double price;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<BuyingRecord> buyingRecords;
    @Transient
    private Scanner input = new Scanner(System.in);

    public Ticket() {
//        try {
//            this.id = String.valueOf(nextId).substring(1);
//            nextId++;
//            if(nextId > 999999){
//                throw new RuntimeException("Limited customer");
//            }
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("Fill in ticket information:");
        System.out.print("Category: ");
        this.category  = input.nextLine();
        System.out.print("Price: ");
        this.price = AppUtil.type(input, 1, 10000);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
