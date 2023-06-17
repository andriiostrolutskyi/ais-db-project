package ua.naukma.aisdbproject.cashier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/cashier")
public class CashierController {
    @GetMapping("/home")
    public String getCashierHome(){
        return "login/cashierHome";
    }
}
