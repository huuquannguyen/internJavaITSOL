import java.util.HashMap;
import java.util.Map;

public class DrivingSchedule {
    
    private Driver driver;
    private Map<Line, Integer> linesAndTurns = new HashMap<>();

    public DrivingSchedule(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Map<Line, Integer> getLinesAndTurns() {
        return linesAndTurns;
    }

    public void setLinesAndTurns(Map<Line, Integer> linesAndTurns) {
        this.linesAndTurns = linesAndTurns;
    }

    public int getTotalTakenLines(){
        return linesAndTurns
                .entrySet()
                .stream()
                .mapToInt(e -> e.getValue())
                .sum();
    }

    public double getAllLineDistance(){
        return linesAndTurns.entrySet().stream().mapToDouble(e -> e.getKey().getDistance()).sum();
    }
    
    @Override
    public String toString() {
        return driver.getId() + " - " + driver.getName() + " - " + linesAndTurns + " - " + getTotalTakenLines();
    }

}
