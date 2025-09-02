package com.example.weatherapi.controllers;

import com.example.weatherapi.domain.LocationResponse;
import com.example.weatherapi.domain.WeatherResponse;
import com.example.weatherapi.domain.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.weatherapi.domain.LocationData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    public MainController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/location")
    public String showDashboard(Model model) {
        model.addAttribute("location", new LocationData());
        return "location";
    }

    //Map the user input to API search
    @PostMapping("/location")
    public String locationSubmit(@ModelAttribute LocationData location, Model model, WeatherData weatherData) {

        String city = location.getName();
        city = city.replaceAll(" ", "+");

        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        LocationResponse response = restTemplate.getForObject(url, LocationResponse.class); //GET API Data call
        assert response != null;
        LocationData results = response.getResults().getFirst();

        System.out.println("*****************Location API Data***********************");
        System.out.println(url);
        System.out.println("JSON Name: " + results.getName());
        System.out.println("JSON country: " + results.getCountry());
        System.out.println("JSON lat: " + results.getLatitude());
        System.out.println("JSON long: " + results.getLongitude());
        System.out.println("*********************************************************");

        model.addAttribute("location", location);

        //Weather API GET call using Geo Coordinates from above^^

        double latitude =  results.getLatitude();
        double longitude = results.getLongitude();

        String weatherURL = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=snowfall,showers,rain,precipitation,temperature_2m,cloud_cover&timeformat=unixtime&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch";
        //String weatherURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current=snowfall,showers,rain,precipitation,temperature_2m,cloud_cover&timeformat=unixtime&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch";
        System.out.println(weatherURL);


        WeatherResponse wxResponse = restTemplate.getForObject(weatherURL, WeatherResponse.class); //GET API Data call
        assert wxResponse != null;
        WeatherData wxResults = wxResponse.getResults().getFirst();

        System.out.println("JSON Temp: " + wxResults.getTemperature_2m());
        model.addAttribute("location's", weatherData);


        return "location";
    }
}
