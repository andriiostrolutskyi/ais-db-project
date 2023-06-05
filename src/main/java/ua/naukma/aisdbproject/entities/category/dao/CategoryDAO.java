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
        return jdbcTemplate.query("SELECT * FROM `category`",
                new BeanPropertyRowMapper<>(Category.class));
    }

    public Category getByID(Integer categoryNumber) {
        return jdbcTemplate.query("SELECT * FROM `category` WHERE category_number=?", new Object[]{categoryNumber},
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

    public void delete(Integer categoryNumber) {
        jdbcTemplate.update("DELETE FROM `category` WHERE category_number=?", categoryNumber);
    }
}
