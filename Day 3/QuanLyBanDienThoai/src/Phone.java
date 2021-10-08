import java.util.Scanner;

public class Phone {
    
    private static int nextId = 100001;

    private String id;
    private String brand;
    private String model;
    private double price;
    private Scanner input = new Scanner(System.in);

    public Phone() {
        this.id = String.valueOf(nextId).substring(1);
        nextId++;
        if(nextId > 999999){
            throw new RuntimeException("Limited customer");
        }
        System.out.println("Fill in phone information:");
        System.out.print("Brand: ");
        this.brand = input.nextLine();
        System.out.print("Model: ");
        this.model = input.nextLine();
        System.out.print("Price: ");
        this.price = App.type(input, 1, 999999);
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " - " + model + " - " + brand + " - " + price;
    }

    
}
