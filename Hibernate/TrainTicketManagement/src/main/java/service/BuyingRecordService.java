package service;

import dao.BuyingRecordDao;
import dao.CustomerDao;
import entity.BuyingRecord;
import entity.Customer;
import entity.Ticket;
import util.AppUtil;

import java.util.List;
import java.util.Scanner;

public class BuyingRecordService {

    public static void createBuyingRecord(){
        Customer customer = CustomerService.findCustomerByInputId();
        if(customer == null){
            System.out.print("Buying ticket not success");
        }else{
            System.out.println("Customer: " + customer.getId() + " - " + customer.getName());
            System.out.println("Buying record list:");
            customer.getBuyingRecords().forEach(System.out::println);
            System.out.println("Can buy " + (4 - customer.getBuyingRecords().size()) + " other ticket types");
            Scanner sc = new Scanner(System.in);
            System.out.print("How many types of ticket you wanna buy?: ");
            int typeNum = (int) AppUtil.type(sc, 1, 4);
            for (int i = 1; i <= typeNum; i++) {
                Ticket ticket = TicketService.findTicketByInputId(i);
                if(ticket == null){
                    System.out.println("Buying ticket not success");
                }else{
                    BuyingRecord buyingRecord = BuyingRecordDao.findBuyingRecord(customer, ticket);
                    if(buyingRecord == null){
                        buyingRecord = new BuyingRecord();
                        buyingRecord.addRecord(customer, ticket);
                        System.out.print("How many tickets do you wanna buy?: ");
                        int quantity = (int) AppUtil.type(sc, 1, 20);
                        buyingRecord.setQuantity(quantity);
                        BuyingRecordDao.createBuyingRecord(buyingRecord);
                        System.out.println("Buy successfull");
                    }else{
                        System.out.println("You have bought " + buyingRecord.getQuantity() + " tickets for this seat type");
                        System.out.println("You can buy " + (20 - buyingRecord.getQuantity()) + " more tickets");
                        if(buyingRecord.getQuantity() < 20){
                            System.out.println("How many tickets do you wanna buy?: ");
                            int quantity = (int) AppUtil.type(sc, 1, (20 - buyingRecord.getQuantity()));
                            buyingRecord.setQuantity(buyingRecord.getQuantity() + quantity);
                            BuyingRecordDao.updateBuyingRecord(buyingRecord);
                            System.out.println("Buy successfull");
                        }
                    }
                }
            }
        }
        AppUtil.menu();
    }

    public static void printAllBuyingRecord(){
        System.out.println("Buying record list:");
        BuyingRecordDao.getAllBuyingRecord().forEach(System.out::println);
        AppUtil.menu();
    }

    public static void sortingByCustomerName(){
        System.out.println("Sorting by name:");
        BuyingRecordDao.getAllBuyingRecordOrderByCustomerName().forEach(System.out::println);
        AppUtil.menu();
    }

    public static void sortingByTicketQuantity(){
        System.out.println("Sorting by ticket quantity: ");
        BuyingRecordDao.getAllBuyingRecordOrderByTicketQuantity().forEach(System.out::println);
        AppUtil.menu();
    }

    public static void printReceipt(){
        List<Customer> customers = CustomerDao.getAllCustomers();
        for (Customer c: customers ){
            double totalPay = c.getBuyingRecords().stream()
                                                    .mapToDouble(br -> br.getTicket().getPrice() * br.getQuantity())
                                                    .sum();
            System.out.println("Receipt for customer: " + c.getId() + " - " + c.getName());
            c.getBuyingRecords()
                    .stream()
                    .forEach(br -> System.out.println("Ticket id: " + br.getTicket().getId() +
                                                        " | " + "Seat type: " + br.getTicket().getPrice() +
                                                        " | " + "Price: " + br.getTicket().getPrice() +
                                                        " | " + "Quantity: " + br.getQuantity() +
                                                        " | " + "Total price: " + br.getTicket().getPrice() * br.getQuantity()));
            System.out.println("Total payment: " + totalPay);
            System.out.println("-----------------------------------------");
        }
    }
}
