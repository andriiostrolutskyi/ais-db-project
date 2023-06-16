package ua.naukma.aisdbproject.entities.employee.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.employee.dao.EmployeeDAO;
import ua.naukma.aisdbproject.entities.employee.model.Employee;

@Controller
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("employees", employeeDAO.getAll());
        return "employee/show";
    }

    @GetMapping("/{idEmployee}")
    public String getByID(@PathVariable("idEmployee") String idEmployee, Model model) {
        model.addAttribute("employee", employeeDAO.getByID(idEmployee));
        return "employee/show";
    }
    @GetMapping("/surname/{surnameEmployee}")
    public String getBySurname(@PathVariable("surnameEmployee") String surnameEmployee, Model model) {
        model.addAttribute("employee", employeeDAO.getBySurname(surnameEmployee));
        return "employee/show :: searchResults";
    }

    @GetMapping("/add-employee")
    public String goToAdd(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/add";
    }

    @PostMapping
    public String add(@ModelAttribute("employee") @Valid Employee employee,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employee/add";
        employeeDAO.add(employee);
        return "redirect:/api/v1/employee";
    }

    @GetMapping("/{idEmployee}/edit")
    public String edit(Model model,
                       @PathVariable("idEmployee") String idEmployee) {
        model.addAttribute("employee", employeeDAO.getByID(idEmployee));
        return "employee/edit";
    }

    @PatchMapping("/{idEmployee}")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult,
                         @PathVariable("idEmployee") String idEmployee) {
        if (bindingResult.hasErrors())
            return "employee/edit";
        employeeDAO.update(idEmployee, employee);
        return "redirect:/api/v1/employee";
    }

    @DeleteMapping("/{idEmployee}")
    public String delete(@PathVariable("idEmployee") String idEmployee) {
        employeeDAO.delete(idEmployee);
        return "redirect:/api/v1/employee";
    }
}
