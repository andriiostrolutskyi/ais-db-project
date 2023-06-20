package ua.naukma.aisdbproject.cashier.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/cashier")
public class CashierController {
    @GetMapping("/home")
    public String getCashierHome(HttpSession session, Model model){
        User user = (User) session.getAttribute("employee");
        if (user == null || (user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employee", user);
        return "login/cashierHome";
    }

}
