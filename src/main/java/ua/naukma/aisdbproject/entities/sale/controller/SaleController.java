package ua.naukma.aisdbproject.entities.sale.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.naukma.aisdbproject.entities.sale.dao.SaleDAO;
import ua.naukma.aisdbproject.entities.sale.model.Sale;

import java.util.List;

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

    @GetMapping("/{checkNumber}")
    public String getByID(@PathVariable("checkNumber") String checkNumber, Model model) {
        model.addAttribute("sales", saleDAO.getByCheck(checkNumber));
        return "check/cashier/add :: cart";
    }

    @GetMapping("/{upc}/{checkNumber}/edit")
    public String edit(Model model,
                       @PathVariable("upc") String upc,
                       @PathVariable("checkNumber") String checkNumber) {
        model.addAttribute("sale", saleDAO.getByID(upc, checkNumber));
        return "sale/edit";
    }

    @GetMapping("/getSum/{checkNumber}")
    @ResponseBody
    public ResponseEntity<Float> getSum(@PathVariable("checkNumber") String checkNumber){
        try {
            Float totalSum = saleDAO.getTotalSum(checkNumber);
            return ResponseEntity.ok(totalSum);
        } catch (Exception e) {
            // Handle the exception appropriately (e.g., return an error response)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/add-sale")
    @ResponseBody
    public ResponseEntity<String> add(@ModelAttribute("sale") Sale sale) {
        saleDAO.add(sale);
        return ResponseEntity.ok("Sale added successfully");
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
    @ResponseBody
    public ResponseEntity<String>  delete(@PathVariable("upc") String upc,
                         @PathVariable("checkNumber") String checkNumber, Model model) {
        saleDAO.delete(upc, checkNumber);
        return ResponseEntity.ok("Sale added successfully");
    }
}

