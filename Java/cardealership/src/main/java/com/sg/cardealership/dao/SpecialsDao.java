/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Special;
import java.util.List;

/**
 *
 * @author kavin
 */
public interface SpecialsDao {
    List<Special> getAllSpecials();

    Special getSpecial(int id);

    void addSpecial(Special special);

    void removeSpecial(int id);

    void editSpecial(Special special);
}
