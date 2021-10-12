import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static List<Driver> drivers = new ArrayList<>();
    public static List<Line> lines = new ArrayList<>();
    public static List<DrivingSchedule> drivingSchedules = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        menu(input);
    }

    public static void menu(Scanner input){
        System.out.println("-------------------------------------------------");
        System.out.println("STORE MANAGEMENT");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add driver");
        System.out.println("2. Print out drivers in store");
        System.out.println("3. Add line");
        System.out.println("4. Print out all lines");
        System.out.println("5. Create driving schedules for each driver in list");
        System.out.println("6. Print out all driving schedules");
        System.out.println("7. Sorting driving schedule list buy driver's name");
        System.out.println("8. Sorting driving schedule list buy line's type have bought");
        System.out.println("9. Print out receipt for each driver");
        System.out.println("0. Exit");
        int part = (int) type(input, 0, 9);
        switch(part){
            case 0:
                System.out.println("GOOD BYE !!!");
                break;
            case 1:
                addDriver(input);
                break;
            case 2:
                printAllDrivers(input);
                break;
            case 3:
                addLine(input);
                break;
            case 4:
                printAllLine(input);
                break;
            case 5: 
                createDrivingSchedulesForEachDriver(input);
                break;
            case 6:
                printAllDrivingSchedules(input);
                break;
            case 7:
                sortingDrivingSchedulesByDriverName(input);
                break;
            case 8:
                sortingDrivingSchedulesByLine(input);
                break;
            case 9:
                printDrivingTable(input);
                break;
        }
    }

    public static void addDriver(Scanner input){
        Driver driver = new Driver();
        drivers.add(driver);
        System.out.println("Adding new driver succesfully");
        menu(input);
    }

    public static void printAllDrivers(Scanner input){
        System.out.println("Driver list:");
        drivers.stream().forEach(System.out::println);
        menu(input);
    }

    public static void addLine(Scanner input){
        Line line = new Line();
        lines.add(line);
        System.out.println("Adding new tikcet succesfully");
        menu(input);
    }

    public static void printAllLine(Scanner input){
        System.out.println("Line list:");
        lines.stream().forEach(System.out::println);
        menu(input);
    }

    public static void createDrivingSchedulesForEachDriver(Scanner input){
        for (Driver driver : drivers) {
            DrivingSchedule ds = findDrivingScheduleOfDriver(driver);
            System.out.println("Driver: " + driver.getId() + " - " + driver.getName());
            System.out.println("Total turns has taken: " + ds.getTotalTakenLines());
            if(ds.getTotalTakenLines() >= 15){
                System.out.println("Cannot take more lines for today.");
            }else{
                System.out.print("Type the number of lines you wanna take: ");
                int lineNum = (int) type(input, 1, (15 - ds.getTotalTakenLines()));
                for (int i = 1; i <= lineNum; i++) {
                    if(ds.getTotalTakenLines() >= 15){
                        System.out.println("Cannot take more line");
                        break;
                    }
                    Line line = findLineById(input, i);
                    if(line != null){
                        System.out.print("How many turns you want to take for this line: ");
                        int turns = (int) type(input, 1, (15 - ds.getTotalTakenLines()));
                        if(ds.getLinesAndTurns().containsKey(line)){
                            ds.getLinesAndTurns().put(line, ds.getLinesAndTurns().get(line) + turns);
                        }else{
                            ds.getLinesAndTurns().put(line, turns);
                        }
                    }else{
                        System.out.println("Taking line is not succesfull");
                    }
                }
            }
            drivingSchedules.add(ds);
            System.out.println("---------------------------------------------");
        }
        System.out.println("Taking line for all drivers is done");
        menu(input);
    }

    public static void printAllDrivingSchedules(Scanner input){
        System.out.println("Driving Schedules");
        drivingSchedules.stream().forEach(System.out::println);
        menu(input);
    }

    public static void sortingDrivingSchedulesByDriverName(Scanner input){
        drivingSchedules = drivingSchedules
                            .stream()
                            .sorted((ds1, ds2) -> ds1.getDriver().getName().compareTo(ds2.getDriver().getName()))
                            .toList();
        printAllDrivingSchedules(input);
    }

    public static void sortingDrivingSchedulesByLine(Scanner input){
        drivingSchedules = drivingSchedules
                                .stream()
                                .sorted((ds1, ds2) -> ds2.getTotalTakenLines() - ds1.getTotalTakenLines())
                                .toList();
        printAllDrivingSchedules(input);
    }

    public static void printDrivingTable(Scanner input){
        for (DrivingSchedule drivingSchedule : drivingSchedules) {
            System.out.println("Driver: " + drivingSchedule.getDriver().getId() + " - " + drivingSchedule.getDriver().getName());
            drivingSchedule
            .getLinesAndTurns()
            .entrySet()
            .stream()
            .forEach(e -> System.out.println("Line: " + e.getKey().getId() +
                                             " | Distance: " + e.getKey().getDistance() +
                                             " | Turns: " + e.getValue() + 
                                             " | Total distance: " + e.getKey().getDistance() * e.getValue()));
        }
    }

    private static Line findLineById(Scanner input, int index){
        boolean reType = true;
        Line line = null;
        while(reType){
            System.out.print("Type id for line number " + index + ": ");
            String lineId = input.next();
            line = lines
                    .stream()
                    .filter(ln -> ln.getId().equals(lineId))
                    .findAny()
                    .orElse(null);
            if(line != null){
                reType = false;
            }else{
                System.out.println("Cannot find this line");
                System.out.println("Do you want to type again?");
                System.out.print("'Y' to find again, other buttom to exit: ");
                if(!input.next().equalsIgnoreCase("y")){
                    reType = false;
                }
            }
        }
        return line;
    }

    private static DrivingSchedule findDrivingScheduleOfDriver(Driver driver){
        return  drivingSchedules
                .stream()
                .filter(ds -> ds.getDriver().equals(driver))
                .findFirst().orElse(new DrivingSchedule(driver));
    }

    public static double type(Scanner input, double min, double max){
        double option = -1;
        while(option == -1){
            try {
                option = input.nextDouble();
                if(option < min || option > max){
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.print("Input value must be between " + min + " - " + max + ". Type again: ");
                input.nextLine();
                option = -1;
            }
        }
        return option;
    }
}
