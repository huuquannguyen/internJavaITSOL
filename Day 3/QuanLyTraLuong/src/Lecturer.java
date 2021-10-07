import java.util.Scanner;

public class Lecturer {

    private static int nextId = 1001;
    
    private String id;
    private String name;
    private String address;
    private String phone;
    private String level;
    private Scanner input = new Scanner (System.in);;  

    

    public Lecturer() { 
        this.id = String.valueOf(nextId).substring(1);
        nextId++;
        if(nextId > 9999){
            throw new RuntimeException("Limited lecturer");
        }
        System.out.println("Fill lecturer information");
        System.out.print("Name: ");
        this.name = input.nextLine();
        System.out.println("Level: ");
        System.out.println("1. GS-TS");
        System.out.println("2. PGS-TS");
        System.out.println("3. Giang vien chinh");
        System.out.println("4. Th.S");
        int level = (int) App.type(input, 1, 4);
        switch(level){
            case 1:
                this.level = "GS-TS";
                break;
            case 2:
                this.level = "PGS-TS";
                break;
            case 3:
                this.level = "Giang vien chinh";
                break;
            case 4:
                this.level = "Th.S";
                break;
        }

        System.out.print("Address: ");
        this.address = input.nextLine();
        System.out.print("Phone: ");
        this.phone = input.next();
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
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + address + " - " + phone + " - " + level;
    }

}

