package com.sg.cardealership.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarDealershipController {
    @GetMapping
    public String redirect(Model model) {
        return "redirect:/home";
    }
}
