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

    public List<Category> getCategories() {
        return jdbcTemplate.query("SELECT * FROM category",
                new BeanPropertyRowMapper<>(Category.class));
    }

    public void addCategory(Category category) {
        jdbcTemplate.update("INSERT INTO category VALUES (?,?)", category.getCategoryNumber(), category.getCategoryName());
    }

    public void updateCategory(Integer categoryNumber, Category updatedCategory) {
        jdbcTemplate.update("UPDATE zlagoda SET categoryNumber=?, categoryName=? WHERE categoryNumber=?",
                updatedCategory.getCategoryNumber(),
                updatedCategory.getCategoryName(),
                categoryNumber
        );
    }

    public void deleteCategory(Integer categoryNumber) {
        jdbcTemplate.update("DELETE FROM zlagoda WHERE categoryNumber=?", categoryNumber);
    }
}
