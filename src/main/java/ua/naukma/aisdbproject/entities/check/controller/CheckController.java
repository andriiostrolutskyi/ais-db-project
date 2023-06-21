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
import ua.naukma.aisdbproject.entities.sale.dao.SaleDAO;
import ua.naukma.aisdbproject.entities.sale.model.Sale;
import ua.naukma.aisdbproject.entities.store_product.dao.StoreProductDAO;
import ua.naukma.aisdbproject.login.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/check")
public class CheckController {
    private final CheckDAO checkDAO;
    private final StoreProductDAO storeProductDAO;
    private final ProductDAO productDAO;
    private final CustomerCardDAO customerCardDAO;
    private final EmployeeDAO employeeDAO;
    private final SaleDAO saleDAO;

    @Autowired
    public CheckController(CheckDAO checkDAO, StoreProductDAO storeProductDAO, ProductDAO productDAO, CustomerCardDAO customerCardDAO, EmployeeDAO employeeDAO, SaleDAO saleDAO) {
        this.checkDAO = checkDAO;
        this.storeProductDAO = storeProductDAO;
        this.productDAO = productDAO;
        this.customerCardDAO = customerCardDAO;
        this.employeeDAO = employeeDAO;
        this.saleDAO = saleDAO;
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
    public String getById(@PathVariable("checkNumber") String checkNumber, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("checks", checkDAO.getById(checkNumber));
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/show :: searchResults";
        else
            return "check/cashier/show :: searchResults";

    }

    @GetMapping("/idEmployee/{idEmployee}")
    public String getByIdEmployee(@PathVariable("idEmployee") String idEmployee, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("checks", checkDAO.getByIdEmployee(idEmployee));
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/show :: searchResults";
        else
            return "check/cashier/show :: searchResults";
    }

    @GetMapping("/showDetails/{checkNumber}")
    public String getCheckDetails(@PathVariable("checkNumber") String checkNumber, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("sales", checkDAO.getSalesByCheckNumber(checkNumber));
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/checkDetails";
        else
            return "check/cashier/checkDetails";
    }

    @GetMapping("/time")
    public String getByTime(@RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        List<Check> checks = checkDAO.getByTime(startDate, endDate);
        model.addAttribute("checks", checks);
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/show :: searchResults";
        else
            return "check/cashier/show :: searchResults";
    }

    @GetMapping("/time/from")
    public String getByTimeFrom(@RequestParam("startDate") String startDate, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        List<Check> checks = checkDAO.getByTimeFrom(startDate);
        model.addAttribute("checks", checks);
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/show :: searchResults";
        else
            return "check/cashier/show :: searchResults";
    }

    @GetMapping("/time/to")
    public String getByTimeTo(@RequestParam("endDate") String endDate, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        List<Check> checks = checkDAO.getByTimeTo(endDate);
        model.addAttribute("checks", checks);
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/show :: searchResults";
        else
            return "check/cashier/show :: searchResults";
    }

    @GetMapping("/add-check")
    public String goToAdd(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null || user.getUsrRole().equals("Manager")) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("storeProducts", storeProductDAO.getAll());
        model.addAttribute("products", productDAO.getAll());
        model.addAttribute("customerCards", customerCardDAO.getAll());
        model.addAttribute("sale", new Sale());
        Integer lastCheckNum = Integer.parseInt(checkDAO.getLastCheckName());
        String employeeID = employeeDAO.getByID(
                ((User) httpSession.getAttribute("employee")).getUsrUsername()).getIdEmployee();
        Check checkForForm = new Check("CH" + (++lastCheckNum), employeeID);
        checkDAO.add(checkForForm);
        model.addAttribute("sales", saleDAO.getByCheck(checkForForm.getCheckNumber()));
        model.addAttribute("check", checkForForm);
        return "check/cashier/add";
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
    public String update(@RequestBody Map<String, Object> requestBody,
                       @PathVariable("checkNumber") String checkNumber) {
        String idEmployee = (String) requestBody.get("idEmployee");
        String checkNumberBody = (String) requestBody.get("checkNumber");
        String cardNumber = (String) requestBody.get("cardNumber");
        Float totalSum =  ((Double) requestBody.get("totalSum")).floatValue();

        Check check = new Check(checkNumberBody, idEmployee, cardNumber, totalSum);
        checkDAO.update(checkNumber, check);
        return "redirect:/api/v1/check";
    }


    @DeleteMapping("/{checkNumber}")
    public String delete(@PathVariable("checkNumber") String checkNumber) {
        checkDAO.delete(checkNumber);
        return "redirect:/api/v1/check";
    }

    @GetMapping("/{checkNumber}/checkDetails")
    public String goToShow(Model model,
                           @PathVariable("checkNumber") String checkNumber, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("employee");
        if (user == null) {
            return "redirect:/api/v1/login";
        }
        model.addAttribute("checkNumber", checkDAO.getById(checkNumber));
        if (user.getUsrRole().equals("Manager"))
            return "check/manager/checkDetails";
        else
            return "check/cashier/checkDetails";
    }
}
