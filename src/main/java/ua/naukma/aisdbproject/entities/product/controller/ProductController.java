package ua.naukma.aisdbproject.entities.product.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.category.dao.CategoryDAO;
import ua.naukma.aisdbproject.entities.product.dao.ProductDAO;
import ua.naukma.aisdbproject.entities.product.model.Product;

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
    public String getAll(Model model) {
        model.addAttribute("products", productDAO.getAll());
        return "product/show";
    }

    @GetMapping("/categoryNumber/{categoryNumber}")
    public String getByCategory(@PathVariable("categoryNumber") Integer categoryNumber, Model model) {
        model.addAttribute("products", productDAO.getByCategory(categoryNumber));
        model.addAttribute("categories", categoryDAO.getAll());
        return "product/show :: searchResults";
    }
    @GetMapping("/productName/{productName}")
    public String getByName(@PathVariable("productName") String productName, Model model) {
        model.addAttribute("products", productDAO.getByName(productName));
        model.addAttribute("categories", categoryDAO.getAll());
        return "product/show :: searchResults";
    }

    @GetMapping("/add-product")
    public String goToAdd(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryDAO.getAll());
        return "product/add";
    }

    @PostMapping
    public String add(@ModelAttribute("product") @Valid Product product,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "product/add";
        productDAO.add(product);
        return "redirect:/api/v1/product";
    }

    @GetMapping("/{idProduct}/edit")
    public String edit(Model model,
                       @PathVariable("idProduct") Integer idProduct) {
        model.addAttribute("product", productDAO.getByID(idProduct));
        model.addAttribute("categories", categoryDAO.getAll());
        return "product/edit";
    }

    @PatchMapping("/{idProduct}")
    public String update(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult,
                         @PathVariable("idProduct") Integer idProduct) {
        if (bindingResult.hasErrors())
            return "product/edit";
        productDAO.update(idProduct, product);
        return "redirect:/api/v1/product";
    }

    @DeleteMapping("/{idProduct}")
    public String delete(@PathVariable("idProduct") Integer idProduct) {
        productDAO.delete(idProduct);
        return "redirect:/api/v1/product";
    }
}