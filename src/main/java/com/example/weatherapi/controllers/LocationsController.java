package com.example.weatherapi.controllers;

import org.springframework.ui.Model;
import com.example.weatherapi.domain.Locations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationsController {

    @GetMapping("/location")
    public String showDashboard(Model model) {
        model.addAttribute("location", new Locations());
        return "location";
    }

    @PostMapping("/location")
    public String locationSubmit(@ModelAttribute Locations location, Model model) {
        model.addAttribute("location", location);
        return "location";
    }
}
