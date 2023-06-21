package ua.naukma.aisdbproject.entities.customer_card.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.customer_card.model.CustomerCard;

import java.util.List;

@Component
public class CustomerCardDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerCardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CustomerCard> getAll() {
        return jdbcTemplate.query("SELECT c.card_number, c.cust_surname, c.cust_name, c.cust_patronymic, c.phone_number, c.city, c.street, c.zip_code, c.percent, SUM(s.product_number) AS bought_number" +
                        " FROM `customer_card` c" +
                        " LEFT JOIN `check` ch ON c.card_number = ch.card_number" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" +
                        " GROUP BY c.card_number",
                new BeanPropertyRowMapper<>(CustomerCard.class));
    }


    public CustomerCard getByID(String cardNumber) {
        return jdbcTemplate.query("SELECT c.card_number, c.cust_surname, c.cust_name, c.cust_patronymic, c.phone_number, c.city, c.street, c.zip_code, c.percent, SUM(s.product_number) AS bought_number" +
                        " FROM `customer_card` c" +
                        " LEFT JOIN `check` ch ON c.card_number = ch.card_number" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" + " WHERE c.card_number=?" +
                        " GROUP BY c.card_number", new Object[]{cardNumber},
                new BeanPropertyRowMapper<>(CustomerCard.class)).stream().findAny().orElse(null);
    }

    public CustomerCard getBySurname(String customerSurname) {
        return jdbcTemplate.query("SELECT c.card_number, c.cust_surname, c.cust_name, c.cust_patronymic, c.phone_number, c.city, c.street, c.zip_code, c.percent, SUM(s.product_number) AS bought_number" +
                        " FROM `customer_card` c" +
                        " LEFT JOIN `check` ch ON c.card_number = ch.card_number" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" + " WHERE c.cust_surname=?" +
                        " GROUP BY c.card_number", new Object[]{customerSurname},
                new BeanPropertyRowMapper<>(CustomerCard.class)).stream().findAny().orElse(null);
    }

    public CustomerCard getByNumber(String cardNumber) {
        return jdbcTemplate.query("SELECT c.card_number, c.cust_surname, c.cust_name, c.cust_patronymic, c.phone_number, c.city, c.street, c.zip_code, c.percent, SUM(s.product_number) AS bought_number" +
                        " FROM `customer_card` c" +
                        " LEFT JOIN `check` ch ON c.card_number = ch.card_number" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" + " WHERE c.card_number=?" +
                        " GROUP BY c.card_number", new Object[]{cardNumber},
                new BeanPropertyRowMapper<>(CustomerCard.class)).stream().findAny().orElse(null);
    }

    public List<CustomerCard> getByPercent(Integer percent) {
        return jdbcTemplate.query("SELECT c.card_number, c.cust_surname, c.cust_name, c.cust_patronymic, c.phone_number, c.city, c.street, c.zip_code, c.percent, SUM(s.product_number) AS bought_number" +
                        " FROM `customer_card` c" +
                        " LEFT JOIN `check` ch ON c.card_number = ch.card_number" +
                        " LEFT JOIN `sale` s ON ch.check_number = s.check_number" + " WHERE c.percent=?" +
                        " GROUP BY c.card_number", new Object[]{percent},
                new BeanPropertyRowMapper<>(CustomerCard.class));
    }

    public void add(CustomerCard customerCard) {
        jdbcTemplate.update("INSERT INTO `customer_card` VALUES (?,?,?,?,?,?,?,?,?)",
                customerCard.getCardNumber(),
                customerCard.getCustSurname(),
                customerCard.getCustName(),
                customerCard.getCustPatronymic(),
                customerCard.getPhoneNumber(),
                customerCard.getCity(),
                customerCard.getStreet(),
                customerCard.getZipCode(),
                customerCard.getPercent());
    }

    public void update(String cardNumber, CustomerCard updatedCustomerCard) {
        jdbcTemplate.update("UPDATE `customer_card` SET  cust_surname=?, cust_name=?, cust_patronymic=?," +
                        "phone_number=?, city=?, street=?, zip_code=?, percent=? WHERE card_number=?",
                updatedCustomerCard.getCustSurname(),
                updatedCustomerCard.getCustName(),
                updatedCustomerCard.getCustPatronymic(),
                updatedCustomerCard.getPhoneNumber(),
                updatedCustomerCard.getCity(),
                updatedCustomerCard.getStreet(),
                updatedCustomerCard.getZipCode(),
                updatedCustomerCard.getPercent(),
                cardNumber
        );
    }

    public void delete(String cardNumber) {
        jdbcTemplate.update("DELETE FROM `customer_card` WHERE  card_number=?", cardNumber);
    }
}
