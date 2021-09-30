package com.sg.cardealership.controllers;

import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Vehicle;
import com.sg.cardealership.service.AdminService;
import java.util.List;
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
        List<Vehicle> allVehicles = adminService.getAllVehicles();
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
    @GetMapping("/Makes")
    public String displayMakes(Model model) {
        List<Make> make = adminService.getAllMake();
        model.addAttribute("allMakes", make);
        return "adminAddMake";
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
