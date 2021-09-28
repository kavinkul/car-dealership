/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Special;
import java.util.ArrayList;
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
public class SpecialsDaoImplTest {
    
    @Autowired
    SpecialsDao specialDao;
    
    public SpecialsDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Special> specials = specialDao.getAllSpecials();
        for(Special special : specials) {
            specialDao.removeSpecial(special.getId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    

    /**
     * Test of getAllSpecials method, of class SpecialsDaoImpl.
     */
    @Test
    public void testGetAllSpecials() {
        Special special = new Special("Test Title", "Test Description 1");
        special = specialDao.addSpecial(special);
        
        Special special2 = new Special("Second Test Title", "A second test description.");
        special2 = specialDao.addSpecial(special2);
        
        List<Special> specials = specialDao.getAllSpecials();
        
        assertEquals(2, specials.size());
        assertTrue(specials.contains(special));
        assertTrue(specials.contains(special2));
    }

    /**
     * Test of addSpecial and getSpecial method, of class SpecialsDaoImpl.
     */
    @Test
    public void testAddAndGetSpecial() {
        Special special = new Special("Fall-Back RollBack", "Prices are rolled back "
                + "for fall. Come in today to recieve xx% of purchase.");
        special = specialDao.addSpecial(special);
        
        Special fromDao = specialDao.getSpecial(special.getId());
        assertEquals(special, fromDao);
    }

    /**
     * Test of removeSpecial method, of class SpecialsDaoImpl.
     */
    
    @Test
    public void testRemoveSpecial() {
        Special special = new Special("Fall-Back RollBack", "Prices are rolled back "
                + "for fall. Come in today to recieve xx% of purchase.");
        special = specialDao.addSpecial(special);
        
        Special fromDao = specialDao.getSpecial(special.getId());
        assertEquals(special, fromDao);
        
        specialDao.removeSpecial(special.getId());
        
        fromDao = specialDao.getSpecial(special.getId());
        assertNull(fromDao);
    }

    /**
     * Test of editSpecial method, of class SpecialsDaoImpl.
     */
    /* edit out due to no edit function in wireframe. -- A.Graham 09/27 10:45pm
    @Test
    public void testEditSpecial() {
    }
    */
    
}
