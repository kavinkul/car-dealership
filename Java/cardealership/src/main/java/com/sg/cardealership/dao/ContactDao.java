package com.sg.cardealership.dao;

import com.sg.cardealership.models.Contact;
import java.util.List;

public interface ContactDao {
    List<Contact> getAllContacts();
    
    Contact getContact(int id);

    Contact addContact(Contact user);

    void removeContact(int id);
}
