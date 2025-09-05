package com.example.weatherapi.controllers;

import com.example.weatherapi.Services.MainService;
import com.example.weatherapi.Services.WeatherService;
import com.example.weatherapi.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @ModelAttribute("location")
    public LocationData locationAttr() { return new LocationData(); }

    @Autowired
    private RestTemplate restTemplate;

    WeatherService weatherService =  new WeatherService();
    MainService service = new MainService();
    double latitude = 0,longitude = 0;
    String locationName = "", country = "", state = "", city ="";

    public MainController(WeatherService weatherService, MainService service) {
        this.weatherService = weatherService;
        this.service = service;
    }

    @GetMapping("/")
    public String showDashboard(@RequestParam(required=false) Double lat,
                                @RequestParam(required=false) Double lon,
                                @RequestParam(required=false) String name,
                                Model model) {

        model.addAttribute("location", new LocationData());

        WeatherData weather;
        String locationName;

        try {

            // London as default
            LocationData locationData = weatherService.geocodeCity("London");
            weather = weatherService.getByCoords(locationData.getLatitude(), locationData.getLongitude());
            locationName = locationData.getName();


            model.addAttribute("weather", weather);
            model.addAttribute("locationName", locationName);
            model.addAttribute("MainService", service);

        } catch (Exception e) {
            System.out.println("Error fetching weather: " + e.getMessage());
        }

        return "index";
    }

    //Map the user input to API search
    @PostMapping("/")
    public String locationSubmit(@ModelAttribute LocationData location,
                                 @RequestParam(required = false) Double lat,
                                 @RequestParam(required = false) Double lon,
                                 Model model) {

        if (lat != null && lon != null) {
            latitude = lat;
            longitude = lon;
            locationName = "Your Location";
        } else {
            city = location.getName().replaceAll(" ", "+");
            String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";

            //GET API provided coordinates
            try {
                LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);
                assert response != null;
                LocationData results = response.getResults().getFirst();
                latitude = results.getLatitude();
                longitude = results.getLongitude();
                locationName = results.getName();
                country = results.getCountry();
                state = results.getAdmin1();
                model.addAttribute("location", location);
                System.out.println(location);

                ObjectMapper objectMapperTest = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                //wxResponse = objectMapper.convertValue(wxResponse, WeatherData.class);
                results = objectMapperTest.convertValue(results, LocationData.class);
                String json = objectMapperTest.writeValueAsString(results);
                //            System.out.println(json);
                //print JSON data to console

                if (results.getAdmin1().equals(results.getName())) {
                    model.addAttribute("locationName", locationName + ", " + country);
                } else {
                    model.addAttribute("locationName", locationName + ", " + state + ", " + country);
                }

                System.out.println(
                        "*****************Location API Data***********************"
                                + "\n" + url + "\n" + locationName + "\n" + country + "\n" + state + "\n" + latitude + "\n" + longitude + "\n"
                                + "*********************************************************"
                );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        //Weather API GET call using Geo Coordinates from above^^
        String weatherURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,snowfall_sum,precipitation_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,wind_direction_10m_dominant&hourly=,temperature_2m,weather_code&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,cloud_cover,wind_gusts_10m,wind_direction_10m,wind_speed_10m&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch&timezone=auto";
        System.out.println(weatherURL);

        try{
            ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            WeatherData wxResponse = restTemplate.getForObject(weatherURL, WeatherData.class); //GET API Data call
            //wxResponse = objectMapper.convertValue(wxResponse, WeatherData.class);
            String json = objectMapper.writeValueAsString(wxResponse);
            model.addAttribute("weather", wxResponse);
            model.addAttribute("MainService", service);
            //print JSON data to console
            //System.out.println(json);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "index";
    }
}
