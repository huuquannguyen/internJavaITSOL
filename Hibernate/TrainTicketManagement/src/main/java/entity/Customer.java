package entity;

import util.AppUtil;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
public class Customer {

    private static final String MUA_LE = "Mua le";
    private static final String MUA_TAP_THE = "Mua tap the";
    private static final String MUA_QUA_MANG = "Mua qua mang";

    private static int nextId = 100001;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ID_CUSTOMER")
    @SequenceGenerator(name = "GEN_ID_CUSTOMER", sequenceName = "SEQ_CUSTOMER", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String type;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BuyingRecord> buyingRecords;
    @Transient
    private Scanner input = new Scanner(System.in);

    public Customer() {
//        try {
//            this.id = String.valueOf(nextId).substring(1);
//            nextId++;
//            if(nextId > 999999){
//                throw new RuntimeException("Limited customer");
//            }
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("Fill in customer information:");
        System.out.print("Name: ");
        this.name = input.nextLine();
        System.out.print("Address: ");
        this.address = input.nextLine();
        System.out.print("Phone: ");
        this.phone = input.next();
        System.out.println("1. Mua le");
        System.out.println("2. Mua tap the");
        System.out.println("3. Mua online");
        System.out.print("Choose customer type: ");
        int option = (int) AppUtil.type(input,1,3);
        switch (option){
            case 1:
                this.type = MUA_LE;
                break;
            case 2:
                this.type = MUA_TAP_THE;
                break;
            case 3:
                this.type = MUA_QUA_MANG;
                break;
        }
    }

    public Long getId() {
            return id;
        }

    public String getName() {
            return name;
        }

    public String getAddress() {
            return address;
        }

    public String getPhone() {
            return phone;
        }

    public String getType() {
            return type;
        }

    public void setName(String name) {
            this.name = name;
        }

    public void setAddress(String address) {
            this.address = address;
        }

    public void setPhone(String phone) {
            this.phone = phone;
        }

    public void setType(String type) {
            this.type = type;
        }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
