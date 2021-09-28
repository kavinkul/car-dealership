package com.sg.cardealership.dao;

import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MakeDaoTest {
    @Autowired
    MakeDao makeDao;
    
    @Autowired
    UserDao userDao;
    
    @BeforeEach
    public void setUp() {
        // Remove all make records
        for(Make make : makeDao.getAllMakes()) {
            makeDao.removeMake(make.getId());
        }
        
        // Remove all user records
        for(User user : userDao.getAllUsers()) {
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
        
        makeDao.addMake(make);
        assertEquals(make, makeDao.getMake(make.getId()));
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
        
        makeDao.addMake(firstMake);
        makeDao.addMake(secondMake);
        
        List<Make> makes = makeDao.getAllMakes();
        
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
        
        makeDao.addMake(make);
        assertEquals(make, makeDao.getMake(make.getId()));
        
        makeDao.removeMake(make.getId());
        assertNull(makeDao.getMake(make.getId()));
    }
}
