package com.sg.cardealership.dao;

import com.sg.cardealership.models.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContactDaoImpl implements ContactDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Contact> getAllContacts() {
        final String SELECT_ALL_CONTACTS = "SELECT * FROM Contacts";
        return jdbc.query(SELECT_ALL_CONTACTS, new ContactMapper());
    }

    @Override
    public Contact getContact(int id) {
        try {
            final String SELECT_CONTACT_BY_ID = "SELECT * FROM Contacts WHERE ContactId = ?";
            return jdbc.queryForObject(SELECT_CONTACT_BY_ID, new ContactMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void addContact(Contact contact) {
        final String INSERT_CONTACT = "INSERT INTO Contacts (FirstName, LastName, Phone, Email, MessageBox) "
                 + "VALUES (?, ?, ?, ?, ?)";
        
        jdbc.update(INSERT_CONTACT,
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getPhoneNumber(),
                    contact.getEmail(),
                    contact.getMessage());  
        
        int contactId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setId(contactId);
    }

    @Override
    public void removeContact(int id) {
        final String DELETE_CONTACT = "DELETE FROM Contacts WHERE ContactId = ?";
        jdbc.update(DELETE_CONTACT, id);
    }
    
    public static final class ContactMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet rs, int index) throws SQLException {
            Contact contact = new Contact(rs.getString("FirstName"),
                                          rs.getString("LastName"),
                                          rs.getString("Phone"),
                                          rs.getString("Email"),
                                          rs.getString("MessageBox"));
            contact.setId(rs.getInt("ContactId"));
            return contact;
        }
    }
}
