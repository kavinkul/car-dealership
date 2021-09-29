/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Model;
import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.Sale;
import com.sg.cardealership.models.User;
import com.sg.cardealership.models.Vehicle;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Austin Graham 09/27/2021
 */
@SpringBootTest
public class UserDaoImplTest {
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    SalesDao salesDao;
    
    @Autowired
    VehicleDao vehicleDao;
    
    public UserDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
        
    }
    
    //before each test, get a list of all users in the database if any
    //loop through the list and delete each user fromthe database until empty.
    @BeforeEach
    public void setUp() {
        List<Sale> sales = salesDao.getAllSales();
        for(Sale sale: sales){
            salesDao.removeSale(sale);
        } 
        
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        for(Vehicle vehicle: vehicles){
            vehicleDao.removeVehicle(vehicle.getVIN());
        }
        
        List<Model> models = vehicleDao.getAllModels();
        for(Model model: models){
            vehicleDao.removeModel(model.getId());
        }
        
        List<Make> makes = vehicleDao.getAllMakes();
        for(Make make: makes){
            vehicleDao.removeMake(make.getId());
        }
        
        List<User> users = userDao.getAllUsers();
        for(User user: users){
            userDao.removeUser(user.getEmail());
        }
    }
    
    @AfterEach
    public void tearDown() {
        List<Sale> sales = salesDao.getAllSales();
        for(Sale sale: sales){
            salesDao.removeSale(sale);
        } 
        
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        for(Vehicle vehicle: vehicles){
            vehicleDao.removeVehicle(vehicle.getVIN());
        }
        
        List<Model> models = vehicleDao.getAllModels();
        for(Model model: models){
            vehicleDao.removeModel(model.getId());
        }
        
        List<Make> makes = vehicleDao.getAllMakes();
        for(Make make: makes){
            vehicleDao.removeMake(make.getId());
        }
        
        List<User> users = userDao.getAllUsers();
        for(User user: users){
            userDao.removeUser(user.getEmail());
        }
    }

    /**
     * Test of getAllUsers method, of class UserDaoImpl.
     */
    @Test
    public void testGetAllUsers() {
        //create a user object and add it to the test database
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        //create a second user object and add it to the test database
        User user2 = new User();
        user2.setEmail("otherTestEmail@email.com");
        user2.setFirstName("seconFirstName");
        user2.setLastName("secondLastName");
        user2.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c008z");
        user2.setRole(Role.SALES);
        user2 = userDao.addUser(user2);
        
        //get a list of all users from the test database for assertion testing
        List<User> users = userDao.getAllUsers();
        
        //assert there are only 2 objects in the list
        assertEquals(2, users.size());
        //assert the first user oject is present
        assertTrue(users.contains(user));
        //assert the second user is present
        assertTrue(users.contains(user2));
    }

    /**
     * Test of addUser and getUser method, of class UserDaoImpl.
     */
    @Test
    public void testAddAndGetUser() {
        //create a user object and add it to the test database
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        //get the user info back from the database
        User fromDao = userDao.getUser(user.getEmail());
        
        //assert that the user added and gotten from the database match
        assertEquals(user, fromDao);
    }

    /**
     * Test of removeUser method, of class UserDaoImpl.
     */
    @Test
    public void testRemoveUser() {
        //create a user object and add it to the test database
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        //get the user info back from the database
        User fromDao = userDao.getUser(user.getEmail());
        
        //assert that the user added and gotten from the database match
        assertEquals(user, fromDao);
        
        //remove the user via the users email
        userDao.removeUser(user.getEmail());
        
        //get the user info back from the database
        fromDao = userDao.getUser(user.getEmail());
        
        //assert that null was returned to verify the delete worked
        assertNull(fromDao);
    }

    /**
     * Test of editUser method, of class UserDaoImpl.
     */
    @Test
    public void testEditUser() {
        //create a user object and add it to the test database
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        //get the user info back from the database
        User fromDao = userDao.getUser(user.getEmail());
        //assert that the user added and gotten from the database match
        assertEquals(user, fromDao);
        
        //set a field of user with a new value
        user.setFirstName("New FirstName");
        //update the object in the database
        userDao.editUser(user);
        
        //assert that user with new field does not equal the object from the database
        assertNotEquals(user, fromDao);
        
        //get the updated user info from the database
        fromDao = userDao.getUser(user.getEmail());
        //assert that the updated user object and database info match
        assertEquals(user, fromDao);
    }
    
}
