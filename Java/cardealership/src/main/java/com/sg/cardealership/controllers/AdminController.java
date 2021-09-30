package com.sg.cardealership.controllers;

import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Special;
import com.sg.cardealership.models.Vehicle;
import com.sg.cardealership.service.AdminService;
import com.sg.cardealership.service.AdminServiceInvalidDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        List<Make> make = adminService.getAllMakes();
        model.addAttribute("allMakes", make);
        return "adminAddMake";
    }

    @PostMapping("/Makes")
    public String addMakes(HttpServletRequest request) throws Exception {
        String makeName = request.getParameter("newMake");
        String email = request.getParameter("userEmail");
        try {
            adminService.addMake(makeName, email);
        } catch (SQLException | AdminServiceInvalidDataException e) {

        }
        return "redirect:/admin/Makes";
    }

    @GetMapping("/Models")
    public String displayModels(Model model) {
        List<com.sg.cardealership.models.Model> carModelList = adminService.getAllModels();
        model.addAttribute("allModels", carModelList);
        model.addAttribute("allMakes", adminService.getAllMakes());
        return "adminAddModel";
    }

    @PostMapping("/Models")
    public String addModels(HttpServletRequest request) throws Exception {
        String modelName = request.getParameter("newModel");
        String makeName = request.getParameter("make");
        int year = Integer.parseInt(request.getParameter("year"));
        String email = request.getParameter("userEmail");
        try {
            adminService.addModel(makeName, modelName, year, email);
        } catch (SQLException | AdminServiceInvalidDataException e) {
            System.out.println(makeName);
            System.out.println(modelName);
            System.out.println(year);
            System.out.println(email);
        }
        return "redirect:/admin/Models";
    }

    @GetMapping("/Users")
    public String displayUsers(Model model) {
        return "";
    }

    @GetMapping("/adduser")
    public String displayAddUser(Model model) {
        return "adminAddUser";
    }

    @PostMapping("/adduser")
    public String addUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("Confirm Password");
        try {
            adminService.addUser(firstName, lastName, email, role, password, confirmPassword);
        } catch (AdminServiceInvalidDataException | SQLException e) {

        }
        return "redirect:/admin/Users";
    }

    @GetMapping("/edituser")
    public String displayEditUser(Model model) {
        return "";
    }

    @GetMapping("adminSpecials")
    public String displaySpecials(Model model) {
        List<Special> specials = adminService.getAllSpecials();
        model.addAttribute("specials", specials);
        return "adminSpecials";
    }

    @PostMapping("addSpecial")
    public String addSpecial(String specialTitle, String description) {
        Special special = new Special(specialTitle, description);
        adminService.addSpecial(special);

        return "redirect:adminSpecials";
    }
}
