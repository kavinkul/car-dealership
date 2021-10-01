/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.controllers;

import com.sg.cardealership.models.Vehicle;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kavin
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String displayLogin(Model model) {
        return "login";
    }
    
}
