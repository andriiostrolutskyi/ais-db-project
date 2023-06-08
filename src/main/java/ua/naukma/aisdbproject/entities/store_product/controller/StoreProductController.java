package ua.naukma.aisdbproject.entities.store_product.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.store_product.dao.StoreProductDAO;
import ua.naukma.aisdbproject.entities.store_product.model.StoreProduct;

@Controller
@RequestMapping("/api/v1/store-product")
public class StoreProductController {
    private final StoreProductDAO storeProductDAO;

    @Autowired
    public StoreProductController(StoreProductDAO storeProductDAO) {
        this.storeProductDAO = storeProductDAO;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("storeProducts", storeProductDAO.getAll());
        return "storeProduct/show";
    }

    @GetMapping("/{upc}")
    public String getByID(@PathVariable("upc") String upc, Model model) {
        model.addAttribute("storeProduct", storeProductDAO.getByID(upc));
        return "storeProduct/show";
    }

    @GetMapping("/add-store-product")
    public String goToAdd(Model model) {
        model.addAttribute("storeProduct", new StoreProduct());
        return "storeProduct/add";
    }

    @PostMapping
    public String add(@ModelAttribute("storeProduct") @Valid StoreProduct storeProduct,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "storeProduct/add";
        storeProductDAO.add(storeProduct);
        return "redirect:/api/v1/store-product";
    }

    @GetMapping("/{upc}/edit")
    public String edit(Model model,
                       @PathVariable("upc") String upc) {
        model.addAttribute("storeProduct", storeProductDAO.getByID(upc));
        return "storeProduct/edit";
    }

    @PatchMapping("/{upc}")
    public String update(@ModelAttribute("storeProduct") @Valid StoreProduct storeProduct,
                         BindingResult bindingResult,
                         @PathVariable("upc") String upc) {
        if (bindingResult.hasErrors())
            return "storeProduct/edit";
        storeProductDAO.update(upc, storeProduct);
        return "redirect:/api/v1/store-product";
    }

    @DeleteMapping("/{upc}")
    public String delete(@PathVariable("upc") String upc) {
        storeProductDAO.delete(upc);
        return "redirect:/api/v1/store-product";
    }
}
