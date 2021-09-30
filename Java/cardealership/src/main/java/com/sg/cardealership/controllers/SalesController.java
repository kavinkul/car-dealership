package com.sg.cardealership.controllers;

import com.sg.cardealership.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    SalesService salesService;
    
    @GetMapping("/index")
    public String displaySales(Model model) {
       return "";
    }
    
    @GetMapping("/purchaseVehicle/{1}")
    public String displayPurchaseVehicle(Model model) {
       return "";
    }
}
