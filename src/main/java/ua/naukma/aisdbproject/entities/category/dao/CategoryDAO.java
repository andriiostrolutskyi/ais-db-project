package ua.naukma.aisdbproject.entities.category.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.naukma.aisdbproject.entities.category.model.Category;

import java.util.List;

@Component
public class CategoryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Category> getAll() {
        return jdbcTemplate.query("SELECT c.category_number, c.category_name, SUM(sp.products_number) AS products_number" +
                        " FROM `category` c" + " INNER JOIN `product` p ON c.category_number = p.category_number" +
                        " INNER JOIN `store_product` sp ON p.id_product = sp.id_product" +
                        " GROUP BY c.category_number",
                new BeanPropertyRowMapper<>(Category.class));
    }

    public Category getByID(Integer categoryNumber) {
        return jdbcTemplate.query("SELECT c.category_number, c.category_name, SUM(sp.products_number) AS products_number" +
                        " FROM `category` c" + " INNER JOIN `product` p ON c.category_number = p.category_number" +
                        " INNER JOIN `store_product` sp ON p.id_product = sp.id_product" + " WHERE c.category_number=?" +
                        " GROUP BY c.category_number", new Object[]{categoryNumber},
                new BeanPropertyRowMapper<>(Category.class)).stream().findAny().orElse(null);
    }

    public Category getByName(String categoryName) {
        return jdbcTemplate.query("SELECT c.category_number, c.category_name, SUM(sp.products_number) AS products_number" +
                        " FROM `category` c" + " INNER JOIN `product` p ON c.category_number = p.category_number" +
                        " INNER JOIN `store_product` sp ON p.id_product = sp.id_product" + " WHERE c.category_name=?" +
                        " GROUP BY c.category_number", new Object[]{categoryName},
                new BeanPropertyRowMapper<>(Category.class)).stream().findAny().orElse(null);
    }

    public void add(Category category) {
        jdbcTemplate.update("INSERT INTO `category` VALUES (?,?)", category.getCategoryNumber(), category.getCategoryName());
    }

    public void update(Integer categoryNumber, Category updatedCategory) {
        jdbcTemplate.update("UPDATE `category` SET category_name=? WHERE category_number=?",
                updatedCategory.getCategoryName(),
                categoryNumber
        );
    }

    public boolean canBeDeleted(Integer categoryNumber) {
        List<Category> categories =
                jdbcTemplate.query("SELECT DISTINCT `category`.category_number " +
                        "FROM `category` " +
                        "INNER JOIN `product` " +
                        "ON `category`.category_number = `product`.category_number " +
                        "WHERE product.category_number = ?", new Object[]{categoryNumber}, new BeanPropertyRowMapper<>(Category.class));
        return categories.isEmpty();
    }

    public void delete(Integer categoryNumber) {
        jdbcTemplate.update("DELETE FROM `category` WHERE category_number=?", categoryNumber);
    }
}
