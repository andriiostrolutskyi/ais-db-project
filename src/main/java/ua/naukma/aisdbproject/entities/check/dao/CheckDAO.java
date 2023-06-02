package ua.naukma.aisdbproject.entities.check.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.check.model.Check;

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
        return jdbcTemplate.query("SELECT * FROM `check` WHERE check_number=?", new Object[]{checkNumber},
                new BeanPropertyRowMapper<>(Check.class)).stream().findAny().orElse(null);
    }

    public void add(Check check) {
        jdbcTemplate.update("INSERT INTO `check` VALUES (?,?,?,?,?,?)", check.getCheckNumber(),
                                                                            check.getIdEmployee(),
                                                                            check.getCardNumber(),
                                                                            check.getPrintDate(),
                                                                            check.getSumTotal(),
                                                                            check.getVat());
    }

    public void update(String checkNumber, Check updatedCheck) {
        jdbcTemplate.update("UPDATE `check` SET id_employee=?, card_number=?, print_date=?, sum_total=?, vat=? WHERE check_number=?",
                updatedCheck.getIdEmployee(),
                updatedCheck.getCardNumber(),
                updatedCheck.getPrintDate(),
                updatedCheck.getSumTotal(),
                updatedCheck.getVat(),
                checkNumber
        );
    }

    public void delete(String checkNumber) {
        jdbcTemplate.update("DELETE FROM `check` WHERE check_number=?", checkNumber);
    }
}