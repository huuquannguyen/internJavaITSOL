import java.util.Scanner;

public class App {

    private static Customer[] customers = new Customer[99999];
    private static int customerIndex = 0;

    private static Phone[] phones = new Phone[99999];
    private static int phoneIndex = 0;

    private static OrderLine[] orderLines = new OrderLine[100];
    private static int orderLineIndex = 0;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        menu(input);
    }

    public static void menu(Scanner input){
        System.out.println("-------------------------------------------------");
        System.out.println("STORE MANAGEMENT");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add customer");
        System.out.println("2. Print out customers in store");
        System.out.println("3. Add phone to store");
        System.out.println("4. Print out all phones");
        System.out.println("5. Create order lines for each customer in list");
        System.out.println("6. Print out all order lines");
        System.out.println("7. Sorting order lines list buy customer's name");
        System.out.println("8. Sorting order lines list buy phone's quantity have bought");
        System.out.println("9. Print out receipt for each customer");
        System.out.println("0. Exit");
        int part = (int) type(input, 0, 9);
        switch(part){
            case 0:
                System.out.println("GOOD BYE !!!");
                break;
            case 1:
                addCustomer(input);
                break;
            case 2:
                printAllCustomers(input);
                break;
            case 3:
                addPhone(input);
                break;
            case 4:
                printAllPhone(input);
                break;
            case 5: 
                createOrderLinesForEachCustomer(input);
                break;
            case 6:
                printAllOrderLines(input);
                break;
            case 7:
                sortingOrderLinesByCustomerName(input);
                break;
            case 8:
                sortingOrderLinesByQuantity(input);
                break;
            case 9:
                printReceiptForEachCustomer(input);
                break;
        }
    }

    public static void addCustomer(Scanner input){
        Customer customer = new Customer();
        customers[customerIndex] = customer;
        customerIndex++;
        System.out.println("Adding new customer succesfully");
        menu(input);
    }

    public static void printAllCustomers(Scanner input){
        System.out.println("Customer list:");
        for (Customer customer : customers) {
            if(customer == null){
                break;
            }
            System.out.println(customer);
        }
        menu(input);
    }

    public static void addPhone(Scanner input){
        Phone phone = new Phone();
        phones[phoneIndex] = phone;
        phoneIndex++;
        menu(input);
    }

    public static void printAllPhone(Scanner input){
        System.out.println("Phone list:");
        for (Phone phone : phones) {
            if(phone == null){
                break;
            }
            System.out.println(phone);
        }
        menu(input);
    }

    public static void createOrderLinesForEachCustomer(Scanner input){
        for (Customer customer : customers) {
            if(customer == null){
                break;
            }
            System.out.println("Customer: " + customer.getId() + " - " + customer.getName());
            System.out.print("Type number of phone model want to buy: ");
            int number = (int) type(input, 1, 99999);
            for (int i = 1; i <= number; i++) {
                Phone phone = findPhoneById(input, i);
                if(phone != null){
                    int index = findOrderLine(customer, phone);
                    if(index != -1){
                        System.out.println("You have bought: " + orderLines[index].getQuantity() + " phones for this model.");
                        System.out.print("Type the number of phone for this model you want to buy more: ");
                        int buyMore = (int) type(input, 0, 999);
                        orderLines[index].setQuantity(orderLines[index].getQuantity() + buyMore);
                        System.out.println("Buy more " + buyMore + " phones for model " + phone.getModel());
                    }else{
                        System.out.print("Type the number of phone for this model you want to buy: ");
                        int buy = (int) type(input, 1, 999);
                        orderLines[orderLineIndex] = new OrderLine(customer, phone, buy);
                        orderLineIndex++;
                        System.out.println("Buying successfully");
                        if(orderLineIndex >= orderLines.length - 1){
                            orderLines = addingSpace(orderLines);
                        }
                    }
                }else{
                    System.out.println("Cannot find this phone");
                }
            }
            System.out.println("-----------------------------------------");
        }
        menu(input);
    }

    public static void printAllOrderLines(Scanner input){
        for (OrderLine orderLine : orderLines) {
            if(orderLine == null){
                break;
            }
            System.out.println(orderLine);
        }
        menu(input);
    }

    public static void sortingOrderLinesByCustomerName(Scanner input){
        for (int i = 0; i < orderLines.length - 1; i++) {
            if(orderLines[i] == null){
                break;
            }
            int minIndex = i;
            for(int j = i + 1; j < orderLines.length; j++){
                if(orderLines[j] == null){
                    break;
                }
                int compareResult = orderLines[minIndex].getCustomer().getName()
                                    .compareTo(orderLines[j].getCustomer().getName());
                if(compareResult > 0){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                OrderLine temp = orderLines[i];
                orderLines[i] = orderLines[minIndex];
                orderLines[minIndex] = temp;
            }
        }
        System.out.println("Sort by customer name:");
        printAllOrderLines(input);
    }

    public static void sortingOrderLinesByQuantity(Scanner input){
        for(int i = 0; i < orderLines.length - 1; i++){
            if(orderLines[i] == null){
                break;
            }
            int maxIndex = i;
            for(int j = i + 1; j < orderLines.length; j++){
                if(orderLines[j] == null){
                    break;
                }
                if(orderLines[maxIndex].getQuantity() < orderLines[j].getQuantity()){
                    maxIndex = j;
                }
            }
            if(maxIndex != i){
                OrderLine temp = orderLines[i];
                orderLines[i] = orderLines[maxIndex];
                orderLines[maxIndex] = temp;
            }
        }
        System.out.println(" Sorting buy quantity successfully");
        printAllOrderLines(input);
    }

    public static void printReceiptForEachCustomer(Scanner input){
        for (Customer customer : customers) {
            if(customer == null){
                break;
            }
            double totalPayment = 0;
            System.out.println("Receipt for customer: " + customer.getId() + " - " + customer.getName());
            OrderLine[] lines = findListOrderLinesForCustomer(customer);
            for (OrderLine orderLine : lines) {
                if(orderLine == null){
                    break;
                }
                double paymentForOneModel = orderLine.getQuantity() * orderLine.getPhone().getPrice();
                totalPayment += paymentForOneModel;
                System.out.println("Phone: " + orderLine.getPhone().getModel() +
                                    " | Brand: " + orderLine.getPhone().getBrand() +
                                    " | Price: " + orderLine.getPhone().getPrice() +
                                    " | Quantity: " + orderLine.getQuantity() + 
                                    " | Total: " + paymentForOneModel);
            }
            System.out.println("Total bill: " + totalPayment);
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

    private static Phone findPhoneById(Scanner input, int index){
        boolean reType = true;
        Phone phone = null;
        while(reType){
            System.out.print("Type phone id for phone " + index + ": ");
            String phoneId = input.next();
            for (Phone s : phones) {
                if(s == null){
                    break;
                }
                if(s.getId().equals(phoneId)){
                    phone = s;
                    break;
                }
            }
            if(phone != null){
                reType = false;
            }else{
                System.out.println("Cannot find this phone");
                System.out.println("Do you want to find again?");
                System.out.print("Type 'Y' to keep finding, other buttom to stop: ");
                if(!input.next().equalsIgnoreCase("y")){
                    reType = false;
                }
            }
        }
        return phone;
    }

    private static int findOrderLine(Customer customer, Phone phone){
        for (int i = 0; i < orderLines.length; i++) {
            if(orderLines[i] == null){
                break;
            }
            if(orderLines[i].getCustomer() == customer && orderLines[i].getPhone() == phone){
                return i;
            }
        }
        return -1;
    }

    private static OrderLine[] findListOrderLinesForCustomer(Customer customer){
        OrderLine[] records = new OrderLine[orderLines.length];
        int recordsIndex = 0;
        for(int i = 0; i < orderLines.length; i++){
            if(orderLines[i] == null){
                break;
            }
            if(orderLines[i].getCustomer() == customer){
                records[recordsIndex] = orderLines[i];
                recordsIndex++;
            }
        }
        return records;
    }

    private static OrderLine[] addingSpace(OrderLine[] oldArray){
        OrderLine[] newArray = new OrderLine[oldArray.length * 2];
        for(int i = 0; i < oldArray.length; i++){
            newArray[i] = oldArray[i];
        }
        return newArray;
    }
}
