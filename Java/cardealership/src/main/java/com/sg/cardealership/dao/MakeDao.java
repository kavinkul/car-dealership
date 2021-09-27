/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Make;
import java.util.List;

/**
 *
 * @author kavin
 */
public interface MakeDao {
    List<Make> getAllMakes();

    Make getMake(int id);

    void addMake(Make make);

    void removeMake(int id);

    void editMake(Make make);
}
