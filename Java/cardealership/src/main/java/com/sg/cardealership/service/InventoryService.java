package com.sg.cardealership.service;

import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryService {
    @Autowired
    VehicleDao vehicleDao;
    
    public List<Vehicle> getNewVehicles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Vehicle> getUsedVehicles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Vehicle getVehicle(int vin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
