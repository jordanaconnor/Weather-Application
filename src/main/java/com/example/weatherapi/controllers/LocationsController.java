package com.example.weatherapi.controllers;

import com.example.weatherapi.domain.GeoCodingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.weatherapi.domain.Locations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class LocationsController {

    @Autowired
    private RestTemplate restTemplate;

    public LocationsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/location")
    public String showDashboard(Model model) {
        model.addAttribute("location", new Locations());
        return "location";
    }

    //Map the user input to API search
    @PostMapping("/location")
    public String locationSubmit(@ModelAttribute Locations location, Model model) {

        String city = location.getName();
        city = city.replaceAll(" ", "+");

        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        GeoCodingResponse response = restTemplate.getForObject(url, GeoCodingResponse.class); //GET API Data call
        assert response != null;
        Locations results = response.getResults().getFirst();

        System.out.println("*****************Location API Data***********************");
        System.out.println(url);
        System.out.println("JSON Name: " + results.getName());
        System.out.println("JSON country: " + results.getCountry());
        System.out.println("JSON lat: " + results.getLatitude());
        System.out.println("JSON long: " + results.getLongitude());
        System.out.println("*********************************************************");

        model.addAttribute("location", location);

        return "location";

    }
}
