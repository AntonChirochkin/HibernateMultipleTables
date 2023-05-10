import hibernate.model.City;
import hibernate.model.Employee;
import hibernate.model.service.CityDao;
import hibernate.model.service.CityDaoImpl;
import hibernate.model.service.EmployeeDao;
import hibernate.model.service.EmployeeDaoImpl;
import lombok.*;
import javax.persistence.*;
import java.util.List;


public class Application {

    static CityDao cityDao = new CityDaoImpl();
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    public static void main(String[] args) {

        City city = City.builder().city_name("Ekaterinburg").employees(List.of()).build();
        cityDao.add(city);
        System.out.println("cities contains all intities: " + cityDao.getAllCity().contains(city));

        Employee employee1 = Employee.builder()
                .firstName("Alexander")
                .lastName("Klimov")
                .gender("man")
                .city(city)
                .age(45)
                .build();
        Employee employee2 = Employee.builder()
                .firstName("Olga")
                .lastName("Shavrova")
                .gender("woman")
                .city(city)
                .age(38)
                .build();
        city.setEmployees(List.of(employee1, employee2));
// проверка каскадности
        City updatedCity = cityDao.updateCity(city);

        System.out.println("All employee were saved: " + employeeDao.getAllEmployee().containsAll(updatedCity.getEmployees()));
    }
}