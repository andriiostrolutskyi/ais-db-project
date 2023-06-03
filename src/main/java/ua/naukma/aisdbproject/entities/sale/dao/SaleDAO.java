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

    public void update(String upc, String checkNumber, Sale updatedSale) {
        jdbcTemplate.update("UPDATE `sale` SET  product_number=?, selling_price=? WHERE UPC=? AND check_number=?",
                updatedSale.getProductNumber(),
                updatedSale.getSellingPrice(),
                upc, checkNumber
        );
    }
}
