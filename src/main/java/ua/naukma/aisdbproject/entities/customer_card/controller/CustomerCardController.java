package ua.naukma.aisdbproject.entities.customer_card.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.customer_card.dao.CustomerCardDAO;
import ua.naukma.aisdbproject.entities.customer_card.model.CustomerCard;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/customer-card")
public class CustomerCardController {
    private final CustomerCardDAO customerCardDAO;

    @Autowired
    public CustomerCardController(CustomerCardDAO customerCardDAO) {
        this.customerCardDAO = customerCardDAO;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("customerCards", customerCardDAO.getAll());
        if (user.getUsrRole().equals("Manager")) {
            return "customerCard/manager/show";
        } else {
            return "customerCard/cashier/show";
        }
    }

    @GetMapping("/{cardNumber}")
    public String getByID(@PathVariable("cardNumber") String cardNumber, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("customerCard", customerCardDAO.getByID(cardNumber));
        if (user.getUsrRole().equals("Manager")) {
            return "customerCard/manager/show";
        } else {
            return "customerCard/cashier/show";
        }
    }
    @GetMapping("/validate/{cardNumber}")
    @ResponseBody
    public boolean getByIDValidation(@PathVariable("cardNumber") String cardNumber) {
        return (customerCardDAO.getByID(cardNumber)) != null;
    }

    @GetMapping("/add-customer-card")
    public String goToAdd(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("customerCard", new CustomerCard());
        return "customerCard/manager/add";
    }

    @PostMapping
    public String add(@ModelAttribute("customerCard") @Valid CustomerCard customerCard,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "customerCard/manager/add";
        customerCardDAO.add(customerCard);
        return "redirect:/api/v1/customer-card";
    }

    @GetMapping("/{cardNumber}/edit")
    public String edit(Model model,
                       @PathVariable("cardNumber") String cardNumber, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("customerCard", customerCardDAO.getByID(cardNumber));
        return "customerCard/manager/edit";
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

    @GetMapping("/customerSurname/{customerSurname}")
    public String getBySurname(@PathVariable("customerSurname") String customerSurname, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("customerCards", customerCardDAO.getBySurname(customerSurname));
        if (user.getUsrRole().equals("Manager")) {
            return "customerCard/manager/show :: searchResults";
        } else {
            return "customerCard/cashier/show :: searchResults";
        }

    }

    @GetMapping("/cardNumber/{cardNumber}")
    public String getByNumber(@PathVariable("cardNumber") String cardNumber, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("customerCards", customerCardDAO.getByNumber(cardNumber));
        if (user.getUsrRole().equals("Manager")) {
            return "customerCard/manager/show :: searchResults";
        } else {
            return "customerCard/cashier/show :: searchResults";
        }
    }

    @GetMapping("/percent/{percent}")
    public String getByPercent(@PathVariable("percent") Integer percent, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("customerCards", customerCardDAO.getByPercent(percent));
        if (user.getUsrRole().equals("Manager")) {
            return "customerCard/manager/show :: searchResults";
        } else {
            return "customerCard/cashier/show :: searchResults";
        }
    }
}
