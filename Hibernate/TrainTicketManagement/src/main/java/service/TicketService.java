package service;

import dao.TicketDao;
import dao.TicketDao;
import entity.Ticket;
import entity.Ticket;
import util.AppUtil;

import java.util.Scanner;

public class TicketService {
    public static void createTicket(){
        Ticket ticket = new Ticket();
        ticket.input();
        System.out.println(TicketDao.createTicket(ticket) == true ? "Created succesfully" : "Failed to create ticket");
        AppUtil.menu();
    }

    public static void printAllTickets(){
        System.out.println("Ticket list");
        TicketDao.getAllTickets().forEach(System.out::println);
        AppUtil.menu();
    }

    public static Ticket findTicketByInputId(int index){
        Scanner sc = new Scanner(System.in);
        boolean reType = true;
        Ticket ticket = null;
        while (reType) {
            System.out.print("Type id for ticket number " + index + ": ");
            int ticketId = (int) AppUtil.type(sc, 1, 99999);
            ticket = TicketDao.findTicketById(ticketId);
            if (ticket == null) {
                System.out.print("Cannot find this ticket. Find again? (Y/N): ");
                String option = String.valueOf(sc.next().charAt(0));
                if(!option.equalsIgnoreCase("y")){
                    reType = false;
                }
            }else{
                reType = false;
            }
        }
        return ticket;
    }
}
