package ua.naukma.aisdbproject.entities.employee.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.employee.dao.EmployeeDAO;
import ua.naukma.aisdbproject.entities.employee.model.Employee;
import ua.naukma.aisdbproject.login.model.User;

@Controller
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employees", employeeDAO.getAll());
        return "employee/manager/show";
    }

    @GetMapping("/manager/special")
    public String getSpecial(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employees", employeeDAO.getSpecial());
        return "employee/manager/special";
    }

    @GetMapping("/{idEmployee}")
    public String getByID(@PathVariable("idEmployee") String idEmployee, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employees", employeeDAO.getByID(idEmployee));
        return "employee/manager/show :: searchResults";
    }

    @GetMapping("/validate/{idEmployee}")
    @ResponseBody
    public boolean getByID(@PathVariable("idEmployee") String idEmployee) {
        return (employeeDAO.getByID(idEmployee)) != null;
    }

    @GetMapping("/surname/{surnameEmployee}")
    public String getBySurname(@PathVariable("surnameEmployee") String surnameEmployee, Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employees", employeeDAO.getBySurname(surnameEmployee));
        return "employee/manager/show :: searchResults";
    }

    @GetMapping("/cashier")
    public String getCashiers(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employees", employeeDAO.getCashiers());
        return "employee/manager/show :: searchResults";
    }


    @GetMapping("/add-employee")
    public String goToAdd(Model model, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employee", new Employee());
        return "employee/manager/add";
    }

    @PostMapping
    public String add(@ModelAttribute("employee") @Valid Employee employee,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employee/manager/add";
        employeeDAO.add(employee);
        return "redirect:/api/v1/employee";
    }

    @GetMapping("/{idEmployee}/edit")
    public String edit(Model model,
                       @PathVariable("idEmployee") String idEmployee, HttpSession session) {
        User user = (User) session.getAttribute("employee");
        if (user == null || (!user.getUsrRole().equals("Manager"))) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("employee", employeeDAO.getByID(idEmployee));
        return "employee/manager/edit";
    }

    @PatchMapping("/{idEmployee}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult,
                         @PathVariable("idEmployee") String idEmployee) {
        if (bindingResult.hasErrors())
            return "employee/manager/edit";
        employeeDAO.update(idEmployee, employee);
        return "redirect:/api/v1/employee";
    }

    @DeleteMapping("/{idEmployee}")
    public String delete(@PathVariable("idEmployee") String idEmployee) {
        employeeDAO.delete(idEmployee);
        return "redirect:/api/v1/employee";
    }
}
