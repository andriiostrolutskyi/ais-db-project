package ua.naukma.aisdbproject.entities.category.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.category.dao.CategoryDAO;
import ua.naukma.aisdbproject.entities.category.model.Category;

import java.io.IOException;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("categories", categoryDAO.getAll());
        return "category/show";
    }

    @GetMapping("/{categoryNumber}")
    public String getByID(@PathVariable("categoryNumber") Integer categoryNumber, Model model) {
        model.addAttribute("category", categoryDAO.getByID(categoryNumber));
        return "category/show";
    }

    @GetMapping("/add-category")
    public String goToAdd(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping
    public String add(@ModelAttribute("category") @Valid Category category,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "category/add";
        categoryDAO.add(category);
        return "redirect:/api/v1/category";
    }

    @GetMapping("/{categoryNumber}/edit")
    public String edit(Model model,
                       @PathVariable("categoryNumber") Integer categoryNumber) {
        model.addAttribute("category", categoryDAO.getByID(categoryNumber));
        return "category/edit";
    }

    @PatchMapping("/{categoryNumber}")
    public String update(@ModelAttribute("category") @Valid Category category,
                         BindingResult bindingResult,
                         @PathVariable("categoryNumber") Integer categoryNumber) {
        if (bindingResult.hasErrors())
            return "category/edit";
        categoryDAO.update(categoryNumber, category);
        return "redirect:/api/v1/category";
    }

    @GetMapping("/canBeDeleted/{categoryNumber}")
    public ResponseEntity<Boolean> canBeDeleted(@PathVariable("categoryNumber") Integer categoryNumber) {
        return ResponseEntity.ok(categoryDAO.canBeDeleted(categoryNumber));
    }

    @DeleteMapping("/{categoryNumber}")
    public String delete(@PathVariable("categoryNumber") Integer categoryNumber) {
        categoryDAO.delete(categoryNumber);
        return "redirect:/api/v1/category";
    }

    @GetMapping("/categoryNumber/{categoryNumber}")
    public String getByNumber(@PathVariable("categoryNumber") Integer categoryNumber, Model model) {
        model.addAttribute("categories", categoryDAO.getByID(categoryNumber));
        return "category/show :: searchResults";
    }

    @GetMapping("/categoryName/{categoryName}")
    public String getByName(@PathVariable("categoryName") String categoryName, Model model) {
        model.addAttribute("categories", categoryDAO.getByName(categoryName));
        return "category/show :: searchResults";
    }

}