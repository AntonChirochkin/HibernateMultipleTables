import hibernate.model.service.CityDao;
import hibernate.model.service.CityDaoImpl;
import hibernate.model.service.EmployeeDao;
import hibernate.model.service.EmployeeDaoImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import hibernate.model.City;
import hibernate.model.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeeDaoImplTest {
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();
    private final CityDao cityDao = new CityDaoImpl();

    @AfterEach
    public void cleanUP() {
        cityDao.getAllCity().forEach(cityDao::deleteCity);
        assertTrue(cityDao.getAllCity().isEmpty());
        assertTrue(employeeDao.getAllEmployee().isEmpty());
    }

    @Test
    public void shouldSaveTheEmployee() {
        Employee employee = new Employee(3, "James", "Bond", "man", 48, null);
        employeeDao.add(employee);
        assertTrue(employeeDao.getAllEmployee().contains(employee));
        employeeDao.deleteEmployee(employee);
        assertFalse(employeeDao.getAllEmployee().contains(employee));
    }

    @Test
    public void shouldSaveTheCity() {
        //setup
        City city = City.builder()
                .city_name("Omsk")
                .build();
        // run
        cityDao.add(city);
        // assert
        assertTrue(cityDao.getAllCity().contains(city));
    }

    @Test
    public void shouldUpdateCity() {
        City city = City.builder()
                .city_name("Omsk")
                .build();
        City created = cityDao.add(city);
        assertTrue(cityDao.getAllCity().contains(city));
        created.setCity_name("Sochi");

        cityDao.updateCity(city);

        assertEquals(cityDao.getById(created.getCity_id()).getCity_name(), "Sochi");
    }

    @Test
    public void shouldCreateEmployeesWhenCityUpdate() {
        City city = City.builder().city_name("London").employees(List.of()).build();
        cityDao.add(city);
        assertTrue(cityDao.getAllCity().contains(city));
        Employee employee1 = Employee.builder()
                .firstName("James")
                .lastName("Bond")
                .gender("man")
                .city(city)
                .age(48)
                .build();
        Employee employee2 = Employee.builder()
                .firstName("Anna")
                .lastName("Koroleva")
                .gender("woman")
                .city(city)
                .age(34)
                .build();
        city.setEmployees(List.of(employee1, employee2));

        City updatedCity = cityDao.updateCity(city);

        assertTrue(employeeDao.getAllEmployee().containsAll(updatedCity.getEmployees()));
    }
}
