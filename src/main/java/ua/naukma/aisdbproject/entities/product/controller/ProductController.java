package ua.naukma.aisdbproject.entities.product.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.category.dao.CategoryDAO;
import ua.naukma.aisdbproject.entities.product.dao.ProductDAO;
import ua.naukma.aisdbproject.entities.product.model.Product;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    @Autowired
    public ProductController(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("products", productDAO.getAll());
        model.addAttribute("categories", productDAO.getCategoryNames());
        if (user.getUsrRole().equals("Manager")) {
            return "product/manager/show";
        } else {
            return "product/cashier/show";
        }
    }

    @GetMapping("/{productID}")
    @ResponseBody
    public boolean getByID(@PathVariable("productID") Integer productID) {
        return (productDAO.getByID(productID)) != null;
    }

    @GetMapping("/categoryNumber/{categoryNumber}")
    public String getByCategory(@PathVariable("categoryNumber") Integer categoryNumber, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("products", productDAO.getByCategory(categoryNumber));
        model.addAttribute("categories", categoryDAO.getAll());

        if (user.getUsrRole().equals("Manager")) {
            return "product/manager/show :: searchResults";
        } else {
            return "product/cashier/show :: searchResults";
        }
    }

    @GetMapping("/productName/{productName}")
    public String getByName(@PathVariable("productName") String productName, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("products", productDAO.getByName(productName));
        model.addAttribute("categories", categoryDAO.getAll());
        if (user.getUsrRole().equals("Manager")) {
            return "product/manager/show :: searchResults";
        } else {
            return "product/cashier/show :: searchResults";
        }
    }

    @GetMapping("/canBeDeleted/{idProduct}")
    public ResponseEntity<Boolean> canBeDeleted(@PathVariable("idProduct") Integer idProduct) {
        return ResponseEntity.ok(productDAO.canBeDeleted(idProduct));
    }

    @GetMapping("/add-product")
    public String goToAdd(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryDAO.getAll());
        return "product/manager/add";
    }

    @PostMapping
    public String add(@ModelAttribute("product") @Valid Product product,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/api/v1/product/add-product";
        productDAO.add(product);
        return "redirect:/api/v1/product";
    }

    @GetMapping("/{idProduct}/edit")
    public String edit(Model model,
                       @PathVariable("idProduct") Integer idProduct, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("product", productDAO.getByID(idProduct));
        model.addAttribute("categories", categoryDAO.getAll());
        return "product/manager/edit";
    }

    @PatchMapping("/{idProduct}")
    public String update(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult,
                         @PathVariable("idProduct") Integer idProduct) {
        if (bindingResult.hasErrors())
            return "product/manager/edit";
        productDAO.update(idProduct, product);
        return "redirect:/api/v1/product";
    }

    @DeleteMapping("/{idProduct}")
    public String delete(@PathVariable("idProduct") Integer idProduct) {
        productDAO.delete(idProduct);
        return "redirect:/api/v1/product";
    }
}