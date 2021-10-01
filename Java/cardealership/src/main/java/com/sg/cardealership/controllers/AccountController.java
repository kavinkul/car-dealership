/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.controllers;

import com.sg.cardealership.service.AdminService;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String displayLogin(Model model) {
        return "login";
    }

    @GetMapping("/password")
    public String displayChangePassword(Model model) {
        return "changePassword";
    }

    @PostMapping("/password")
    public String displayChangePassword(HttpServletRequest request) {
        String email = request.getParameter("userEmail");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (!password.equals(confirmPassword))
            return "password";

        String encodedPassword = passwordEncoder.encode(password);

        try {
            adminService.editPassword(email, encodedPassword);
        } catch (SQLException e) {
            return "password";
        }
        return "redirect:/logout";
    }


}
