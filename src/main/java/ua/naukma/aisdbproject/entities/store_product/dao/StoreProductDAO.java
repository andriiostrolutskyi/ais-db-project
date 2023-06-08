package ua.naukma.aisdbproject.entities.store_product.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.category.model.Category;
import ua.naukma.aisdbproject.entities.store_product.model.StoreProduct;

import java.util.List;

@Component
public class StoreProductDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StoreProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StoreProduct> getAll() {
        return jdbcTemplate.query("SELECT * FROM `store_product`",
                new BeanPropertyRowMapper<>(StoreProduct.class));
    }

    public StoreProduct getByID(String upc) {
        return jdbcTemplate.query("SELECT * FROM `store_product` WHERE UPC=?", new Object[]{upc},
                new BeanPropertyRowMapper<>(StoreProduct.class)).stream().findAny().orElse(null);
    }

    public void add(StoreProduct storeProduct) {
        jdbcTemplate.update("INSERT INTO `store_product` VALUES (?,?,?,?,?,?)",
                storeProduct.getUpc(), storeProduct.getUpcProm(), storeProduct.getIdProduct(),
                storeProduct.getSellingPrice(), storeProduct.getProductsNumber(), storeProduct.getPromotionalProduct());
    }

    public void update(String upc, StoreProduct updatedStoreProduct) {
        jdbcTemplate.update("UPDATE `store_product` SET  UPC_prom=?, id_product=?, selling_price=?, products_number=?," +
                        "promotional_product=? WHERE UPC=?",
                updatedStoreProduct.getUpcProm(),
                updatedStoreProduct.getIdProduct(),
                updatedStoreProduct.getSellingPrice(),
                updatedStoreProduct.getProductsNumber(),
                updatedStoreProduct.getPromotionalProduct(),
                upc
        );
    }

    public void delete(String upc) {
        jdbcTemplate.update("DELETE FROM `store_product` WHERE UPC=?", upc);
    }
}
