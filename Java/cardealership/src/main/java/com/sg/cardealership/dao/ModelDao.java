/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Model;
import java.util.List;

/**
 *
 * @author kavin
 */
public interface ModelDao {
    List<Model> getAllModels();

    Model getModel(int id);

    void addModel(Model model);

    void removeModel(int id);

    void editModel(Model model);
}
