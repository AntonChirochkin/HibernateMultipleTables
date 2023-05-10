package hibernate.model.service;

import hibernate.model.City;

import java.util.List;

public interface CityDao {
    City add(City city);

    City getById(int id);

    List<City> getAllCity();

    City updateCity(City city);

    void deleteCity(City city);
}
