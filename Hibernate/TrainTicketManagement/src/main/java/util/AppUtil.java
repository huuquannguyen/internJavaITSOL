package util;

import dao.BuyingRecordDao;
import dao.CustomerDao;
import dao.TicketDao;
import entity.BuyingRecord;
import entity.Customer;
import service.BuyingRecordService;
import service.CustomerService;
import service.TicketService;

import java.util.Scanner;

public class AppUtil {

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

    public static void menu(){
        System.out.println("-------------------------------------------------");
        System.out.println("TICKET MANAGEMENT");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add Customer");
        System.out.println("2. Print all customers");
        System.out.println("3. Add ticket");
        System.out.println("4. Print all tickets");
        System.out.println("5. Create buying record");
        System.out.println("6. Print out all buying record");
        System.out.println("7. Sorting buying record by customer's name");
        System.out.println("8. Sorting buying record by ticket quantity");
        System.out.println("9. Print out receipt for each customer");
        System.out.println("0. Exit");
        Scanner input = new Scanner(System.in);
        int part = (int) type(input, 0, 9);
        switch(part){
            case 0:
                System.out.println("GOOD BYE !!!");
                break;
            case 1:
                CustomerService.createCustomer();
                break;
            case 2:
                CustomerService.printAllCustomers();
                break;
            case 3:
                TicketService.createTicket();
                break;
            case 4:
                TicketService.printAllTickets();
                break;
            case 5:
                BuyingRecordService.createBuyingRecord();
                break;
            case 6:
                BuyingRecordService.printAllBuyingRecord();
                break;
            case 7:
                BuyingRecordService.sortingByCustomerName();
                break;
            case 8:
                BuyingRecordService.sortingByTicketQuantity();
                break;
            case 9:
                BuyingRecordService.printReceipt();
                break;
        }
    }
}
