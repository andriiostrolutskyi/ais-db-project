package ua.naukma.aisdbproject.login.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.naukma.aisdbproject.login.dao.HomeDAO;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/login")
public class HomeController {
    private final HomeDAO homeDAO;


    public HomeController(HomeDAO homeDAO) {
        this.homeDAO = homeDAO;
    }

    @GetMapping
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login/loginPage";
    }

    @PostMapping
    public String signIn(User user, Model model, HttpSession session) {
        if (homeDAO.getByCredentials(user) != null) {
            session.setAttribute("employee", user);
            if (homeDAO.getByCredentials(user).getUsrRole().equals("Manager"))
                return "redirect:manager/home";
            else
                return "redirect:cashier/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login/loginPage";
        }
    }
}