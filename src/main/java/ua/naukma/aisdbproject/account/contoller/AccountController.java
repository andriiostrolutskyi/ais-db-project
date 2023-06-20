package ua.naukma.aisdbproject.account.contoller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.employee.dao.EmployeeDAO;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/account")
public class AccountController {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public AccountController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping
    public String getInfo(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("account", employeeDAO.getByID(user.getUsrUsername()));
        if (user.getUsrRole().equals("Manager")) {
            return "account/manager/show";
        } else {
            return "account/cashier/show";
        }
    }
}
