/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.User;
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
 * @author agrah
 */
@SpringBootTest
public class UserDaoImplTest {
    
    @Autowired
    UserDao userDao;
    
    public UserDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<User> users = userDao.getAllUsers();
        for(User user: users){
            userDao.removeUser(user.getEmail());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllUsers method, of class UserDaoImpl.
     */
    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        User user2 = new User();
        user2.setEmail("otherTestEmail@email.com");
        user2.setFirstName("seconFirstName");
        user2.setLastName("secondLastName");
        user2.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c008z");
        user2.setRole(Role.SALES);
        user2 = userDao.addUser(user2);
        
        List<User> users = userDao.getAllUsers();
        
        assertEquals(2, users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }

    /**
     * Test of addUser and getUser method, of class UserDaoImpl.
     */
    @Test
    public void testAddAndGetUser() {
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUser(user.getEmail());
        
        assertEquals(user, fromDao);
    }

    /**
     * Test of removeUser method, of class UserDaoImpl.
     */
    @Test
    public void testRemoveUser() {
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUser(user.getEmail());
        
        assertEquals(user, fromDao);
        
        userDao.removeUser(user.getEmail());
        
        fromDao = userDao.getUser(user.getEmail());
        
        assertNull(fromDao);
    }

    /**
     * Test of editUser method, of class UserDaoImpl.
     */
    @Test
    public void testEditUser() {
        User user = new User();
        user.setEmail("testEmail@email.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setPasswordHash("3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d");
        user.setRole(Role.ADMIN);
        user = userDao.addUser(user);
        
        User fromDao = userDao.getUser(user.getEmail());
        assertEquals(user, fromDao);
        
        user.setFirstName("New FirstName");
        userDao.editUser(user);
        
        assertNotEquals(user, fromDao);
        
        fromDao = userDao.getUser(user.getEmail());
        assertEquals(user, fromDao);
    }
    
}
