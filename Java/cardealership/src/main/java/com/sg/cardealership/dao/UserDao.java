/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.User;
import java.util.List;

/**
 *
 * @author kavin
 */
public interface UserDao {
    List<User> getAllUsers();

    User getUser(String email);

    void addUser(User user);

    void removeUser(String email);

    void editUser(User user);
}
