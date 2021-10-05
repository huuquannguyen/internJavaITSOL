import java.util.Scanner;

public class Product {

    private static int nextId = 100001;
    
    private String id;
    private String name;
    private String group;
    private double price;

    public Product(Scanner input) {
        this.id = String.valueOf(nextId).substring(1);
        nextId++;
        if(nextId > 999999){
            throw new RuntimeException("Limited customer");
        }
        input.nextLine();
        System.out.println("Fill customer information");
        System.out.print("Name: ");
        this.name = input.nextLine();
        System.out.println("Group: ");
        System.out.println("1. Hang thoi trang");
        System.out.println("2. Hang tieu dung");
        System.out.println("3. Hang dien may");
        System.out.println("4. Hang gia dung");
        int groupNum = App.type(input, 1, 4);
        switch(groupNum){
            case 1:
                this.group = "Hang thoi trang";
                break;
            case 2:
                this.group = "Hang tieu dung";
                break;
            case 3:
                this.group = "Hang dien may";
                break;
            case 4:
                this.group = "Hang gia dung";
                break;
        }
        System.out.print("Price: ");
        this.price = input.nextDouble();
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + group + " - " + price;
    }
}
