package com.sg.cardealership.controllers;

import com.sg.cardealership.models.Vehicle;
import com.sg.cardealership.service.InventoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService invService;

    @GetMapping("/new")
    public String displayNewVehicles(Model model) {
        /*
       List<Vehicle> newVehicles = invService.getNewVehicles();
        model.addAttribute("new", newVehicles);
        */
        return "new"; 
    }
    
    @GetMapping("/used")
    public String displayUsedVehicles(Model model) {
       return "used"; 
    }
    
    @GetMapping("/details")
    public String displayVehicleDetails(Model model) {
       return "details"; 
    }
}
