package ua.naukma.aisdbproject.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/manager")
public class ManagerController {
    @GetMapping("/home")
    public String getManagerHome() {
        return "login/managerHome";
    }
}
