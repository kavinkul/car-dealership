package com.sg.cardealership.controllers;

import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.Special;
import com.sg.cardealership.models.Transmission;
import com.sg.cardealership.models.Type;
import com.sg.cardealership.models.User;
import com.sg.cardealership.service.AdminService;
import com.sg.cardealership.service.AdminServiceInvalidDataException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        return "adminVehicles";
    }

    @GetMapping("/addvehicle")
    public String displayAddVehicle(Model model) {
        model.addAttribute("makeList", adminService.getAllMakes());
        model.addAttribute("modelList", adminService.getAllModels());
        model.addAttribute("typeList", Arrays.asList(Type.values()));
        model.addAttribute("transList", Arrays.asList(Transmission.values()));

        return "adminAddVehicle";
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
            // This should have errors handling. Not implemented.
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
            // This should have errors handling. Not implemented.
        }
        return "redirect:/admin/Models";
    }

    @GetMapping("/Users")
    public String displayUsers(Model model) {
        List<User> allUsers = adminService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "adminUsers";
    }

    @GetMapping("/AddUser")
    public String displayAddUser(Model model) {
        List<String> roles = Arrays.asList(Role.values()).stream().map(r -> r.getValue()).collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "adminAddUser";
    }

    @PostMapping("/AddUser")
    public String addUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        try {
            adminService.addUser(firstName, lastName, email, role, password, confirmPassword);
        } catch (AdminServiceInvalidDataException | SQLException e) {
            // This should have errors handling. Not implemented.
        }
        return "redirect:/admin/Users";
    }

    @GetMapping("/EditUser")
    public String displayEditUser(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        model.addAttribute("user", adminService.getUser(email));
        List<String> roles = Arrays.asList(Role.values()).stream().map(r -> r.getValue()).collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "adminEditUser";
    }

    @PostMapping("/EditUser")
    public String editUser(HttpServletRequest request) {
        String adminEmail = request.getParameter("adminEmail");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // Admins are not allowed to edit themselves for whatever reason.

        if (adminEmail.equals(email)) {
            return "redirect:/admin/Users";
        }

        try {
            adminService.editUser(firstName, lastName, email, role);
        } catch (AdminServiceInvalidDataException | SQLException e) {
            // This should have errors handling. Not implemented.
        }
        return "redirect:/admin/Users";
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

    @GetMapping("deleteSpecial")
    public String deleteStudent(Integer id) {
        adminService.removeSpecial(id);
        return "redirect:adminSpecials";
    }
}
