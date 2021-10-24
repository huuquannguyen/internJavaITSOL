package service;

import dao.CustomerDao;
import entity.Customer;
import entity.Ticket;
import util.AppUtil;

import java.util.Scanner;

public class CustomerService {

    public static void createCustomer(){
        Customer customer = new Customer();
        customer.input();
        System.out.println(CustomerDao.createCustomer(customer) == true ? "Created succesfully" : "Failed to create customer");
        AppUtil.menu();
    }

    public static void printAllCustomers(){
        System.out.println("Customer list");
        CustomerDao.getAllCustomers().forEach(System.out::println);
        AppUtil.menu();
    }

    public static Customer findCustomerByInputId(){
        Scanner sc = new Scanner(System.in);
        boolean reType = true;
        Customer customer = null;
        while (reType) {
            System.out.print("Type customer id: ");
            int customerId = (int) AppUtil.type(sc, 1, 99999);
            customer = CustomerDao.findCustomerById(customerId);
            if (customer == null) {
                System.out.print("Cannot find this customer. Find again? (Y/N): ");
                String option = String.valueOf(sc.next().charAt(0));
                if(!option.equalsIgnoreCase("y")){
                    reType = false;
                }
            }else{
                reType = false;
            }
        }
        return customer;
    }

}
