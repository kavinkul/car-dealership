package com.sg.cardealership.service;

import com.sg.cardealership.dao.ContactDao;
import com.sg.cardealership.dao.SpecialsDao;
import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Special;
import com.sg.cardealership.models.Vehicle;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeService {
    @Autowired
    VehicleDao vehicleDao;
    
    @Autowired
    SpecialsDao specialsDao;
    
    @Autowired
    ContactDao contactDao;
    
    public List<Vehicle> getAllFeaturedVehicles() {
        return vehicleDao.getAllVehicles()
                         .stream()
                         .filter(v -> v.isFeatured())
                         .collect(Collectors.toList());
    }
    
    public List<Special> getAllSpecials() {
        return specialsDao.getAllSpecials();
    }
}