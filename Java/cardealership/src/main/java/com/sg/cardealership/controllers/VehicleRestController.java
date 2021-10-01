package com.sg.cardealership.controllers;

import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Condition;
import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.MileageUnit;
import com.sg.cardealership.models.Model;
import com.sg.cardealership.models.Transmission;
import com.sg.cardealership.models.Trim;
import com.sg.cardealership.models.Type;
import com.sg.cardealership.models.Vehicle;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    
    @PostMapping
    public ResponseEntity create(@RequestBody Map<String, String> vehicle) {
        Model model = vehicleDao.getModel(Integer.parseInt(vehicle.get("model")));
        Make make = vehicleDao.getMake(Integer.parseInt(vehicle.get("make")));
        
        Condition condition = new Condition(Integer.parseInt(vehicle.get("mileage")),
                                            MileageUnit.MILES,
                                            vehicle.get("type").equalsIgnoreCase("NEW")
                                                    ? Type.NEW 
                                                    : Type.USED);
        Trim trim = new Trim("trim", 
                             vehicle.get("interior"), 
                             vehicle.get("exterior"),
                             vehicle.get("trans").equalsIgnoreCase("AUTOMATIC") 
                                     ? Transmission.AUTOMATIC 
                                     : Transmission.MANUAL);
        
        
        Vehicle addVehicle = new Vehicle("123456789012AS567",
                                        model,
                                        condition,
                                        vehicle.get("bodyStyle"),
                                        null,
                                        vehicle.get("description"),
                                        trim,
                                        new BigDecimal(vehicle.get("msrp")),
                                        new BigDecimal(vehicle.get("salePrice")),
                                        true);
        vehicleDao.addVehicle(addVehicle);
        return new ResponseEntity(HttpStatus.ACCEPTED); //test
    }
}
