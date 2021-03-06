package dao;

import entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class CustomerDao {

    public static boolean createCustomer(Customer customer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            System.out.println("Creating new customer not success");
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    public static Customer findCustomerById(int id){
        String sql = "FROM Customer WHERE id = :p_customer_id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query<Customer> query = session.createQuery(sql);
            query.setParameter("p_customer_id", Long.valueOf(id));
            Customer customer = query.uniqueResult();
            session.getTransaction().commit();
            return customer;
        }catch(Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }

    public static List<Customer> getAllCustomers(){
        String sql = "FROM Customer";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<Customer> query = session.createQuery(sql);
            List<Customer> customers = query.getResultList();
            session.getTransaction().commit();
            return customers;
        }catch (HibernateException e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }
}
