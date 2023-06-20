package ua.naukma.aisdbproject.manager.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/manager")
public class ManagerController {
    @GetMapping("/home")
    public String getManagerHome(HttpSession session, Model model){
        User user = (User) session.getAttribute("employee");
        if (user == null || (user.getUsrRole().equals("Cashier"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employee", user);
        return "login/managerHome";
    }
}
