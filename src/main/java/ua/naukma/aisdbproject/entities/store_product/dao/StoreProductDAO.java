package ua.naukma.aisdbproject.entities.store_product.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.product.model.Product;
import ua.naukma.aisdbproject.entities.store_product.model.StoreProduct;
import ua.naukma.aisdbproject.entities.store_product.model.StoreProductSearch;

import java.util.List;

@Component
public class StoreProductDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StoreProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StoreProduct> getAll() {
        return jdbcTemplate.query("SELECT * FROM `store_product` ORDER BY products_number",
                new BeanPropertyRowMapper<>(StoreProduct.class));
    }

    public List<Product> getProductNames() {
        return jdbcTemplate.query("SELECT DISTINCT product.id_product, category_number, product_name, characteristics " +
                "FROM `store_product` " +
                "INNER JOIN `product` " +
                "ON `store_product`.id_product = `product`.id_product;", new BeanPropertyRowMapper<>(Product.class));
    }

    public StoreProductSearch getProductByUPC(String upc) {
        return jdbcTemplate.queryForObject
                ("SELECT DISTINCT `store_product`.upc, selling_price, products_number, product_name, characteristics " +
                        "FROM store_product " +
                        "INNER JOIN product " +
                        "ON store_product.id_product = product.id_product " +
                        "WHERE UPC=?;", new Object[]{upc}, new BeanPropertyRowMapper<>(StoreProductSearch.class));
    }

    public StoreProduct getByID(String upc) {
        return jdbcTemplate.query("SELECT * FROM `store_product` WHERE UPC=?", new Object[]{upc},
                new BeanPropertyRowMapper<>(StoreProduct.class)).stream().findAny().orElse(null);
    }

    public List<StoreProduct> getPromotionalByName() {
        return jdbcTemplate.query("SELECT DISTINCT  UPC, UPC_prom, store_product.id_product, product_name, selling_price, products_number, promotional_product " +
                        "FROM `store_product` INNER JOIN `product`" +
                        "ON store_product.id_product = product.id_product " +
                        "WHERE promotional_product=true " +
                        "ORDER BY product_name;",
                new BeanPropertyRowMapper<>(StoreProduct.class));
    }

    public List<StoreProduct> getPromotionalByQuantity() {
        return jdbcTemplate.query("SELECT * FROM `store_product` " +
                        "WHERE promotional_product=true " +
                        "ORDER BY products_number;",
                new BeanPropertyRowMapper<>(StoreProduct.class));
    }

    public List<StoreProduct> getNotPromotionalByName() {
        return jdbcTemplate.query("SELECT DISTINCT UPC, UPC_prom, store_product.id_product, product_name, selling_price, products_number, promotional_product " +
                        "FROM `store_product` INNER JOIN `product` " +
                        "ON store_product.id_product = product.id_product " +
                        "WHERE promotional_product=false " +
                        "ORDER BY product_name;",
                new BeanPropertyRowMapper<>(StoreProduct.class));
    }

    public List<StoreProduct> getNotPromotionalByQuantity() {
        return jdbcTemplate.query("SELECT * FROM `store_product` " +
                        "WHERE promotional_product=false " +
                        "ORDER BY products_number;",
                new BeanPropertyRowMapper<>(StoreProduct.class));
    }

    public void add(StoreProduct storeProduct) {
        String upcProm = null;
        if (!(storeProduct.getUpcProm().isEmpty()))
            upcProm = storeProduct.getUpcProm();
        jdbcTemplate.update("INSERT INTO `store_product` VALUES (?,?,?,?,?,?)",
                storeProduct.getUpc(), upcProm, storeProduct.getIdProduct(),
                storeProduct.getSellingPrice(), storeProduct.getProductsNumber(), storeProduct.getPromotionalProduct());
    }

    public void update(String upc, StoreProduct updatedStoreProduct) {
        String upcProm = null;
        if (!(updatedStoreProduct.getUpcProm().isEmpty()))
            upcProm = updatedStoreProduct.getUpcProm();
        jdbcTemplate.update("UPDATE `store_product` SET  UPC_prom=?, id_product=?, selling_price=?, products_number=?," +
                        "promotional_product=? WHERE UPC=?",
                upcProm,
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
