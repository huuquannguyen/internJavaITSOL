import dao.CustomerDao;
import entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.AppUtil;
import util.HibernateUtil;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AppUtil.menu();
    }
}
