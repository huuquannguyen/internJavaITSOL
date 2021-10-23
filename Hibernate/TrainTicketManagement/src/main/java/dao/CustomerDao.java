package dao;

import entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class CustomerDao {
    public static boolean createCustomer(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = new Customer();
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
        }finally {
            session.close();
        }
        return null;
    }
}
