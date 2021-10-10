
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    private static Passenger[] passengers = new Passenger[99999];
    private static int passengerIndex = 0;

    private static Ticket[] tickets = new Ticket[999];
    private static int ticketIndex = 0;

    private static BuyingRecord[] buyingRecords = new BuyingRecord[100];
    private static int buyingRecordIndex = 0;
    public static void main(String[] args) throws Exception {
          Scanner input = new Scanner(System.in);
          menu(input);
    }

    public static void menu(Scanner input){
        System.out.println("-------------------------------------------------");
        System.out.println("STORE MANAGEMENT");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add passenger");
        System.out.println("2. Print out passengers in store");
        System.out.println("3. Add ticket");
        System.out.println("4. Print out all tickets");
        System.out.println("5. Create buying records for each passenger in list");
        System.out.println("6. Print out all buying records");
        System.out.println("7. Sorting buying record list buy passenger's name");
        System.out.println("8. Sorting buying record list buy ticket's type have bought");
        System.out.println("9. Print out receipt for each passenger");
        System.out.println("0. Exit");
        int part = (int) type(input, 0, 9);
        switch(part){
            case 0:
                System.out.println("GOOD BYE !!!");
                break;
            case 1:
                addPassenger(input);
                break;
            case 2:
                printAllPassengers(input);
                break;
            case 3:
                addTicket(input);
                break;
            case 4:
                printAllTicket(input);
                break;
            case 5: 
                createBuyingRecordsForEachPassenger(input);
                break;
            case 6:
                printAllBuyingRecords(input);
                break;
            case 7:
                sortingBuyingRecordsByPassengerName(input);
                break;
            case 8:
                sortingBuyingRecordsByTicketType(input);
                break;
            case 9:
                printReceiptForEachPassenger(input);
                break;
        }
    }

    public static void addPassenger(Scanner input){
        Passenger passenger = new Passenger();
        passengers[passengerIndex] = passenger;
        passengerIndex++;
        System.out.println("Adding new passenger succesfully");
        menu(input);
    }

    public static void printAllPassengers(Scanner input){
        System.out.println("Passenger list:");
        for (Passenger passenger : passengers) {
            if(passenger == null){
                break;
            }
            System.out.println(passenger);
        }
        menu(input);
    }

    public static void addTicket(Scanner input){
        Ticket ticket = new Ticket();
        tickets[ticketIndex] = ticket;
        ticketIndex++;
        System.out.println("Adding new tikcet succesfully");
        menu(input);
    }

    public static void printAllTicket(Scanner input){
        System.out.println("Ticket list:");
        for (Ticket ticket : tickets) {
            if(ticket == null){
                break;
            }
            System.out.println(ticket);
        }
        menu(input);
    }

    public static void createBuyingRecordsForEachPassenger(Scanner input){
        for (Passenger passenger : passengers) {
            if(passenger == null){
                break;
            }
            int tickets = countPassengerTickets(passenger);
            System.out.println("Passenger: " + passenger.getId() + " - " + passenger.getName() + " - So ve da mua: " + tickets);
            if(tickets == 3){
                System.out.println("Khong the mua them ve xe");
            }else{
                System.out.print("Nhap so ve muon mua: ");
                int number = (int) type(input, 1, 3 - tickets);
                for (int i = 1; i <= number; i++) {
                    Ticket ticket = findTicketBuyId(input, i);
                    if(ticket != null){
                        int indexRecord = findBuyingRecord(passenger, ticket);
                        if(indexRecord != -1){
                            if(buyingRecords[indexRecord].getDate().getMonth().equals(LocalDate.now().getMonth())
                            && buyingRecords[indexRecord].getDate().getYear() == LocalDate.now().getYear()){
                                    System.out.println("Mua khong thanh cong. Ban da mua ve nay trong thang");
                            }else{
                                buyingRecords[buyingRecordIndex] = new BuyingRecord(passenger, ticket);
                                buyingRecordIndex++;
                                System.out.println("Mua ve thanh cong");
                                if(buyingRecordIndex == buyingRecords.length - 1){
                                    buyingRecords = addSpace(buyingRecords);
                                }
                            }
                        }else{
                            buyingRecords[buyingRecordIndex] = new BuyingRecord(passenger, ticket);
                            buyingRecordIndex++;
                            System.out.println("Mua ve thanh cong");
                            if(buyingRecordIndex == buyingRecords.length - 1){
                                buyingRecords = addSpace(buyingRecords);
                            }
                        }
                    }else{
                        System.out.println("Mua khong thanh cong");
                    }
                }
            }
            System.out.println("---------------------------------------");
        }
        System.out.println("Nhap hoan tat");
        menu(input);
    }

    public static void printAllBuyingRecords(Scanner input){
        for (BuyingRecord buyingRecord : buyingRecords) {
            if(buyingRecord == null){
                break;
            }
            System.out.println(buyingRecord);
        }
        menu(input);
    }

    public static void sortingBuyingRecordsByPassengerName(Scanner input){
        for (int i = 0; i < buyingRecords.length - 1; i++) {
            if(buyingRecords[i] == null){
                break;
            }
            int minIndex = i;
            for(int j = i + 1; j < buyingRecords.length; j++){
                if(buyingRecords[j] == null){
                    break;
                }
                int compareResult = buyingRecords[minIndex].getPassenger().getName()
                                    .compareTo(buyingRecords[j].getPassenger().getName());
                if(compareResult > 0){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                BuyingRecord temp = buyingRecords[i];
                buyingRecords[i] = buyingRecords[minIndex];
                buyingRecords[minIndex] = temp;
            }
        }
        System.out.println("Sort by passenger name:");
        printAllBuyingRecords(input);
    }
    
    public static void sortingBuyingRecordsByTicketType(Scanner input){
        for (int i = 0; i < buyingRecords.length - 1; i++) {
            if(buyingRecords[i] == null){
                break;
            }
            int minIndex = i;
            for(int j = i + 1; j < buyingRecords.length; j++){
                if(buyingRecords[j] == null){
                    break;
                }
                int compareResult = buyingRecords[minIndex].getTicket().getDescription()
                                    .compareTo(buyingRecords[j].getTicket().getDescription());
                if(compareResult > 0){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                BuyingRecord temp = buyingRecords[i];
                buyingRecords[i] = buyingRecords[minIndex];
                buyingRecords[minIndex] = temp;
            }
        }
        System.out.println("Sort by ticket type:");
        printAllBuyingRecords(input);
    }
    
    public static void printReceiptForEachPassenger(Scanner input){
        for (Passenger passenger : passengers) {
            if(passenger == null){
                break;
            }
            double paymentPercent = 1;
            if(passenger.getType().equals("HSSV")){
                paymentPercent = 0.5;
            }else if(passenger.getType().equals("Can bo")){
                paymentPercent = 0.3;
            }
            double totalPayment = 0;
            System.out.println("Receipt for passenger: " + passenger.getId() + " - " + passenger.getName());
            BuyingRecord[] records = findTicketsBuyToday(passenger);
            for (BuyingRecord buyingRecord : records) {
                if(buyingRecord == null){
                    break;
                }
                double payment = buyingRecord.getTicket().getPrice() * paymentPercent;
                System.out.println("Ticket: " + buyingRecord.getTicket().getDescription() +
                                    " | Price: " + payment);
                totalPayment += payment;
            }
            System.out.println("Total payment: " + totalPayment);
        }
        menu(input);
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

    private static Ticket findTicketBuyId(Scanner input, int index) {
        boolean reType = true;
        Ticket ticket = null;
        while(reType){
            System.out.print("Nhap id cho ticket thu " + index + ": ");
            String ticketId = input.next();
            for (Ticket s : tickets) {
                if(s == null){
                    break;
                }
                if(s.getId().equals(ticketId)){
                    ticket = s;
                    break;
                }
            }
            if(ticket != null){
                reType = false;
            }else{
                System.out.println("Khong tim thay ve");
                System.out.println("Ban co muon nhap lai khong?");
                System.out.print("Nhap 'Y' de tiep tuc, nut bat ki de thoat: ");
                if(!input.next().equalsIgnoreCase("y")){
                    reType = false;
                }
            }
        }
        return ticket;
    }

    private static BuyingRecord[] findTicketsBuyToday(Passenger passenger){
        BuyingRecord[] records = new BuyingRecord[buyingRecordIndex];
        int recordIndex = 0;
        for (BuyingRecord buyingRecord : buyingRecords) {
            if(buyingRecord == null){
                break;
            }
            if(buyingRecord.getPassenger() == passenger && buyingRecord.getDate().equals(LocalDate.now())){
                records[recordIndex] = buyingRecord;
                recordIndex++;
            }
        }
        return records;
    }
    
    private static int findBuyingRecord(Passenger passenger, Ticket ticket){
        int latestMonthIndex = -1;
        for (int i = 0; i < buyingRecords.length; i++) {
            if(buyingRecords[i] == null){
                break;
            }
            if(buyingRecords[i].getPassenger() == passenger && buyingRecords[i].getTicket() == ticket){
                latestMonthIndex = i;
            }
        }
        return latestMonthIndex;
    }

    private static int countPassengerTickets(Passenger passenger){
        int count = 0;
        for (BuyingRecord buyingRecord : buyingRecords) {
            if(buyingRecord == null){
                break;
            }
            if(buyingRecord.getPassenger() == passenger){
                if(!(buyingRecord.getDate().getMonth().equals(LocalDate.now().getMonth())
                && buyingRecord.getDate().getYear() == LocalDate.now().getYear())){
                    count ++;
                }
            }
        }
        return count;
    }

    private static BuyingRecord[] addSpace(BuyingRecord[] oldArray) {
        BuyingRecord[] newArray = new BuyingRecord[oldArray.length * 2];
        for(int i = 0; i < oldArray.length; i++){
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

}
