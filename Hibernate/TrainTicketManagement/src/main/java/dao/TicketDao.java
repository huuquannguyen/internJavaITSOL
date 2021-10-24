package dao;

import entity.Ticket;
import entity.Ticket;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class TicketDao {

    public static boolean createTicket(Ticket ticket){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(ticket);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    public static List<Ticket> getAllTickets(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Ticket";
            session.beginTransaction();
            Query<Ticket> query = session.createQuery(sql);
            List<Ticket> tickets = query.getResultList();
            session.getTransaction().commit();
            return tickets;
        }catch (HibernateException e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }

    public static Ticket findTicketById(int id){
        String sql = "FROM Ticket WHERE id = :p_ticket_id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query<Ticket> query = session.createQuery(sql);
            query.setParameter("p_ticket_id", Long.valueOf(id));
            Ticket ticket = query.uniqueResult();
            session.getTransaction().commit();
            return ticket;
        }catch(Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }
}
