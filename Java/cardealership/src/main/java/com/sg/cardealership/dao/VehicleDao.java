package com.sg.cardealership.dao;

import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Model;
import com.sg.cardealership.models.Vehicle;
import java.util.List;

public interface VehicleDao {
    // Vehicle
    List<Vehicle> getAllVehicles();

    Vehicle getVehicle(String vin);

    void addVehicle(Vehicle vehicle);

    void removeVehicle(String vin);

    void editVehicle(Vehicle vehicle);
    
    // Make
    List<Make> getAllMakes();

    Make getMake(int id);

    void addMake(Make make);

    void removeMake(int id);
    
    //  Model
    List<Model> getAllModels();

    Model getModel(int id);

    void addModel(Model model);

    void removeModel(int id);
}
