package com.example.test2.dao;

import com.example.test2.entity.Student;
import com.example.test2.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class StudentDao {

    public static boolean createStudent(Student student){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }
        return false;
    }

    public static boolean updateStudentInfo(Student student){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(student);
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

    public static boolean deleteStudent(Student student){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(student);
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

    public static List<Student> getStudentsByParam(MultivaluedMap<String, String> queryString){
        List<Student> students = null;
        if(queryString.size() == 1){
            Map.Entry<String, List<String>> entry = queryString.entrySet().iterator().next();
            String queryParam = entry.getKey();
            switch (queryParam){
                case "name":
                    students = findStudentsByName(entry.getValue().get(0));
                    break;
                case "gender":
                    students = findStudentByGender(entry.getValue().get(0));
                    break;
                case "className":
                    students = findStudentByClassName((entry.getValue().get(0)));
                    break;
                case "homeTown":
                    students = findStudentByHomeTown(entry.getValue().get(0));
                    break;
                case "faculty":
                    students = findStudentByFaculty(entry.getValue().get(0));
                    break;
                default:
                     students = Collections.emptyList();
            }
        }else if(queryString.size() == 2){
            if(queryString.containsKey("minAvg") && queryString.containsKey("maxAvg")){
                String minStr = queryString.get("minAvg").get(0);
                String maxStr = queryString.get("maxAvg").get(0);
                try {
                    double min = Double.valueOf(minStr);
                    double max = Double.valueOf(maxStr);
                    students = findStudentByAvgMarkRange(min, max);
                }catch (Exception e){
                    System.out.println(e);
                    students = Collections.emptyList();
                }
            }else if(queryString.containsKey("startDate") && queryString.containsKey("endDate")){
                String startDateStr = queryString.get("startDate").get(0);
                String endDateStr = queryString.get("endDate").get(0);
                try {
                    Date startDate = Date.valueOf(startDateStr);
                    Date endDate = Date.valueOf(endDateStr);
                    students = findStudentByBirthDayRange(startDate, endDate);
                }catch (Exception e){
                    System.out.println(e);
                    students = Collections.emptyList();
                }
            }else{
                students = Collections.emptyList();
            }
        }else if(queryString.size() == 0){
            students = getAllStudents();
        }else{
            students = Collections.emptyList();
        }
        return students;
    }

    public static List<Student> getAllStudents(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String sql = "FROM Student";
            Query<Student> query = session.createQuery(sql);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    private static List<Student> findStudentsByName(String queryStringValue){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE LOWER(fullName) LIKE LOWER(to_char(concat(concat('%', :p_student_name), '%')))";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_name", queryStringValue);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    private static List<Student> findStudentByGender(String queryStringValue){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE gender = :p_student_gender";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_gender", queryStringValue);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    private static List<Student> findStudentByHomeTown(String queryStringValue){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE homeTown = :p_student_hometown";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_hometown", queryStringValue);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    private static List<Student> findStudentByClassName(String queryStringValue){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE gender = :p_student_classname";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_classname", queryStringValue);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    private static List<Student> findStudentByFaculty(String queryStringValue){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE gender = :p_student_faculty";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_faculty", queryStringValue);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    private static List<Student> findStudentByAverageMark(String queryStringValue){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE gender = :p_student_gender";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_gender", queryStringValue);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    private static List<Student> findStudentByBirthDayRange(Date startDate, Date endDate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE birthDay BETWEEN :p_student_startDate AND :p_student_endDate";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_startDate", startDate);
            query.setParameter("p_student_endDate", endDate);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }


    private static List<Student> findStudentByAvgMarkRange(double min, double max){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "FROM Student WHERE averageMark BETWEEN :p_student_minMark AND :p_student_maxMark";
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_minMark", min);
            query.setParameter("p_student_maxMark", max);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    public static List<Student> findBirthdayPeople(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        try {
            session.beginTransaction();
            String sql = "FROM Student WHERE to_number(to_char(birthDay, 'MM')) = :p_today_month AND " +
                         "to_number(to_char(birthDay, 'dd')) = :p_today_day";
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_today_month", month);
            query.setParameter("p_today_day", day);
            List<Student> students = query.getResultList();
            session.getTransaction().commit();
            return students;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return Collections.emptyList();
    }

    public static Student getStudentById(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Student WHERE id = :p_student_id";
        try {
            session.beginTransaction();
            Query<Student> query = session.createQuery(sql);
            query.setParameter("p_student_id", id);
            Student student = query.getSingleResult();
            session.getTransaction().commit();
            return student;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }
}
