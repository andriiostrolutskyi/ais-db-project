package ua.naukma.aisdbproject.entities.category.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.category.dao.CategoryDAO;
import ua.naukma.aisdbproject.entities.category.model.Category;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryDAO.getCategories();
    }

    @PostMapping("/add-category")
    public void addCategory(@ModelAttribute("category") @Valid Category category){
        categoryDAO.addCategory(category);
    }

    @PatchMapping("/update/{categoryNumber}")
    public void updateCategory(@ModelAttribute("category") @Valid Category category,
                               @PathVariable Integer categoryNumber){
         categoryDAO.updateCategory(categoryNumber, category);
    }

    @DeleteMapping("/delete/{categoryNumber}")
    public void deleteCategory(@PathVariable Integer categoryNumber){
        categoryDAO.deleteCategory(categoryNumber);
    }
}
