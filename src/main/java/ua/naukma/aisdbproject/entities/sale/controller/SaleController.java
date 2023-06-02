package ua.naukma.aisdbproject.entities.sale.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.sale.dao.SaleDAO;
import ua.naukma.aisdbproject.entities.sale.model.Sale;

@Controller
@RequestMapping("/api/v1/sale")
public class SaleController {
    private final SaleDAO saleDAO;

    @Autowired
    public SaleController(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("sales", saleDAO.getAll());
        return "sale/show";
    }

    @GetMapping("/{upc}/{checkNumber}")
    public String getByID(@PathVariable("upc") String upc,
                          @PathVariable("checkNumber") String checkNumber, Model model) {
        model.addAttribute("sale", saleDAO.getByID(upc, checkNumber));
        return "sale/show";
    }

    @GetMapping("/add-sale")
    public String goToAdd(Model model) {
        model.addAttribute("sale", new Sale());
        return "sale/add";
    }

    @PostMapping
    public String add(@ModelAttribute("sale") @Valid Sale sale,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "sale/add";
        saleDAO.add(sale);
        return "redirect:/api/v1/sale";
    }

    @GetMapping("/{upc}/{checkNumber}/edit")
    public String edit(Model model,
                       @PathVariable String upc,
                       @PathVariable String checkNumber) {
        model.addAttribute("sale", saleDAO.getByID(upc, checkNumber));
        return "sale/edit";
    }

    @PatchMapping("/{upc}/{checkNumber}")
    public String update(@ModelAttribute("sale") @Valid Sale sale,
                         BindingResult bindingResult,
                         @PathVariable("upc") String upc,
                         @PathVariable("checkNumber") String checkNumber) {
        if (bindingResult.hasErrors())
            return "sale/edit";
        saleDAO.update(upc, checkNumber, sale);
        return "redirect:/api/v1/sale";
    }

    @DeleteMapping("/{upc}/{checkNumber}")
    public String delete(@PathVariable("upc") String upc,
                         @PathVariable("checkNumber") String checkNumber) {
        saleDAO.delete(upc, checkNumber);
        return "redirect:/api/v1/sale";
    }
}

