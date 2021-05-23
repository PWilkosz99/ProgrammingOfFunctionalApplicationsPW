package entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

public class Connection {
    private static SessionFactory factory;

    public static void main(String[] args) {



        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Connection ME = new Connection();

        ME.listEmployees();

    }

    /* Method to CREATE an employee in the database */
    public Integer addTrain(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            TrainsEntity employee = new TrainsEntity(i1, i2, i3, i4);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM TrainsEntity").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
                TrainsEntity employee = (TrainsEntity) iterator.next();
                System.out.print("First Name: " + employee.getCars());
                System.out.print("  Last Name: " + employee.getCapacity());
                System.out.println("  Salary: " + employee.getId());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}