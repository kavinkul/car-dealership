package com.sg.cardealership.dao;

import com.sg.cardealership.models.Contact;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactDaoTest {
    @Autowired
    ContactDao contactDao;
    
    @BeforeEach
    public void setUp() {
        // Remove all contact records
        for(Contact contact : contactDao.getAllContacts()) {
            contactDao.removeContact(contact.getId());
        }
    }
    
    @Test
    public void testAddAndGetContact() {
        Contact contact = new Contact("Elizbeth",
                                      "Contrera", 
                                      "17316898797", 
                                      "elizbeth@gmail.com", 
                                      "test message");
        
        contactDao.addContact(contact);
        assertEquals(contact, contactDao.getContact(contact.getId()));
    }
    
    @Test
    public void testGetAllContacts() {
        Contact firstContact = new Contact("Elizbeth",
                                           "Contrera", 
                                           "17316898797", 
                                           "elizbeth@gmail.com", 
                                           "test message");
        
        Contact secondContact = new Contact("Kerstin",
                                            "Gash", 
                                            "15066248797", 
                                            "kerstin@gmail.com", 
                                            "test message");
        
        contactDao.addContact(firstContact);
        contactDao.addContact(secondContact);
        
        List<Contact> contacts = contactDao.getAllContacts();
        
        assertEquals(2, contacts.size());
        assertTrue(contacts.contains(firstContact));
        assertTrue(contacts.contains(secondContact));
    }
    
    @Test
    public void testRemoveContact() {
        Contact contact = new Contact("Elizbeth",
                                      "Contrera", 
                                      "17316898797", 
                                      "elizbeth@gmail.com", 
                                      "test message");
        
        contactDao.addContact(contact);
        assertEquals(contact, contactDao.getContact(contact.getId()));
        
        contactDao.removeContact(contact.getId());
        assertNull(contactDao.getContact(contact.getId()));
    }    
}
