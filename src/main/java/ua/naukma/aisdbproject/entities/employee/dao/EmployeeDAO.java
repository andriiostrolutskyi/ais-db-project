package ua.naukma.aisdbproject.entities.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.employee.model.Employee;

import java.util.List;

@Component
public class EmployeeDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll() {
        return jdbcTemplate.query("SELECT * FROM `employee`",
                new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getByID(String idEmployee) {
        return jdbcTemplate.query("SELECT * FROM `employee` WHERE id_employee=?", new Object[]{idEmployee},
                new BeanPropertyRowMapper<>(Employee.class)).stream().findAny().orElse(null);
    }

    public void add(Employee employee) {
        jdbcTemplate.update("INSERT INTO `employee` VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
                employee.getIdEmployee(), employee.getEmployeeSurname(), employee.getEmployeeName(),
                employee.getEmployeePatronymic(), employee.getEmployeeRole(), employee.getSalary(),
                employee.getDateOfBirth(), employee.getDateOfStart(), employee.getPhoneNumber(),
                employee.getPhoneNumber(), employee.getCity(), employee.getStreet(), employee.getZipCode());
    }

    public void update(String idEmployee, Employee updatedEmployee) {
        jdbcTemplate.update("UPDATE `employee` SET  empl_surname=?, empl_name=?, empl_patronymic=?, empl_role=?," +
                        "salary=?, date_of_birth=?, date_of_start=?, phone_number=?, city=?, street=?, zip_code=?" +
                        " WHERE id_employee=?",
                updatedEmployee.getEmployeeSurname(),
                updatedEmployee.getEmployeeName(),
                updatedEmployee.getEmployeePatronymic(),
                updatedEmployee.getEmployeeRole(),
                updatedEmployee.getSalary(),
                updatedEmployee.getDateOfBirth(),
                updatedEmployee.getDateOfStart(),
                updatedEmployee.getPhoneNumber(),
                updatedEmployee.getCity(),
                updatedEmployee.getStreet(),
                updatedEmployee.getZipCode(),
                idEmployee
        );
    }

    public void delete(String idEmployee) {
        jdbcTemplate.update("DELETE FROM `employee` WHERE id_employee=?", idEmployee);
    }
}
