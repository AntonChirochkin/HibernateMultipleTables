package hibernate.model.service;

import hibernate.model.Employee;
import hibernate.model.service.EmployeeDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Integer add(Employee employee) {
        Integer id;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(employee);
            transaction.commit();
        }
        return id;
    }

    @Override
    public Employee getById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee").list();
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updated;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            updated = (Employee) session.merge(employee);
            transaction.commit();
        }
        return updated;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}