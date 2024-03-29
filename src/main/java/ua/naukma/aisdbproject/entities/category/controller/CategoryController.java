package ua.naukma.aisdbproject.entities.category.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.category.dao.CategoryDAO;
import ua.naukma.aisdbproject.entities.category.model.Category;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {

        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("categories", categoryDAO.getAll());

        if (user.getUsrRole().equals("Manager")) {
            return "category/manager/show";
        } else {
            return "category/cashier/show";
        }
    }

    @GetMapping("/{categoryNumber}")
    public String getByID(@PathVariable("categoryNumber") Integer categoryNumber, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("category", categoryDAO.getByID(categoryNumber));
        if (user.getUsrRole().equals("Manager")) {
            return "category/manager/show";
        } else {
            return "category/cashier/show";
        }
    }

    @GetMapping("/validate/{categoryNumber}")
    @ResponseBody
    public boolean getByIDValidation(@PathVariable("categoryNumber") Integer categoryNumber) {
        return (categoryDAO.getByID(categoryNumber)) != null;
    }

    @GetMapping("/add-category")
    public String goToAdd(Model model, HttpSession session) {

        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }

        model.addAttribute("category", new Category());
        return "category/manager/add";
    }

    @PostMapping
    public String add(@ModelAttribute("category") @Valid Category category,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "category/manager/add";
        categoryDAO.add(category);
        return "redirect:/api/v1/category";
    }

    @GetMapping("/{categoryNumber}/edit")
    public String edit(Model model,
                       @PathVariable("categoryNumber") Integer categoryNumber, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("category", categoryDAO.getByID(categoryNumber));
        return "category/manager/edit";
    }

    @PatchMapping("/{categoryNumber}")
    public String update(@ModelAttribute("category") @Valid Category category,
                         BindingResult bindingResult,
                         @PathVariable("categoryNumber") Integer categoryNumber) {
        if (bindingResult.hasErrors())
            return "category/manager/edit";
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
    public String getByNumber(@PathVariable("categoryNumber") Integer categoryNumber, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("categories", categoryDAO.getByID(categoryNumber));
        if (user.getUsrRole().equals("Manager")) {
            return "category/manager/show :: searchResults";
        } else {
            return "category/cashier/show :: searchResults";
        }
    }

    @GetMapping("/categoryName/{categoryName}")
    public String getByName(@PathVariable("categoryName") String categoryName, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("categories", categoryDAO.getByName(categoryName));
        if (user.getUsrRole().equals("Manager")) {
            return "category/manager/show :: searchResults";
        } else {
            return "category/cashier/show :: searchResults";
        }
    }
}