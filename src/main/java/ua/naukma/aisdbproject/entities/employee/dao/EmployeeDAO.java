package ua.naukma.aisdbproject.entities.employee.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.customer_card.model.CustomerCard;
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
        return jdbcTemplate.query("SELECT e.id_employee, e.empl_surname, e.empl_name, e.empl_patronymic, e.empl_role, e.salary, e.date_of_birth, e.date_of_start, e.phone_number, e.city, e.street, e.zip_code, SUM(s.product_number) AS sold_number" +
                        " FROM `employee` e" +
                        " LEFT JOIN `check` ch ON e.id_employee = ch.id_employee" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" +
                        " GROUP BY e.id_employee",
                new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> getSpecial() {
        return jdbcTemplate.query("SELECT e.id_employee, e.empl_surname, e.empl_name, e.empl_patronymic, e.empl_role, e.salary, e.date_of_birth, e.date_of_start, e.phone_number, e.city, e.street" +
                        " FROM employee AS e" +
                        " WHERE NOT EXISTS (SELECT c.category_number" +
                        "                   FROM category AS c" +
                        "                   WHERE NOT EXISTS (SELECT e.id_employee, sp.UPC, c.category_number" +
                        "                                    FROM ((((store_product AS sp" +
                        "                                            INNER JOIN product ON sp.id_product = product.id_product)" +
                        "                                            INNER JOIN sale ON sp.UPC = sale.UPC)" +
                        "                                            INNER JOIN `check` ON sale.check_number = `check`.check_number)" +
                        "                                            INNER JOIN employee ON check.id_employee = employee.id_employee)" +
                        "                                        INNER JOIN category ON product.category_number = category.category_number" +
                        "                                        WHERE employee.id_employee = e.id_employee AND" +
                        "                                        categorycategory_number = c.category_number" +
                        "                                    )" +
                        "                );",
                new BeanPropertyRowMapper<>(Employee.class));
    }


    public Employee getByID(String idEmployee) {
        return jdbcTemplate.query("SELECT e.id_employee, e.empl_surname, e.empl_name, e.empl_patronymic, e.empl_role, e.salary, e.date_of_birth, e.date_of_start, e.phone_number, e.city, e.street, e.zip_code, SUM(s.product_number) AS sold_number" +
                        " FROM `employee` e" +
                        " LEFT JOIN `check` ch ON e.id_employee = ch.id_employee" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" + " WHERE e.id_employee=?" +
                        " GROUP BY e.id_employee", new Object[]{idEmployee},
                new BeanPropertyRowMapper<>(Employee.class)).stream().findAny().orElse(null);
    }

    public List<Employee> getBySurname(String surnameEmployee) {
        return jdbcTemplate.query("SELECT e.id_employee, e.empl_surname, e.empl_name, e.empl_patronymic, e.empl_role, e.salary, e.date_of_birth, e.date_of_start, e.phone_number, e.city, e.street, e.zip_code, SUM(s.product_number) AS sold_number" +
                        " FROM `employee` e" +
                        " LEFT JOIN `check` ch ON e.id_employee = ch.id_employee" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" + " WHERE e.empl_surname=?" +
                        " GROUP BY e.id_employee", new Object[]{surnameEmployee},
                new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> getCashiers() {
        String query = "SELECT e.id_employee, e.empl_surname, e.empl_name, e.empl_patronymic, e.empl_role, e.salary, e.date_of_birth, e.date_of_start, e.phone_number, e.city, e.street, e.zip_code, SUM(s.product_number) AS sold_number" +
                " FROM `employee` e" +
                " LEFT JOIN `check` ch ON e.id_employee = ch.id_employee" +
                " LEFT JOIN `sale` s ON ch.check_number = s.check_number" + " WHERE e.empl_role='Cashier'" +
                " GROUP BY e.id_employee";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Employee.class));
    }


    public void add(Employee employee) {
        jdbcTemplate.update("INSERT INTO `employee` VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
                employee.getIdEmployee(), employee.getEmplSurname(), employee.getEmplName(),
                employee.getEmplPatronymic(), employee.getEmplRole(), employee.getSalary(),
                employee.getDateOfBirth(), employee.getDateOfStart(), employee.getPhoneNumber(),
                employee.getCity(), employee.getStreet(), employee.getZipCode());
        jdbcTemplate.update("INSERT INTO `users` VALUES (?,?,?)",
                employee.getIdEmployee(),
                BCrypt.hashpw(employee.getIdEmployee(), BCrypt.gensalt()),
                employee.getEmplRole());
    }

    public void update(String idEmployee, Employee updatedEmployee) {
        jdbcTemplate.update("UPDATE `employee` SET  empl_surname=?, empl_name=?, empl_patronymic=?, empl_role=?," +
                        "salary=?, date_of_birth=?, date_of_start=?, phone_number=?, city=?, street=?, zip_code=?" +
                        " WHERE id_employee=?",
                updatedEmployee.getEmplSurname(),
                updatedEmployee.getEmplName(),
                updatedEmployee.getEmplPatronymic(),
                updatedEmployee.getEmplRole(),
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
        jdbcTemplate.update("DELETE FROM `users` WHERE usr_username=?", idEmployee);
    }
}

