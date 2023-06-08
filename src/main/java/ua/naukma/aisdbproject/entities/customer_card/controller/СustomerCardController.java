package ua.naukma.aisdbproject.entities.customer_card.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.customer_card.dao.CustomerCardDAO;
import ua.naukma.aisdbproject.entities.customer_card.model.CustomerCard;

@Controller
@RequestMapping("/api/v1/customer-card")
public class СustomerCardController {
    private final CustomerCardDAO customerCardDAO;

    @Autowired
    public СustomerCardController(CustomerCardDAO customerCardDAO) {
        this.customerCardDAO = customerCardDAO;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("customerCards", customerCardDAO.getAll());
        return "customerCard/show";
    }

    @GetMapping("/{cardNumber}")
    public String getByID(@PathVariable("cardNumber") String cardNumber, Model model) {
        model.addAttribute("customerCard", customerCardDAO.getByID(cardNumber));
        return "customerCard/show";
    }

    @GetMapping("/add-customer-card")
    public String goToAdd(Model model) {
        model.addAttribute("customerCard", new CustomerCard());
        return "customerCard/add";
    }

    @PostMapping
    public String add(@ModelAttribute("customerCard") @Valid CustomerCard customerCard,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "customerCard/add";
        customerCardDAO.add(customerCard);
        return "redirect:/api/v1/customer-card";
    }

    @GetMapping("/{cardNumber}/edit")
    public String edit(Model model,
                       @PathVariable("cardNumber") String cardNumber) {
        model.addAttribute("customerCard", customerCardDAO.getByID(cardNumber));
        return "customerCard/edit";
    }

    @PatchMapping("/{cardNumber}")
    public String update(@ModelAttribute("customerCard") @Valid CustomerCard customerCard,
                         BindingResult bindingResult,
                         @PathVariable("cardNumber") String cardNumber) {
        if (bindingResult.hasErrors())
            return "customerCard/edit";
        customerCardDAO.update(cardNumber, customerCard);
        return "redirect:/api/v1/customer-card";
    }

    @DeleteMapping("/{cardNumber}")
    public String delete(@PathVariable("cardNumber") String cardNumber) {
        customerCardDAO.delete(cardNumber);
        return "redirect:/api/v1/customer-card";
    }
}
