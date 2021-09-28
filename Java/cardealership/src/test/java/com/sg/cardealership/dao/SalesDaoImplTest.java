/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Condition;
import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.MileageUnit;
import com.sg.cardealership.models.Model;
import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.Sale;
import com.sg.cardealership.models.Transmission;
import com.sg.cardealership.models.Trim;
import com.sg.cardealership.models.Type;
import com.sg.cardealership.models.User;
import com.sg.cardealership.models.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author kavin
 */
@SpringBootTest
public class SalesDaoImplTest {

    @Autowired
    UserDao userDao;

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    SalesDao salesDao;

    @BeforeEach
    public void setUp() {
        List<Sale> allSales = salesDao.getAllSales();
        for (Sale sale : allSales)
            salesDao.removeSale(sale);
        List<Vehicle> allVehicles = vehicleDao.getAllVehicles();
        for (Vehicle vehicle : allVehicles)
            vehicleDao.removeVehicle(vehicle.getVIN());
        List<Model> allModels = vehicleDao.getAllModels();
        for (Model model : allModels)
            vehicleDao.removeModel(model.getId());
        List<Make> allMakes = vehicleDao.getAllMakes();
        for (Make make : allMakes)
            vehicleDao.removeMake(make.getId());
        List<Trim> allTrims = vehicleDao.getAllTrims();
        for (Trim trim : allTrims)
            vehicleDao.removeTrim(trim.getId());
        List<Condition> allVehicleConditions = vehicleDao.getAllVehicleConditions();
        for (Condition condition : allVehicleConditions)
            vehicleDao.removeVehicleCondition(condition.getId());
        List<User> allUsers = userDao.getAllUsers();
        for (User user : allUsers)
            userDao.removeUser(user.getEmail());
    }

    /**
     * Test of getAllSales method, of class SalesDao.
     */
    @Test
    public void testAddGetAllSales() {
        User admin = new User("gemail@googie.com", "Johny", "Bravo", "4887d8d68c02152d4c2532b83886b4cd56e20aab018fd912324ff3c6b1394851", Role.ADMIN);

        userDao.addUser(admin);

        Make firstMake = new Make("first", LocalDate.now(), admin.getEmail());
        Make secondMake = new Make("second", LocalDate.now(), admin.getEmail());

        vehicleDao.addMake(firstMake);
        vehicleDao.addMake(secondMake);

        Model firstModel = new Model("first model", 2012, LocalDate.now(), admin.getEmail(), firstMake);

        vehicleDao.addModel(firstModel);

        Model secondModel = new Model("second model", 2016, LocalDate.now(), admin.getEmail(), secondMake);

        vehicleDao.addModel(secondModel);

        Condition firstCondition = new Condition(10000, MileageUnit.KILOMETERS, Type.USED);
        Condition secondCondition = new Condition(100, MileageUnit.MILES, Type.NEW);

        Trim firstTrim = new Trim("first trim", "red", "black", Transmission.AUTOMATIC);
        Trim secondTrim = new Trim("second trim", "black", "white", Transmission.MANUAL);

        User firstUser = new User("email@email.com", "John", "Doe", "3384d8d68c02152d4c2532b83886b4cd56e20aab018fd912324ff3c6b1394851", Role.SALES);
        User secondUser = new User("real@fake.email.com", "Fake", "Person", "3384d7658e455ad4c2532b83886b74cd56e20aab018fd912324ff3c6b1394851", Role.SALES);

        userDao.addUser(firstUser);
        userDao.addUser(secondUser);

        Vehicle firstVehicle = new Vehicle("1234567890abcdefg", firstModel, firstCondition, "Sedan", null, "Nothing", firstTrim, new BigDecimal("25430.65"), new BigDecimal("30000.00"), true);
        Vehicle secondVehicle = new Vehicle("7777567890abcdefg", secondModel, secondCondition, "Not Sedan", null, "Not Nothing", secondTrim, new BigDecimal("10000.65"), new BigDecimal("20000.50"), false);

        vehicleDao.addVehicle(firstVehicle);
        vehicleDao.addVehicle(secondVehicle);

        salesDao.addSale(firstUser, firstVehicle);

        List<Sale> allSales = salesDao.getAllSales();

        assertNotNull(allSales);
        assertEquals(1, allSales.size());
        assertTrue(allSales.contains(salesDao.getSale(firstUser)));

        salesDao.addSale(secondUser, secondVehicle);

        allSales = salesDao.getAllSales();

        assertNotNull(allSales);
        assertEquals(2, allSales.size());
        assertTrue(allSales.contains(salesDao.getSale(firstUser)));
        assertTrue(allSales.contains(salesDao.getSale(secondUser)));

    }

    /**
     * Test of getSale method, of class SalesDao.
     */
    @Test
    public void testAddGetSale() {
        User admin = new User("gemail@googie.com", "Johny", "Bravo", "4887d8d68c02152d4c2532b83886b4cd56e20aab018fd912324ff3c6b1394851", Role.ADMIN);

        userDao.addUser(admin);

        Make firstMake = new Make("first", LocalDate.now(), admin.getEmail());

        vehicleDao.addMake(firstMake);

        Model firstModel = new Model("first model", 2012, LocalDate.now(), admin.getEmail(), firstMake);

        vehicleDao.addModel(firstModel);

        Condition firstCondition = new Condition(10000, MileageUnit.KILOMETERS, Type.USED);

        Trim firstTrim = new Trim("first trim", "red", "black", Transmission.AUTOMATIC);

        User firstUser = new User("email@email.com", "John", "Doe", "3384d8d68c02152d4c2532b83886b4cd56e20aab018fd912324ff3c6b1394851", Role.SALES);

        userDao.addUser(firstUser);

        Vehicle firstVehicle = new Vehicle("1234567890abcdefg", firstModel, firstCondition, "Sedan", null, "Nothing", firstTrim, new BigDecimal("25430.65"), new BigDecimal("30000.00"), true);

        vehicleDao.addVehicle(firstVehicle);

        salesDao.addSale(firstUser, firstVehicle);

        Sale retrievedSale = salesDao.getSale(firstUser);

        assertNotNull(retrievedSale);

        assertEquals(firstUser, retrievedSale.getUser());

        List<Vehicle> soldVehicles = retrievedSale.getVehicles();

        assertNotNull(soldVehicles);
        assertEquals(1, soldVehicles.size());
        assertTrue(soldVehicles.contains(firstVehicle));
    }

    @Test
    public void testAddRemoveSale() {
        User admin = new User("gemail@googie.com", "Johny", "Bravo", "4887d8d68c02152d4c2532b83886b4cd56e20aab018fd912324ff3c6b1394851", Role.ADMIN);

        userDao.addUser(admin);

        Make firstMake = new Make("first", LocalDate.now(), admin.getEmail());
        Make secondMake = new Make("second", LocalDate.now(), admin.getEmail());

        vehicleDao.addMake(firstMake);
        vehicleDao.addMake(secondMake);

        Model firstModel = new Model("first model", 2012, LocalDate.now(), admin.getEmail(), firstMake);

        vehicleDao.addModel(firstModel);

        Model secondModel = new Model("second model", 2016, LocalDate.now(), admin.getEmail(), secondMake);

        vehicleDao.addModel(secondModel);

        Condition firstCondition = new Condition(10000, MileageUnit.KILOMETERS, Type.USED);
        Condition secondCondition = new Condition(100, MileageUnit.MILES, Type.NEW);

        Trim firstTrim = new Trim("first trim", "red", "black", Transmission.AUTOMATIC);
        Trim secondTrim = new Trim("second trim", "black", "white", Transmission.MANUAL);

        User firstUser = new User("email@email.com", "John", "Doe", "3384d8d68c02152d4c2532b83886b4cd56e20aab018fd912324ff3c6b1394851", Role.SALES);
        User secondUser = new User("real@fake.email.com", "Fake", "Person", "3384d7658e455ad4c2532b83886b74cd56e20aab018fd912324ff3c6b1394851", Role.SALES);

        userDao.addUser(firstUser);
        userDao.addUser(secondUser);

        Vehicle firstVehicle = new Vehicle("1234567890abcdefg", firstModel, firstCondition, "Sedan", null, "Nothing", firstTrim, new BigDecimal("25430.65"), new BigDecimal("30000.00"), true);
        Vehicle secondVehicle = new Vehicle("7777567890abcdefg", secondModel, secondCondition, "Not Sedan", null, "Not Nothing", secondTrim, new BigDecimal("10000.65"), new BigDecimal("20000.50"), false);

        salesDao.addSale(firstUser, firstVehicle);

        Sale firstAddedSale = salesDao.getSale(firstUser);

        assertNotNull(firstAddedSale);

        salesDao.addSale(secondUser, secondVehicle);

        Sale secondAddedSale = salesDao.getSale(firstUser);

        assertNotNull(secondAddedSale);

        List<Sale> allSales = salesDao.getAllSales();
        assertNotNull(allSales);
        assertEquals(2, allSales.size());

        assertTrue(allSales.contains(firstAddedSale));
        assertTrue(allSales.contains(secondAddedSale));

        allSales = salesDao.getAllSales();

        assertNotNull(allSales);
        assertEquals(1, allSales.size());
        assertFalse(allSales.contains(firstAddedSale));
        assertTrue(allSales.contains(secondAddedSale));
    }

}
