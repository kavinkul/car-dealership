package com.sg.cardealership.dao;

import com.sg.cardealership.models.Condition;
import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.MileageUnit;
import com.sg.cardealership.models.Model;
import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.Transmission;
import com.sg.cardealership.models.Trim;
import com.sg.cardealership.models.Type;
import com.sg.cardealership.models.User;
import com.sg.cardealership.models.Vehicle;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleDaoTest {
    @Autowired
    UserDao userDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    @BeforeEach
    public void setUp() {
        // Remove all vehicles
        for (Vehicle vehicle : vehicleDao.getAllVehicles()) {
            vehicleDao.removeVehicle(vehicle.getVIN());
        }
        
        // Remove all vehicle conditions
        for (Condition condition : vehicleDao.getAllVehicleConditions()) {
            vehicleDao.removeVehicleCondition(condition.getId());
        }
        
        // Remove all trims
        for (Trim trim : vehicleDao.getAllTrims()) {
            vehicleDao.removeTrim(trim.getId());
        }
        
        // Remove all models
        for (Model model : vehicleDao.getAllModels()) {
            vehicleDao.removeModel(model.getId());
        }
        
        // Remove all makes
        for (Make make : vehicleDao.getAllMakes()) {
            vehicleDao.removeMake(make.getId());
        }
        
        // Remove all users
        for (User user : userDao.getAllUsers()) {
            userDao.removeUser(user.getEmail());
        }
    }
    
    @Test
    public void testAddAndGetMake() {
        User user = new User("elizbeth@gmail.com",
                             "Elizbeth",
                             "Contrera",
                             "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd",
                             Role.ADMIN);
        
        userDao.addUser(user);
        
        Make make = new Make("Tesla", LocalDate.now(), user);
        
        vehicleDao.addMake(make);
        assertEquals(make, vehicleDao.getMake(make.getId()));
    }
    
    @Test
    public void testGetAllMakes() {
        User user = new User("elizbeth@gmail.com",
                             "Elizbeth",
                             "Contrera",
                             "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd",
                              Role.ADMIN);
        
        userDao.addUser(user);
        
        Make firstMake = new Make("Tesla", LocalDate.now(), user);
        Make secondMake = new Make("Ford", LocalDate.now(), user);
        
        vehicleDao.addMake(firstMake);
        vehicleDao.addMake(secondMake);
        
        List<Make> makes = vehicleDao.getAllMakes();
        
        assertEquals(2, makes.size());
        assertTrue(makes.contains(firstMake));
        assertTrue(makes.contains(secondMake));
    }
    
    @Test
    public void testRemoveMake() {
        User user = new User("elizbeth@gmail.com",
                             "Elizbeth",
                             "Contrera",
                             "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd",
                             Role.ADMIN);
        
        userDao.addUser(user);
        
        Make make = new Make("Tesla", LocalDate.now(), user);
        
        vehicleDao.addMake(make);
        assertEquals(make, vehicleDao.getMake(make.getId()));
        
        vehicleDao.removeMake(make.getId());
        assertNull(vehicleDao.getMake(make.getId()));
    }
    
    @Test
    public void testAddAndGetModel() {
        // Adding User
        User user = new User("elizbeth@gmail.com",
                             "Elizbeth",
                             "Contrera",
                             "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd",
                             Role.ADMIN);
        userDao.addUser(user);
        
        assertEquals(user, userDao.getUser(user.getEmail()));
        
        // Adding Make
        Make make = new Make("Hyundai", LocalDate.now(), user);
        vehicleDao.addMake(make);
        
        assertEquals(make, vehicleDao.getMake(make.getId()));
        
        // Adding Model
        Model model = new Model("Accent", 
                                List.of(2015),
                                LocalDate.now(), 
                                user, 
                                make);
        vehicleDao.addModel(model);
        
        assertEquals(model, vehicleDao.getModel(model.getId()));
    }
    
    public void testGetAllModels() {
        // Adding User
        User user = new User("elizbeth@gmail.com",
                             "Elizbeth",
                             "Contrera",
                             "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd",
                             Role.ADMIN);
        userDao.addUser(user);
        
        assertEquals(user, userDao.getUser(user.getEmail()));
        
        // Adding Make
        Make make = new Make("Hyundai", LocalDate.now(), user);
        vehicleDao.addMake(make);
        
        assertEquals(make, vehicleDao.getMake(make.getId()));
        
        // Adding Models
        Model firstModel = new Model("Accent", 
                                     List.of(2015),
                                     LocalDate.now(), 
                                     user, 
                                     make);
        vehicleDao.addModel(firstModel);
        
        Model secondModel = new Model("Azera", 
                                      List.of(2006, 2007, 2008),
                                      LocalDate.now(), 
                                      user, 
                                      make);
        vehicleDao.addModel(secondModel);
        
        List<Model> models = vehicleDao.getAllModels();
        
        assertEquals(2, models.size());
        assertTrue(models.contains(firstModel));
        assertTrue(models.contains(secondModel));
    }
    
    @Test
    public void testRemoveModel() {
        // Adding User
        User user = new User("elizbeth@gmail.com",
                             "Elizbeth",
                             "Contrera",
                             "559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd",
                             Role.ADMIN);
        userDao.addUser(user);
        
        assertEquals(user, userDao.getUser(user.getEmail()));
        
        // Adding Make
        Make make = new Make("Hyundai", LocalDate.now(), user);
        vehicleDao.addMake(make);
        
        assertEquals(make, vehicleDao.getMake(make.getId()));
        
        // Adding Model
        Model model = new Model("Accent", 
                                List.of(2015),
                                LocalDate.now(), 
                                user, 
                                make);
        vehicleDao.addModel(model);
        
        assertEquals(model, vehicleDao.getModel(model.getId()));
        
        // Remove Model
        vehicleDao.removeModel(model.getId());
        assertNull(vehicleDao.getModel(model.getId()));
    }
    
    @Test
    public void testAddAndGetTrim() {
        Trim trim = new Trim("trim", "black", "red", Transmission.AUTOMATIC);
        vehicleDao.addTrim(trim);
        
        assertEquals(trim, vehicleDao.getTrim(trim.getId()));
    }
    
    @Test
    public void testGetAllTrims() {
        // Adding Trims
        Trim firstTrim = new Trim("first trim", "black", "red", Transmission.AUTOMATIC);
        vehicleDao.addTrim(firstTrim);
        
        assertEquals(firstTrim, vehicleDao.getTrim(firstTrim.getId()));
        
        Trim secondTrim = new Trim("second trim", "black", "blue", Transmission.MANUAL);
        vehicleDao.addTrim(secondTrim);
        
        assertEquals(secondTrim, vehicleDao.getTrim(secondTrim.getId()));
        
        // Getting All Trims
        List<Trim> trims = vehicleDao.getAllTrims();
        
        assertEquals(2, trims.size());
        assertTrue(trims.contains(firstTrim));
        assertTrue(trims.contains(secondTrim));
    }
    
    @Test
    public void testRemoveTrim() {
        // Adding Trim
        Trim trim = new Trim("trim", "black", "red", Transmission.AUTOMATIC);
        vehicleDao.addTrim(trim);
        
        assertEquals(trim, vehicleDao.getTrim(trim.getId()));
        
        // Removing Trim
        vehicleDao.removeTrim(trim.getId());
        assertNull(vehicleDao.getTrim(trim.getId()));
    }
    
    @Test
    public void testAddAndGetVehicleCondition() {
        Condition condition = new Condition(1000, MileageUnit.KILOMETERS, Type.NEW);
        vehicleDao.addVehicleCondition(condition);
        
        assertEquals(condition, vehicleDao.getVehicleCondition(condition.getId()));        
    }
    
    @Test
    public void testGetAllVehicleConditions() {
        // Adding Vehicle Conditions
        Condition firstCondition = new Condition(1000, MileageUnit.KILOMETERS, Type.NEW);
        vehicleDao.addVehicleCondition(firstCondition);
        
        assertEquals(firstCondition, vehicleDao.getVehicleCondition(firstCondition.getId()));
        
        Condition secondCondition = new Condition(50000, MileageUnit.MILES, Type.USED);
        vehicleDao.addVehicleCondition(secondCondition);
        
        assertEquals(secondCondition, vehicleDao.getVehicleCondition(secondCondition.getId()));
        
        // Getting All Conditions
        List<Condition> conditions = vehicleDao.getAllVehicleConditions();
        
        assertEquals(2, conditions.size());
        assertTrue(conditions.contains(firstCondition));
        assertTrue(conditions.contains(secondCondition));
    }
    
    @Test
    public void testRemoveVehicleCondition() {
        // Adding Condition
        Condition condition = new Condition(1000, MileageUnit.KILOMETERS, Type.NEW);
        vehicleDao.addVehicleCondition(condition);
        
        assertEquals(condition, vehicleDao.getVehicleCondition(condition.getId())); 
        
        // Removing Condition
        vehicleDao.removeVehicleCondition(condition.getId());
        assertNull(vehicleDao.getVehicleCondition(condition.getId()));
    }
}