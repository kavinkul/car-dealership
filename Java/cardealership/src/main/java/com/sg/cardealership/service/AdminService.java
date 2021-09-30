package com.sg.cardealership.service;

import com.sg.cardealership.dao.SpecialsDao;
import com.sg.cardealership.dao.UserDao;
import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Vehicle;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminService {
    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    SpecialsDao specialsDao;

    public List<Vehicle> getAllVehicles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Vehicle> searchVehicles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Make> getAllMake() {
        return vehicleDao.getAllMakes();
    }
}
