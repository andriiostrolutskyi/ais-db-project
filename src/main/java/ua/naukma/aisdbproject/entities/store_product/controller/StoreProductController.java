package ua.naukma.aisdbproject.entities.store_product.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.product.dao.ProductDAO;
import ua.naukma.aisdbproject.entities.store_product.dao.StoreProductDAO;
import ua.naukma.aisdbproject.entities.store_product.model.StoreProduct;
import ua.naukma.aisdbproject.entities.store_product.model.StoreProductSearch;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/store-product")
public class StoreProductController {
    private final StoreProductDAO storeProductDAO;
    private final ProductDAO productDAO;

    @Autowired
    public StoreProductController(StoreProductDAO storeProductDAO, ProductDAO productDAO) {
        this.storeProductDAO = storeProductDAO;
        this.productDAO = productDAO;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getAll());
        model.addAttribute("products", storeProductDAO.getProductNames());
        if (user.getUsrRole().equals("Manager")) {
            return "storeProduct/manager/show";
        } else {
            return "storeProduct/cashier/show";
        }
    }

    @GetMapping("/popular")
    public String getPopular(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getPopular());
        model.addAttribute("products", storeProductDAO.getProductNames());
        return "storeProduct/manager/popular";

    }

    @GetMapping("/{upc}")
    @ResponseBody
    public StoreProductSearch getByID(@PathVariable("upc") String upc) {
        return storeProductDAO.getProductByUPC(upc);
    }

    @GetMapping("/validate")
    @ResponseBody
    public boolean getValidationByID(@RequestParam("upc") String upc,
                                     @RequestParam("upcProm") String upcProm) {
        StoreProduct storeProduct = storeProductDAO.getByID(upc);
        int count = storeProductDAO.getNumberByID(upcProm);
        return (storeProduct != null) || (count > 2);
    }

    @GetMapping("/getPromotional/sortByName")
    public String getPromotionalByName(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getPromotionalByName());
        model.addAttribute("products", storeProductDAO.getProductNames());
        if (user.getUsrRole().equals("Manager")) {
            return "storeProduct/manager/show :: searchResults";
        } else {
            return "storeProduct/cashier/show :: searchResults";
        }
    }

    @GetMapping("/getPromotional/sortByQuantity")
    public String getPromotionalByQuantity(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getPromotionalByQuantity());
        model.addAttribute("products", storeProductDAO.getProductNames());
        if (user.getUsrRole().equals("Manager")) {
            return "storeProduct/manager/show :: searchResults";
        } else {
            return "storeProduct/cashier/show :: searchResults";
        }
    }

    @GetMapping("/getNotPromotional/sortByName")
    public String getNotPromotionalByName(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getNotPromotionalByName());
        model.addAttribute("products", storeProductDAO.getProductNames());
        if (user.getUsrRole().equals("Manager")) {
            return "storeProduct/manager/show :: searchResults";
        } else {
            return "storeProduct/cashier/show :: searchResults";
        }
    }

    @GetMapping("/getNotPromotional/sortByQuantity")
    public String getNotPromotionalByQuantity(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getNotPromotionalByQuantity());
        model.addAttribute("products", storeProductDAO.getProductNames());
        if (user.getUsrRole().equals("Manager")) {
            return "storeProduct/manager/show :: searchResults";
        } else {
            return "storeProduct/cashier/show :: searchResults";
        }
    }

    @GetMapping("/noFilters")
    public String getProductsWithoutFilters(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getAll());
        model.addAttribute("products", storeProductDAO.getProductNames());
        if (user.getUsrRole().equals("Manager")) {
            return "storeProduct/manager/show :: searchResults";
        } else {
            return "storeProduct/cashier/show :: searchResults";
        }
    }

    @GetMapping("/add-store-product")
    public String goToAdd(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProduct", new StoreProduct());
        model.addAttribute("storeProducts", storeProductDAO.getAll());
        model.addAttribute("productIDs", productDAO.getAll());
        return "storeProduct/manager/add";
    }

    @PostMapping
    public String add(@ModelAttribute("storeProduct") @Valid StoreProduct storeProduct,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/api/v1/store-product/add-store-product";
        storeProductDAO.add(storeProduct);
        return "redirect:/api/v1/store-product";
    }

    @GetMapping("/{upc}/edit")
    public String edit(Model model,
                       @PathVariable("upc") String upc, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProduct", storeProductDAO.getByID(upc));
        model.addAttribute("storeProducts", storeProductDAO.getAll());
        model.addAttribute("productIDs", productDAO.getAll());
        return "storeProduct/manager/edit";
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
