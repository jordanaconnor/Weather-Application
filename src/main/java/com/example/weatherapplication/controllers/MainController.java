package com.example.weatherapplication.controllers;

import com.example.weatherapplication.Services.MainService;
import com.example.weatherapplication.Services.WeatherService;
import com.example.weatherapplication.domain.LocationData;
import com.example.weatherapplication.domain.LocationResponse;
import com.example.weatherapplication.domain.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class MainController {

    @ModelAttribute("location")
    public LocationData locationAttr() { return new LocationData(); }

    @Autowired
    private RestTemplate restTemplate;

    WeatherService weatherService =  new WeatherService();
    MainService service = new MainService();
    double latitude = 0,longitude = 0;
    String locationName = "", country = "", state = "", city = "", zoneId = "";


    public MainController(WeatherService weatherService, MainService service) {
        this.weatherService = weatherService;
        this.service = service;
    }

    @GetMapping("/")
    public String showDashboard(Model model) {

        WeatherData weatherData;

        try {

            // London as default
            weatherData = weatherService.getByCoords(51.50853, -0.12574, "C");
            model.addAttribute("weather", weatherData);
            model.addAttribute("MainService", service);
            model.addAttribute("unit", "C");

            zoneId = weatherData.getTimezone();
            String currentTime = service.getCurrentTimeForZone(zoneId);

            int currentHourIndex = 0;
            for (int i = 0; i < weatherData.getHourly().getTime().size(); i++) {
                String time = weatherData.getHourly().getTime().get(i);
                if (time.startsWith(currentTime)) {
                    currentHourIndex = i;
                    break;
                }
            }
            model.addAttribute("currentHourIndex", currentHourIndex);

            //Grab the local time
            String localTime = service.getLocalTimeForZone(zoneId);
            model.addAttribute("localTime", localTime);

        } catch (Exception e) {
            System.out.println("Error fetching weatherData: " + e.getMessage());
        }
        return "index";
    }

    //Map the user input to API search
    @PostMapping("/")
    public String locationSubmit(@ModelAttribute LocationData location, @RequestParam(required = false, defaultValue = "C") String unit, Model model) {

        if (location.getName() == null || location.getName().isBlank()) {
            city = "London";
        } else {
            city = location.getName().replaceAll(" ", "+");
        }

        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";

            //GET API provided coordinates
        try {
            LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);

            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                LocationData results = response.getResults().get(0);
                latitude = results.getLatitude();
                longitude = results.getLongitude();
                locationName = results.getName();
                country = results.getCountry();
                state = results.getAdmin1();
                model.addAttribute("location", location);

                if (results.getAdmin1().equals(results.getName())) {
                    model.addAttribute("locationName", locationName + ", " + country);
                } else {
                    model.addAttribute("locationName", locationName + ", " + state + ", " + country);
                }
            } else {
                // fallback to London if geocoding fails
                latitude = 51.50853;
                longitude = -0.12574;
                locationName = "London";
                country = "United Kingdom";
                state = "England";
                model.addAttribute("locationName", "London, England, United Kingdom");
            }
        } catch (Exception e) {
            System.out.println("Geocoding error: " + e.getMessage());
            // also fallback
            latitude = 51.50853;
            longitude = -0.12574;
            model.addAttribute("locationName", "London, England, United Kingdom");
        }

        String weatherURL;
        if ("F".equalsIgnoreCase(unit)) {
            weatherURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,snowfall_sum,precipitation_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,wind_direction_10m_dominant&hourly=,temperature_2m,weather_code&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,cloud_cover,wind_gusts_10m,wind_direction_10m,wind_speed_10m&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch&timezone=auto";
            System.out.println(weatherURL);
        } else {
            weatherURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,snowfall_sum,precipitation_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,wind_direction_10m_dominant&hourly=,temperature_2m,weather_code&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,cloud_cover,wind_gusts_10m,wind_direction_10m,wind_speed_10m&wind_speed_unit=mph&precipitation_unit=inch&timezone=auto";
            System.out.println(weatherURL);
        }


        try{
            WeatherData wxResponse = restTemplate.getForObject(weatherURL, WeatherData.class); //GET API Data call
            model.addAttribute("weather", wxResponse);
            model.addAttribute("MainService", service);
            model.addAttribute("unit", unit);


            zoneId = wxResponse.getTimezone();
            String currentTime = service.getCurrentTimeForZone(zoneId);

            int currentHourIndex = 0;
            for (int i = 0; i < wxResponse.getHourly().getTime().size(); i++) {
                String time = wxResponse.getHourly().getTime().get(i);
                if (time.startsWith(currentTime)) {
                    currentHourIndex = i;
                    break;
                }
            }
            model.addAttribute("currentHourIndex", currentHourIndex);

            //Grab the local time
            String localTime = service.getLocalTimeForZone(zoneId);
            model.addAttribute("localTime", localTime);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "index";
    }
}
