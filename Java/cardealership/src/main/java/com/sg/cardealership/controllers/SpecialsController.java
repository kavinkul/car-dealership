/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.controllers;

import com.sg.cardealership.dao.SpecialsDao;
import com.sg.cardealership.models.Special;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author agrah
 */
@Controller
public class SpecialsController {
    @Autowired
    SpecialsDao specialsDao;
    
    @GetMapping("specials")
    public String displaySpecials(Model model) {
        List<Special> specials = specialsDao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "specials";
    }
}
