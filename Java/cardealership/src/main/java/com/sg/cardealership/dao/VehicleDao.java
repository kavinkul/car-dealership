/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Vehicle;
import java.util.List;

/**
 *
 * @author kavin
 */
public interface VehicleDao {
    List<Vehicle> getAllVehicles();

    Vehicle getVehicle(String vin);

    void addVehicle(Vehicle vehicle);

    void removeVehicle(String vin);

    void editVehicle(Vehicle vehicle);
}
