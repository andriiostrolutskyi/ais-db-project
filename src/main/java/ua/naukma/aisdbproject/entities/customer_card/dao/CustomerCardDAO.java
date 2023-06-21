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
        return jdbcTemplate.query("SELECT * FROM `customer_card` ORDER BY cust_surname ASC",
                new BeanPropertyRowMapper<>(CustomerCard.class));
    }

    public List<CustomerCard> getSpecial() {
        return jdbcTemplate.query(
                    "SELECT cc.card_number, cc.cust_surname, cc.cust_name, cc.cust_patronymic, cc.phone_number, cc.city, cc.street, cc.zip_code, cc.percent" +
                        " FROM `customer_card` AS cc" +
                        " WHERE NOT EXISTS (SELECT c.category_number" +
                        "                   FROM `category` AS c" +
                        "                   WHERE NOT EXISTS (SELECT cc.card_number, sp.UPC, c.category_number" +
                        "                                    FROM ((((`store_product` AS sp" +
                        "                                            INNER JOIN `product` ON sp.id_product = product.id_product)" +
                        "                                            INNER JOIN `sale` ON sp.UPC = sale.UPC)" +
                        "                                            INNER JOIN `check` ON sale.check_number = `check`.check_number)" +
                        "                                            INNER JOIN `customer_card` ON `check`.card_number = customer_card.card_number)" +
                        "                                        INNER JOIN `category` ON product.category_number = category.category_number" +
                        "                                        WHERE customer_card.card_number = cc.card_number AND" +
                        "                                        category.category_number = c.category_number" +
                        "                                    )" +
                        "                )",
                new BeanPropertyRowMapper<>(CustomerCard.class));
    }

    public CustomerCard getByID(String cardNumber) {
        return jdbcTemplate.query("SELECT * FROM `customer_card` WHERE card_number=?", new Object[]{cardNumber},
                new BeanPropertyRowMapper<>(CustomerCard.class)).stream().findAny().orElse(null);
    }

    public CustomerCard getBySurname(String customerSurname) {
        return jdbcTemplate.query("SELECT * FROM `customer_card` WHERE cust_surname=?", new Object[]{customerSurname},
                new BeanPropertyRowMapper<>(CustomerCard.class)).stream().findAny().orElse(null);
    }

    public CustomerCard getByNumber(String cardNumber) {
        return jdbcTemplate.query("SELECT * FROM `customer_card` WHERE card_number=?", new Object[]{cardNumber},
                new BeanPropertyRowMapper<>(CustomerCard.class)).stream().findAny().orElse(null);
    }

    public List<CustomerCard> getByPercent(Integer percent) {
        return jdbcTemplate.query("SELECT * FROM `customer_card` WHERE percent=? ORDER BY cust_surname", new Object[]{percent},
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
