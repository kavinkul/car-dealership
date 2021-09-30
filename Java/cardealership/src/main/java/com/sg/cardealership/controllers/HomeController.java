package com.sg.cardealership.controllers;

import com.sg.cardealership.service.HomeService;
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
    
    @GetMapping("/index")
    public String displayFeatured(Model model) {
        return "index";
    }
    
    @GetMapping("/specials")
    public String displaySpecials(Model model) {
        return "";
    }
    
    @GetMapping("/contact")
    public String displayContact(Model model) {
        return "";
    }
}
