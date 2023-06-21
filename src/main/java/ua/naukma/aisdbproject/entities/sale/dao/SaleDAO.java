package ua.naukma.aisdbproject.entities.sale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.sale.model.Sale;

import java.util.List;

@Component
public class SaleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SaleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sale> getAll() {
        return jdbcTemplate.query("SELECT * FROM `sale`",
                new BeanPropertyRowMapper<>(Sale.class));
    }

    public Sale getByID(String upc, String checkNumber) {
        return jdbcTemplate.query("SELECT * FROM `sale` WHERE UPC=? AND check_number=?", new Object[]{upc, checkNumber},
                new BeanPropertyRowMapper<>(Sale.class)).stream().findAny().orElse(null);
    }

    public List<Sale> getByCheck(String checkNumber) {
        return jdbcTemplate.query("SELECT * FROM `sale` WHERE check_number=?", new Object[]{checkNumber},
                new BeanPropertyRowMapper<>(Sale.class));
    }

    public void add(Sale sale) {
        jdbcTemplate.update("INSERT INTO `sale` VALUES (?,?,?,?)", sale.getUpc(), sale.getCheckNumber(),
                sale.getProductNumber(), sale.getSellingPrice());
        jdbcTemplate.update("UPDATE `store_product` SET products_number= products_number - ? WHERE UPC=?",
                sale.getProductNumber(),
                sale.getUpc());
    }

    public void update(String upc, String checkNumber, Sale updatedSale) {
        jdbcTemplate.update("UPDATE `sale` SET  product_number=?, selling_price=? WHERE UPC=? AND check_number=?",
                updatedSale.getProductNumber(),
                updatedSale.getSellingPrice(),
                upc, checkNumber
        );
    }

    public Float getTotalSum(String checkNumber) {
        return jdbcTemplate.queryForObject("SELECT SUM(selling_price) AS totalSum " +
                "FROM `sale` " +
                "WHERE check_number = ?;", new Object[]{checkNumber}, Float.class);
    }

    public void delete(String upc, String checkNumber) {
        jdbcTemplate.update("UPDATE `store_product` SET products_number= products_number + ? WHERE UPC=?",
                (getByID(upc, checkNumber)).getProductNumber(),
                upc);
        jdbcTemplate.update("DELETE FROM `sale` WHERE UPC=? AND check_number=?", upc, checkNumber);
    }
}
