package com.sg.cardealership.controllers;

import com.sg.cardealership.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    
    @GetMapping("/vehicles")
    public String displayVehicles(Model model) {
       return "";
    }
    
    @GetMapping("/addvehicle")
    public String displayAddVehicle(Model model) {
       return "";
    } 
    
    @GetMapping("/editvehicle/{1}")
    public String displayEditVehicle(Model model) {
       return "";
    }
    
    @GetMapping("/users")
    public String displayUsers(Model model) {
       return "";
    }
    
    @GetMapping("/adduser")
    public String displayAddUser(Model model) {
       return "";
    }
    
    @GetMapping("/edituser")
    public String displayEditUser(Model model) {
       return "";
    } 
    
    @GetMapping("/specials")
    public String displaySpecials(Model model) {
       return "";
    } 
}
