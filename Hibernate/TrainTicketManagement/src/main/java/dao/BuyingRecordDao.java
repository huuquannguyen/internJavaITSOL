package dao;

import entity.BuyingRecord;
import entity.Customer;
import entity.Ticket;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class BuyingRecordDao {

    public static boolean createBuyingRecord(BuyingRecord buyingRecord){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(buyingRecord);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    public static List<BuyingRecord> getAllBuyingRecord(){
        String sql = "FROM BuyingRecord";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query<BuyingRecord> query = session.createQuery(sql);
            List<BuyingRecord> buyingRecords = query.getResultList();
            session.getTransaction().commit();
            return buyingRecords;
        }catch (HibernateException e){
            System.out.println(e);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return null;
    }

    public static List<BuyingRecord> getAllBuyingRecordOrderByCustomerName(){
        String sql = "FROM BuyingRecord ORDER BY customer.name";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query<BuyingRecord> query = session.createQuery(sql);
            List<BuyingRecord> buyingRecords = query.getResultList();
            session.getTransaction().commit();
            return buyingRecords;
        }catch (HibernateException e){
            System.out.println(e);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return null;
    }

    public static List<BuyingRecord> getAllBuyingRecordOrderByTicketQuantity(){
        String sql = "FROM BuyingRecord ORDER BY quantity DESC";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query<BuyingRecord> query = session.createQuery(sql);
            List<BuyingRecord> buyingRecords = query.getResultList();
            session.getTransaction().commit();
            return buyingRecords;
        }catch (HibernateException e){
            System.out.println(e);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
        return null;
    }

    public static BuyingRecord findBuyingRecord(Customer customer, Ticket ticket){
        String sql = "FROM BuyingRecord WHERE customer = :p_br_customer and ticket = :p_br_ticket";
        Session session = HibernateUtil.getSessionFactory().openSession();
        BuyingRecord buyingRecord = null;
        try {
            session.beginTransaction();
            Query<BuyingRecord> query = session.createQuery(sql);
            query.setParameter("p_br_customer", customer);
            query.setParameter("p_br_ticket", ticket);
            buyingRecord = query.uniqueResult();
            session.getTransaction().commit();
            return buyingRecord;
        }catch (HibernateException e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return buyingRecord;
    }

    public static void updateBuyingRecord(BuyingRecord buyingRecord){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(buyingRecord);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }
}
