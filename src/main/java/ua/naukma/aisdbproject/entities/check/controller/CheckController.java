package ua.naukma.aisdbproject.entities.check.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.check.dao.CheckDAO;
import ua.naukma.aisdbproject.entities.check.model.Check;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/check")
public class CheckController {
        private final CheckDAO checkDAO;

        @Autowired
        public CheckController(CheckDAO checkDAO) {
            this.checkDAO = checkDAO;
        }

        @GetMapping
        public String getAll(Model model) {
            model.addAttribute("checks", checkDAO.getAll());
            return "check/show";
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
        public String goToAdd(Model model) {
            model.addAttribute("check", new Check());
            return "check/add";
        }

        @PostMapping
        public String add(@ModelAttribute("check") @Valid Check check,
                                  BindingResult bindingResult) {
            if (bindingResult.hasErrors())
                return "check/add";
            checkDAO.add(check);
            return "redirect:/api/v1/check";
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
