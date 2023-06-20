package ua.naukma.aisdbproject.entities.check.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.check.dao.CheckDAO;
import ua.naukma.aisdbproject.entities.check.model.Check;
import ua.naukma.aisdbproject.entities.customer_card.dao.CustomerCardDAO;
import ua.naukma.aisdbproject.entities.employee.dao.EmployeeDAO;
import ua.naukma.aisdbproject.entities.product.dao.ProductDAO;
import ua.naukma.aisdbproject.entities.sale.model.Sale;
import ua.naukma.aisdbproject.entities.store_product.dao.StoreProductDAO;
import ua.naukma.aisdbproject.login.model.User;

import java.util.List;

@Controller
@RequestMapping("/api/v1/check")
public class CheckController {
    private final CheckDAO checkDAO;
    private final StoreProductDAO storeProductDAO;
    private final ProductDAO productDAO;
    private final CustomerCardDAO customerCardDAO;
    private final EmployeeDAO employeeDAO;

    @Autowired
    public CheckController(CheckDAO checkDAO, StoreProductDAO storeProductDAO, ProductDAO productDAO, CustomerCardDAO customerCardDAO, EmployeeDAO employeeDAO) {
        this.checkDAO = checkDAO;
        this.storeProductDAO = storeProductDAO;
        this.productDAO = productDAO;
        this.customerCardDAO = customerCardDAO;
        this.employeeDAO = employeeDAO;
    }

    @GetMapping
    public String getAll(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("checks", checkDAO.getAll());
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/show";
        else
            return "check/cashier/show";
    }

    @GetMapping("/{checkNumber}")
    public String getById(@PathVariable("checkNumber") String checkNumber, Model model) {
        model.addAttribute("checks", checkDAO.getById(checkNumber));
        return "check/show :: searchResults";
    }

    @GetMapping("/idEmployee/{idEmployee}")
    public String getByIdEmployee(@PathVariable("idEmployee") String idEmployee, Model model) {
        model.addAttribute("checks", checkDAO.getByIdEmployee(idEmployee));
        return "check/show :: searchResults";
    }

    @GetMapping("/showDetails/{checkNumber}")
    public String getCheckDetails(@PathVariable("checkNumber") String checkNumber, Model model) {
        model.addAttribute("sales", checkDAO.getSalesByCheckNumber(checkNumber));
        return "check/checkDetails";
    }

    @GetMapping("/time")
    public String getByTime(@RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate, Model model) {
        List<Check> checks = checkDAO.getByTime(startDate, endDate);
        model.addAttribute("checks", checks);
        return "check/show :: searchResults";
    }

    @GetMapping("/time/from")
    public String getByTimeFrom(@RequestParam("startDate") String startDate, Model model) {
        List<Check> checks = checkDAO.getByTimeFrom(startDate);
        model.addAttribute("checks", checks);
        return "check/show :: searchResults";
    }

    @GetMapping("/time/to")
    public String getByTimeTo(@RequestParam("endDate") String endDate, Model model) {
        List<Check> checks = checkDAO.getByTimeTo(endDate);
        model.addAttribute("checks", checks);
        return "check/show :: searchResults";
    }

    @GetMapping("/add-check")
    public String goToAdd(Model model, HttpSession httpSession) {
        model.addAttribute("storeProducts", storeProductDAO.getAll());
        model.addAttribute("products", productDAO.getAll());
        model.addAttribute("customerCards", customerCardDAO.getAll());
        model.addAttribute("sale", new Sale());
        Integer lastCheckNum = Integer.parseInt(checkDAO.getLastCheckName());
        String employeeID = employeeDAO.getByID(
                ((User) httpSession.getAttribute("employee")).getUsrUsername()).getIdEmployee();
        Check checkForForm = new Check("CH" + (++lastCheckNum), employeeID);
        checkDAO.add(checkForForm);
        model.addAttribute("check", checkForForm);
        return "check/add";
    }

    @PostMapping
    public String add(@ModelAttribute("check") @Valid Check check,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/api/v1/check/add-check";
        checkDAO.add(check);
        return "redirect:/api/v1/check";
    }

    @PatchMapping("/{checkNumber}")
    public void update(@ModelAttribute("check") Check check,
                       @PathVariable("checkNumber") String checkNumber) {
        checkDAO.update(checkNumber, check);
    }


    @DeleteMapping("/{checkNumber}")
    public String delete(@PathVariable("checkNumber") String checkNumber) {
        checkDAO.delete(checkNumber);
        return "redirect:/api/v1/check";
    }

    @GetMapping("/{checkNumber}/checkDetails")
    public String goToShow(Model model,
                           @PathVariable("checkNumber") String checkNumber) {
        model.addAttribute("checkNumber", checkDAO.getById(checkNumber));
        return "check/checkDetails";
    }
}
