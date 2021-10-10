import java.util.Scanner;

public class Ticket {
    
    private static int nextId = 1001;

    private String id;
    private String description;
    private double price;
    private Scanner input = new Scanner(System.in);

    public Ticket(){
        try {
            this.id = String.valueOf(nextId).substring(1);
            nextId++;
            if(nextId > 9999){
                throw new RuntimeException("Limited ticket");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Nhap thong tin ve xe:");
        System.out.println("Loai: ");
        System.out.println("1. 1 tuyen");
        System.out.println("2. 1 so tuyen cu the");
        System.out.println("3. Lien tuyen");
        StringBuffer sb = new StringBuffer();
        int type = (int) App.type(input, 1, 3);
        switch(type){
            case 1:
                sb.append("1 tuyen - ");
                System.out.print("Nhap ma xe bus: ");
                String busId = input.next();
                sb.append(busId);
                description = sb.toString();
                break;
            case 2:
                sb.append("1 so tuyen cu the -");
                System.out.print("Nhap so tuyen: ");
                int soTuyen = (int) App.type(input, 1, 10);
                for(int i = 0; i < soTuyen; i++){
                    System.out.print("Nhap ma xe bus cho tuyen " + (i + 1) + ": ");
                    String maXe = input.next();
                    sb.append(" " + maXe);
                }
                this.description = sb.toString();
                break;
            case 3:
                sb.append("Lien tuyen");
                this.description = sb.toString();
                break;
        }
        System.out.print("Gia ve: ");
        this.price = App.type(input, 100000, 200000);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " | " + description + " | " + price;
    }
    
}
