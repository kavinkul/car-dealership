package com.sg.cardealership.service;

import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Type;
import com.sg.cardealership.models.Vehicle;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryService {
    @Autowired
    VehicleDao vehicleDao;
    
    //get a list of only the vehicles marked as new condition
    public List<Vehicle> getNewVehicles() {
        return vehicleDao.getAllVehicles()
                .stream()
                .filter(v -> Type.NEW == v.getVehicleCondition().getType())
                .collect(Collectors.toList());
    }
    
    public List<Vehicle> getUsedVehicles() {
        return vehicleDao.getAllVehicles()
                .stream()
                .filter(v -> Type.USED == v.getVehicleCondition().getType())
                .collect(Collectors.toList());
    }
    
    public Vehicle getVehicle(String vin) {
        return vehicleDao.getVehicle(vin);
    }
}
