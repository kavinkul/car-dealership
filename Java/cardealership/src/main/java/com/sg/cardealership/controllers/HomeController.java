package com.sg.cardealership.controllers;

import com.sg.cardealership.models.Special;
import com.sg.cardealership.models.Vehicle;
import com.sg.cardealership.service.HomeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;
    
    @GetMapping
    public String displayFeatured(Model model) {
        List<Vehicle> featured = homeService.getAllFeaturedVehicles();
        model.addAttribute("vehicles", featured);
        return "index";
    }
    
    @GetMapping("/specials")
    public String displaySpecials(Model model) {
        List<Special> specials = homeService.getAllSpecials();
        model.addAttribute("specials", specials);
        return "specials";
    }
    
    @GetMapping("/contact")
    public String displayContact(Model model) {
        return "";
    }
}
