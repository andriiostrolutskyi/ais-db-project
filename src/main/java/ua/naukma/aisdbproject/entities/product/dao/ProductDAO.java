package ua.naukma.aisdbproject.entities.product.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.product.model.Product;

import java.util.List;

@Component
public class ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAll() {
        return jdbcTemplate.query("SELECT * FROM `product`",
                new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getByID(Integer idProduct) {
        return jdbcTemplate.query("SELECT * FROM `product` WHERE id_product=?", new Object[]{idProduct},
                new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    public void add(Product product) {
        jdbcTemplate.update("INSERT INTO `product` VALUES (?,?,?,?)",
                product.getIdProduct(), product.getCategoryNumber(),
                product.getProductName(), product.getCharacteristics()
        );
    }

    public void update(Integer idProduct, Product updatedProduct) {
        jdbcTemplate.update("UPDATE `product` SET category_number=?,  product_name=?, characteristics=? WHERE id_product=?",
                updatedProduct.getCategoryNumber(),
                updatedProduct.getProductName(),
                updatedProduct.getCharacteristics(),
                idProduct
        );
    }

    public void delete(Integer idProduct) {
        jdbcTemplate.update("DELETE FROM `product` WHERE id_product=?", idProduct);
    }
}
