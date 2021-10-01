/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.controllers;

import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Vehicle;
import com.sg.cardealership.service.InventoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author agrah
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryRestController {
    
    @Autowired
    InventoryService invService;
    
    @GetMapping("new")
    public List<Vehicle> getNew() {
        return invService.getNewVehicles();
    }
    
    @GetMapping("used")
    public List<Vehicle> getUsed() {
        return invService.getUsedVehicles();
    }
    
    @GetMapping("new/details/{1}")
    public Vehicle getVehicle(String vin){
        return invService.getVehicle(vin);
    }
}
