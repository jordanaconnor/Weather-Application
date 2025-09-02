package com.example.weatherapi.controllers;

import com.example.weatherapi.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    public MainController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("location", new LocationData());
        return "home";
    }

    //Map the user input to API search
    @PostMapping("/")
    public String locationSubmit(@ModelAttribute LocationData location, Model model, WeatherData weatherData) {

        double latitude = 0;
        double longitude = 0;
        String city = location.getName();
        city = city.replaceAll(" ", "+");

        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";

        //GET API provided coordinates
        try {
            LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);
            assert response != null;
            LocationData results = response.getResults().getFirst();

            latitude =  results.getLatitude();
            longitude = results.getLongitude();

            System.out.println("*****************Location API Data***********************");
            System.out.println(url);
            System.out.println("JSON Name: " + results.getName());
            System.out.println("JSON country: " + results.getCountry());
            System.out.println("JSON lat: " + results.getLatitude());
            System.out.println("JSON long: " + results.getLongitude());
            System.out.println("*********************************************************");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        model.addAttribute("location", location);

        //Weather API GET call using Geo Coordinates from above^^

        int time = 0;
        double snowfall = 0.00;
        double showers = 0.00;
        double rain = 0.00;
        double precipitation = 0.00;
        double temp = 0.00;
        int cloudCover = 0;
        String timezone = "";

        List<String> hourlyTimes, dailyTimes;
        List<Double> hourlyTemps, maxTemps, minTemps, rainSum, showersSum, snowfallSum, precipitationSum, precipitationHours, windSpeedMax, windGustsMax;
        List<Integer> hourlyWeatherCodes, dailyWeatherCodes, precipitationProb, windDirection;
        String hourlyTime, hourlyWeatherCode, hourlyTemp, dailyTime, dailyWeatherCode, dailyTempMax,  dailyTempMin, dailyRain, dailyShowers, dailySnowfall, dailyPrecipHours, dailyPrecipSum, dailyPrecipProb, dailyWindSpeedMax, dailyWindGustsMax, dailyWindDirection,  currTimeUnit = "", currIntervalUnit, currTempUnit = "", currHumidityUnit, currApparentTempUnit,  currPrecipUnit = "", currRainUnit = "", currShowersUnit = "", currSnowfallUnit = "", currWxCodeUnit, currCloudCoverUnit = "", currWindSpeedUnit, currWindGustsUnit, currWindDirectionUnit, currTime = "";
        int currInterval, currHumidity, currWeatherCode, currCloudCover = 0;
        double currTemp = 0, currApparentTemp, currPrecip = 0, currRain = 0, currShowers = 0, currSnowfall = 0, currWindGusts, currWindDirection, currWindSpeed;

        String weatherURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,snowfall_sum,precipitation_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,wind_direction_10m_dominant&hourly=,temperature_2m,weather_code&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,cloud_cover,wind_gusts_10m,wind_direction_10m,wind_speed_10m&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch";
        System.out.println(weatherURL);

        try{
            ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            WeatherData wxResponse = restTemplate.getForObject(weatherURL, WeatherData.class); //GET API Data call
            wxResponse = objectMapper.convertValue(wxResponse, WeatherData.class);
            String json = objectMapper.writeValueAsString(wxResponse);

            System.out.println(json);

            //Get Hourly Weather Data
            hourlyTimes = wxResponse.getHourly().getTime();
            hourlyTemps = wxResponse.getHourly().getTemperature_2m();
            hourlyWeatherCodes = wxResponse.getHourly().getWeather_code();
            //Hourly units
            hourlyTime = wxResponse.getHourly_units().getTime();
            hourlyWeatherCode = wxResponse.getHourly_units().getWeather_code();
            hourlyTemp = wxResponse.getHourly_units().getTemperature_2m();

            //Get Daily Weather Data
            dailyTimes = wxResponse.getDaily().getTime();
            dailyWeatherCodes = wxResponse.getDaily().getWeather_code();
            maxTemps = wxResponse.getDaily().getTemperature_2m_max();
            minTemps = wxResponse.getDaily().getTemperature_2m_min();
            rainSum = wxResponse.getDaily().getRain_sum();
            showersSum = wxResponse.getDaily().getShowers_sum();
            snowfallSum = wxResponse.getDaily().getSnowfall_sum();
            precipitationSum = wxResponse.getDaily().getPrecipitation_sum();
            precipitationHours = wxResponse.getDaily().getPrecipitation_hours();
            precipitationProb = wxResponse.getDaily().getPrecipitation_probability_max();
            windSpeedMax = wxResponse.getDaily().getWind_speed_10m_max();
            windGustsMax = wxResponse.getDaily().getWind_gusts_10m_max();
            windDirection = wxResponse.getDaily().getWind_direction_10m_dominant();
            //Daily units
            dailyTime = wxResponse.getDaily_units().getTime();
            dailyWeatherCode = wxResponse.getDaily_units().getWeather_code();
            dailyTempMax = wxResponse.getDaily_units().getTemperature_2m_max();
            dailyTempMin = wxResponse.getDaily_units().getTemperature_2m_min();
            dailyRain = wxResponse.getDaily_units().getRain_sum();
            dailyShowers = wxResponse.getDaily_units().getShowers_sum();
            dailySnowfall = wxResponse.getDaily_units().getSnowfall_sum();
            dailyPrecipHours = wxResponse.getDaily_units().getPrecipitation_hours();
            dailyPrecipSum =  wxResponse.getDaily_units().getPrecipitation_sum();
            dailyPrecipProb = wxResponse.getDaily_units().getPrecipitation_probability_max();
            dailyWindSpeedMax = wxResponse.getDaily_units().getWind_speed_10m_max();
            dailyWindGustsMax = wxResponse.getDaily_units().getWind_gusts_10m_max();
            dailyWindDirection = wxResponse.getDaily_units().getWind_direction_10m_dominant();

            //Get Current Weather Data
            currTime = wxResponse.getCurrent().getTime();
            currInterval = wxResponse.getCurrent().getInterval();
            currTemp = wxResponse.getCurrent().getTemperature_2m();
            currHumidity = wxResponse.getCurrent().getRelative_humidity_2m();
            currApparentTemp = wxResponse.getCurrent().getApparent_temperature();
            currPrecip = wxResponse.getCurrent().getPrecipitation();
            currRain = wxResponse.getCurrent().getRain();
            currShowers = wxResponse.getCurrent().getShowers();
            currSnowfall = wxResponse.getCurrent().getSnowfall();
            currWeatherCode = wxResponse.getCurrent().getWeather_code();
            currCloudCover = wxResponse.getCurrent().getCloud_cover();
            currWindGusts = wxResponse.getCurrent().getWind_gusts_10m();
            currWindDirection = wxResponse.getCurrent().getWind_direction_10m();
            currWindSpeed = wxResponse.getCurrent().getWind_speed_10m();
            //current units
            currTimeUnit = wxResponse.getCurrent_units().getTime();
            currIntervalUnit = wxResponse.getCurrent_units().getInterval();
            currTempUnit = wxResponse.getCurrent_units().getTemperature_2m();
            currHumidityUnit = wxResponse.getCurrent_units().getRelative_humidity_2m();
            currApparentTempUnit = wxResponse.getCurrent_units().getApparent_temperature();
            currPrecipUnit = wxResponse.getCurrent_units().getPrecipitation();
            currRainUnit = wxResponse.getCurrent_units().getRain();
            currShowersUnit = wxResponse.getCurrent_units().getShowers();
            currSnowfallUnit = wxResponse.getCurrent_units().getSnowfall();
            currWxCodeUnit = wxResponse.getCurrent_units().getWeather_code();
            currCloudCoverUnit = wxResponse.getCurrent_units().getCloud_cover();
            currWindSpeedUnit = wxResponse.getCurrent_units().getWind_speed_10m();
            currWindGustsUnit = wxResponse.getCurrent_units().getWind_gusts_10m();
            currWindDirectionUnit = wxResponse.getCurrent_units().getWind_direction_10m();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Display current weather data
        model.addAttribute("CurrentTime", currTime);
        model.addAttribute("CurrentSnowfall", currSnowfall);
        model.addAttribute("CurrentShowers", currShowers);
        model.addAttribute("CurrentRain", currRain);
        model.addAttribute("CurrentPrecipitation", currPrecip);
        model.addAttribute("CurrentTemp", currTemp);
        model.addAttribute("CurrentCloudCover", currCloudCover);
        model.addAttribute("CurrTimeUnit", currTimeUnit);
        model.addAttribute("CurrSnowfallUnit", currSnowfallUnit);
        model.addAttribute("CurrShowersUnit", currShowersUnit);
        model.addAttribute("CurrRainUnit", currRainUnit);
        model.addAttribute("CurrentPrecipUnit", currPrecipUnit);
        model.addAttribute("CurrentTempUnit", currTempUnit);
        model.addAttribute("CurrentCloudCoverUnit", currCloudCoverUnit);
        

        return "home";
    }
}
