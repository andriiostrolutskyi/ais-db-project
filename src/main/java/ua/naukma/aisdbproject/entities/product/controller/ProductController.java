package ua.naukma.aisdbproject.entities.product.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.product.dao.ProductDAO;
import ua.naukma.aisdbproject.entities.product.model.Product;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", productDAO.getAll());
        return "product/show";
    }

    @GetMapping("/{idProduct}")
    public String getByID(@PathVariable("idProduct") Integer idProduct, Model model) {
        model.addAttribute("product", productDAO.getByID(idProduct));
        return "product/show";
    }

    @GetMapping("/add-product")
    public String goToAdd(Model model) {
        model.addAttribute("product", new Product());
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
                       @PathVariable Integer idProduct) {
        model.addAttribute("product", productDAO.getByID(idProduct));
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
    public String delete(@PathVariable Integer idProduct) {
        productDAO.delete(idProduct);
        return "redirect:/api/v1/product";
    }
}