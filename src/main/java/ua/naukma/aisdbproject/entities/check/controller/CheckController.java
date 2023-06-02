package ua.naukma.aisdbproject.entities.check.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.check.dao.CheckDAO;
import ua.naukma.aisdbproject.entities.check.model.Check;

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
            model.addAttribute("check", checkDAO.getById(checkNumber));
            return "check/show";
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

        @GetMapping("/{checkNumber}/edit")
        public String edit(Model model,
                                   @PathVariable("checkNumber") String checkNumber) {
            model.addAttribute("check", checkDAO.getById(checkNumber));
            return "check/edit";
        }

        @PatchMapping("/{checkNumber}")
        public String update(@ModelAttribute("check") @Valid Check check,
                             BindingResult bindingResult,
                             @PathVariable("checkNumber") String checkNumber) {
            if (bindingResult.hasErrors())
                return "check/edit";
            checkDAO.update(checkNumber, check);
            return "redirect:/api/v1/check";
        }

        @DeleteMapping("/{checkNumber}")
        public String delete(@PathVariable("checkNumber") String checkNumber) {
            checkDAO.delete(checkNumber);
            return "redirect:/api/v1/check";
        }
}
