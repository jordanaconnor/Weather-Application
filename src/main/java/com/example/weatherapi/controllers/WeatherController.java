package com.example.weatherapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @GetMapping("/getWeather")
    public String getWeather() {
        return "Hello World";
    }
}
