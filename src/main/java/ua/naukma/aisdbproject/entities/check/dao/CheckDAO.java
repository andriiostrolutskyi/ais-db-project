package ua.naukma.aisdbproject.entities.check.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.check.model.Check;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class CheckDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CheckDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Check> getAll() {
        return jdbcTemplate.query("SELECT * FROM `check`",
                new BeanPropertyRowMapper<>(Check.class));
    }

    public Check getById(String checkNumber) {
        return jdbcTemplate.query("SELECT * FROM `check` WHERE check_number = ?", new Object[]{checkNumber},
                new BeanPropertyRowMapper<>(Check.class)).stream().findAny().orElse(null);
    }

    public List<Check> getByIdEmployee(String idEmployee) {
        return jdbcTemplate.query("SELECT * FROM `check` WHERE id_employee = ?", new Object[]{idEmployee},
                new BeanPropertyRowMapper<>(Check.class));
    }

    public List<Check> getByTime(String from, String to) {
        return jdbcTemplate.query("SELECT * FROM `check` WHERE print_date BETWEEN ? AND ?", new Object[]{from, to},
                new BeanPropertyRowMapper<>(Check.class));
    }

    public List<Check> getByTimeFrom(String startDate) {
        LocalDate ld = LocalDate.parse(startDate);
        return jdbcTemplate.query("SELECT * FROM `check` WHERE print_date >= ?", new Object[]{ld},
                new BeanPropertyRowMapper<>(Check.class));
    }
    public List<Check> getByTimeTo(String endDate) {
        LocalDate ld = LocalDate.parse(endDate);
        LocalDateTime localDateTime = LocalDateTime.of(ld, LocalTime.MAX);
        return jdbcTemplate.query("SELECT * FROM `check` WHERE print_date <= ?", new Object[]{localDateTime},
                new BeanPropertyRowMapper<>(Check.class));
    }


    public void add(Check check) {
        jdbcTemplate.update("INSERT INTO `check` VALUES (?,?,?,?,?,?)", check.getCheckNumber(),
                                                                            check.getIdEmployee(),
                                                                            check.getCardNumber(),
                                                                            check.getPrintDate(),
                                                                            check.getSumTotal(),
                                                                            check.getVat());
    }

    public void delete(String checkNumber) {
        jdbcTemplate.update("DELETE FROM `check` WHERE check_number=?", checkNumber);
    }
}

