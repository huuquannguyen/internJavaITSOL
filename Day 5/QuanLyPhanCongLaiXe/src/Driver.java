import java.util.Scanner;

public class Driver {
    private static int nextId = 100001;

    private String id;
    private String name;
    private String address;
    private String phone;
    private String level;
    private Scanner input = new Scanner(System.in);

    public Driver (){
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
        System.out.print("Level(A - F): ");
        char c = input.next().charAt(0);
        while(c < 65 || c > 71 ){
            System.out.print("Type a upper case character (A - F): ");
            c = input.next().charAt(0);
        }
        String level = String.valueOf(c);
        this.level = level;
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

    public String getlevel() {
        return level;
    }

    public void setlevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + address + " - " + phone + " - " + level;
    }
}
