import java.util.Scanner;

public class Line {
    private static int nextId = 1001;

    private String id;
    private double distance;
    private int stops;
    Scanner input = new Scanner(System.in);

    public Line(){
        try {
            this.id = String.valueOf(nextId).substring(1);
            nextId++;
            if(nextId > 9999){
                throw new RuntimeException("Limited ticket");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Fill in line information:");
        System.out.print("Distance: ");
        this.distance = App.type(input, 1, 100000);
        System.out.print("Stops: ");
        this.stops = (int) App.type(input, 0, 100);
    }

    public String getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return id + " - " + distance + " - " + stops;
    }
    
}
