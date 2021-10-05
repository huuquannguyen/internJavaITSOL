import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

    private static List<Product> products = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<BuyingRecord> buyingRecords = new ArrayList<>();
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
        System.out.println("3. Add product to store");
        System.out.println("4. Print out all products");
        System.out.println("5. Create buying record for each customer in list");
        System.out.println("6. Print out all buying record");
        System.out.println("7. Sorting buying record list buy customer's name");
        System.out.println("8. Sorting buying record list buy product's name");
        System.out.println("9. Print out receipt for for customer");
        System.out.println("0. Exit");
        int part = type(input, 0, 9);
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
                addProductToStore(input);
                break;
            case 4:
                printAllProdut(input);
                break;
            case 5: 
                createBuyRecordsForEachCustomer(input);
                break;
            case 6:
                printOutAllBuyingRecord(input);
                break;
            case 7:
                sortingBuyingRecordsByCustomerName(input);
                break;
            case 8:
                sortingBuyingRecordsBuyProductName(input);
                break;
            case 9:
                printReceipForEachCustomer(input);
                break;
        }
    }

    public static void addProductToStore(Scanner input){
        Product product = new Product(input);
        products.add(product);
        System.out.println("Add product to store successfully");
        menu(input);
    }

    public static void printAllProdut(Scanner input){
        for (Product product : products) {
            System.out.println(product);
        }
        menu(input);
    }

    public static void addCustomer(Scanner input){
        Customer customer = new Customer(input);
        customers.add(customer);
        System.out.println("A new customer has been added to list");
        menu(input);
    }

    public static void printAllCustomers(Scanner input){
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        menu(input);
    }

    public static void createBuyRecordsForEachCustomer(Scanner input){
        for (Customer customer : customers) {
            int countBoughtProduct = countBoughtProduct(customer);
            System.out.println("Customer: " + customer.getId() + " - " + customer.getName());
            System.out.println("Can buy " + (10 - countBoughtProduct) + " other products");
            System.out.print("Type number of products this customer want to buy: ");
            int number = type(input, 1, 10 - countBoughtProduct);
            for(int i = 1; i <= number; i++){
                Product product = findProductById(input, i);
                if(product != null){
                    System.out.print("Type the quantity want to buy: ");
                    int quantity = type(input, 1, 1000);
                    BuyingRecord record = findBuyingRecordByCustomerAndProduct(customer, product);
                    if(record != null){
                        record.setQuantity(record.getQuantity() + quantity);
                        System.out.println("Buy product successfully");
                    }else{
                        record = new BuyingRecord(customer, product, quantity);
                        buyingRecords.add(record);
                        System.out.println("Buy product successfully");
                    }
                }
            } 
            System.out.println("----------------------------");
        }
        System.out.println("Finish creating records");
        menu(input);
    }

    public static void printOutAllBuyingRecord(Scanner input){
        for (BuyingRecord buyingRecord : buyingRecords) {
            System.out.println(buyingRecord);
        }
        menu(input);
    }

    public static void sortingBuyingRecordsByCustomerName(Scanner input){
        for(int i = 0; i < buyingRecords.size() - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < buyingRecords.size(); j++){
                int compareResult = buyingRecords.get(minIndex).getCustomer().getName()
                                    .compareTo(buyingRecords.get(j).getCustomer().getName());
                if(compareResult > 0){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                BuyingRecord temp = buyingRecords.get(minIndex);
                buyingRecords.set(minIndex, buyingRecords.get(i));
                buyingRecords.set(i, temp);
            }
        }
        System.out.println("Sorting records succesfully");
        printOutAllBuyingRecord(input);
    }

    public static void sortingBuyingRecordsBuyProductName(Scanner input){
        for(int i = 0; i < buyingRecords.size() - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < buyingRecords.size(); j++){
                int compareResult = buyingRecords.get(minIndex).getProduct().getName()
                                    .compareTo(buyingRecords.get(j).getProduct().getName());
                if(compareResult > 0){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                BuyingRecord temp = buyingRecords.get(minIndex);
                buyingRecords.set(minIndex, buyingRecords.get(i));
                buyingRecords.set(i, temp);
            }
        }
        System.out.println("Sorting records succesfully");
        printOutAllBuyingRecord(input);
    }

    public static void printReceipForEachCustomer(Scanner input){
        for (Customer customer : customers) {
            List<BuyingRecord> list = findBuyingRecordBuyCustomer(customer);
            System.out.println(customer.getId() + " - " + customer.getName() + "'s RECEIPT:");
            System.out.println("PRODUCT'S NAME              QUANTITY            PRICE");
            double totalPrice = 0;
            for (BuyingRecord buyingRecord : list) {
                totalPrice += buyingRecord.getProduct().getPrice() * buyingRecord.getQuantity();
                System.out.println(buyingRecord.getProduct().getName() + "                     " 
                + buyingRecord.getQuantity() + "              " + buyingRecord.getProduct().getPrice() * buyingRecord.getQuantity());
            }
            System.out.println("                              Totol pay: " + totalPrice);
        }
        menu(input);
    }

    public static int type(Scanner input, int min, int max){
        int option = -1;
        while(option == -1){
            try {
                option = input.nextInt();
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

    private static int countBoughtProduct(Customer customer){
        int count = 0;
        for (BuyingRecord buyingRecord : buyingRecords) {
            if(buyingRecord.getCustomer() == customer){
                count++;
            }
        }
        return count;
    }

    private static Product findProductById(Scanner input, int index){
        boolean reType = true;
        Product product = null;
        while(reType){
            System.out.print("Type product id for product " + index + ": ");
            String productId = input.next();
            Optional<Product> rawProdut = products.stream().filter(p -> p.getId().equals(productId)).findFirst();
            if(rawProdut.isPresent()){
                product = rawProdut.get();
                reType = false;
            }else{
                System.out.println("Cannot find this product");
                System.out.println("Do you want to find again?");
                System.out.print("Type 'Y' to keep finding, other buttom to stop: ");
                if(!input.next().equalsIgnoreCase("y")){
                    reType = false;
                }
            }
        }
        return product;
    }

    private static BuyingRecord findBuyingRecordByCustomerAndProduct(Customer customer, Product product){
        for (BuyingRecord buyingRecord : buyingRecords) {
            if(buyingRecord.getCustomer() == customer && buyingRecord.getProduct() == product){
                return buyingRecord;
            }
        }
        return null;
    }

    private static List<BuyingRecord> findBuyingRecordBuyCustomer(Customer customer){
        List<BuyingRecord> result = new ArrayList<>();
        for (BuyingRecord buyingRecord : buyingRecords) {
            if(buyingRecord.getCustomer() == customer){
                result.add(buyingRecord);
            }
        }
        return result;
    }


}
