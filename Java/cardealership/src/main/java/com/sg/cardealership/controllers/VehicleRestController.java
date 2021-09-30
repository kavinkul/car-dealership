package com.sg.cardealership.controllers;

import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleRestController {
    @Autowired
    VehicleDao vehicleDao;
    
    @GetMapping
    public List<Vehicle> getAll() {
        return vehicleDao.getAllVehicles();
    }
    
}
