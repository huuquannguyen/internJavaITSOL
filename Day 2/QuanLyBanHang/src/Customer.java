import java.util.Scanner;

public class Customer {

    private static int nextId = 100001;
    
    private String id;
    private String name;
    private String address;
    private String phone;
    private String type;

    public Customer(Scanner input) {
        this.id = String.valueOf(nextId).substring(1);
        nextId++;
        if(nextId > 999999){
            throw new RuntimeException("Limited customer");
        }
        input.nextLine();
        System.out.println("Fill customer information");
        System.out.print("Name: ");
        this.name = input.nextLine();
        System.out.print("Address: ");
        this.address = input.nextLine();
        System.out.print("Phone: ");
        this.phone = input.next();
        System.out.println("Type: ");
        System.out.println("1. Mua le");
        System.out.println("2. Mua buon");
        int type = App.type(input, 1, 2);
        switch(type){
            case 1:
                this.type = "Mua le";
                break;
            case 2:
                this.type = "Mua buon";
                break;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + address + " - " + phone + " - " + type;
    }
    
}
