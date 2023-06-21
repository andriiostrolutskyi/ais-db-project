package ua.naukma.aisdbproject.cashier.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.naukma.aisdbproject.entities.employee.dao.EmployeeDAO;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/cashier")
public class CashierController {

    private final EmployeeDAO employeeDAO;
    @Autowired
    public CashierController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/home")
    public String getCashierHome(HttpSession session, Model model){
        User user = (User) session.getAttribute("employee");
        if (user == null || (user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employee", user);
        model.addAttribute("cashier", employeeDAO.getByID(user.getUsrUsername()));
        return "login/cashierHome";
    }

}
