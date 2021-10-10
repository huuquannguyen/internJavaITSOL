import java.util.Scanner;

public class Passenger {
    private static int nextId = 100001;

    private String id;
    private String name;
    private String address;
    private String phone;
    private String type;
    private Scanner input = new Scanner(System.in);

    
    public Passenger(){
        try {
            this.id = String.valueOf(nextId).substring(1);
            nextId++;
            if(nextId > 999999){
                throw new RuntimeException("Limited ticket");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Fill in passenger information:");
        System.out.print("Name: ");
        this.name = input.nextLine();
        System.out.print("Address: ");
        this.address = input.nextLine();
        System.out.print("Phone: ");
        this.phone = input.next();
        System.out.println("Type: ");
        System.out.println("1. HSSV");
        System.out.println("2. Can bo");
        System.out.println("3. Thong thuong");
        int type = (int) App.type(input, 1, 3);
        switch(type){
            case 1:
                this.type = "HSSV";
                break;
            case 2:
                this.type = "Can bo";
                break;
            case 3:
                this.type = "Thong thuong";
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
