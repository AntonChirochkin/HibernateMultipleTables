package hibernate.model.service;

import hibernate.model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDaoImpl implements CityDao {
    @Override
    public City add(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(city);
            transaction.commit();
        }
        return city;
    }

    @Override
    public City getById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> getAllCity() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM City").list();
        }
    }

    @Override
    public City updateCity(City city) {
        City updated;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            updated = (City) session.merge(city);
            transaction.commit();
        }
        return updated;
    }

    @Override
    public void deleteCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}
