/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Contact;

/**
 *
 * @author kavin
 */
public interface ContactDao {
    Contact getContact(int id);

    void addContact(Contact user);

    void removeContact(String email);
}
