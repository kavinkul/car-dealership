/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Sale;
import com.sg.cardealership.models.User;
import com.sg.cardealership.models.Vehicle;
import java.util.List;

/**
 *
 * @author kavin
 */
public interface SalesDao {
    List<Sale> getAllSales();

    Sale getSale(User user);

    void addSale(User user, Vehicle vehicle);
}
