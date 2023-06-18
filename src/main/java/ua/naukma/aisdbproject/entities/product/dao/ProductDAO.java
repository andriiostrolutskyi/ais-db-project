package ua.naukma.aisdbproject.entities.product.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.category.model.Category;
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
        return jdbcTemplate.query("SELECT * FROM `product` ORDER BY product_name",
                new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getByID(Integer idProduct) {
        return jdbcTemplate.query("SELECT * FROM `product` WHERE id_product=?", new Object[]{idProduct},
                new BeanPropertyRowMapper<>(Product.class)).stream().findAny().orElse(null);
    }

    public boolean canBeDeleted(Integer idProduct) {
        List<Product> products =
                jdbcTemplate.query("SELECT DISTINCT `product`.id_product " +
                        "FROM `product` " +
                        "INNER JOIN `store_product` " +
                        "ON `product`.id_product = `store_product`.id_product " +
                        "WHERE `product`.id_product = ?", new Object[]{idProduct}, new BeanPropertyRowMapper<>(Product.class));
        return products.isEmpty();
    }


    public List<Product> getByCategory(Integer categoryNumber) {
        return jdbcTemplate.query("SELECT * FROM `product` WHERE category_number=?", new Object[]{categoryNumber},
                new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Category> getCategoryNames() {
        return jdbcTemplate.query("SELECT DISTINCT `category`.category_number, category_name " +
                "FROM product " +
                "INNER JOIN category " +
                "ON product.category_number=category.category_number;", new BeanPropertyRowMapper<>(Category.class));
    }

    public Product getByName(String productName) {
        return jdbcTemplate.query("SELECT * FROM `product` WHERE product_name=?", new Object[]{productName},
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
