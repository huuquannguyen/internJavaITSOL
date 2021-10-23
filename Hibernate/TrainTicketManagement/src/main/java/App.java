import dao.CustomerDao;
import entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

public class App {
    public static void main(String[] args) {
        CustomerDao.createCustomer();
    }
}
