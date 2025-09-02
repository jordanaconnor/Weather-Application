package com.example.weatherapi.controllers;

import com.example.weatherapi.Services.MainService;
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

        double latitude = 0,longitude = 0;
        String locationName = "", city = location.getName(), country = "", state = "";
        city = city.replaceAll(" ", "+");


        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";

        //GET API provided coordinates
        try {
            LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);
            assert response != null;
            LocationData results = response.getResults().getFirst();

            latitude =  results.getLatitude();
            longitude = results.getLongitude();
            locationName = results.getName();
            country = results.getCountry();
            state = results.getAdmin1();

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
        model.addAttribute("locationName", locationName + ", ");
        model.addAttribute("state", state + ", ");
        model.addAttribute("country", country);

        //Weather API GET call using Geo Coordinates from above^^

        MainService service = new MainService();

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
        String currentWxCodeString = "", convertedDate = "", wxCodeString = "", hourlyTime, hourlyWeatherCode, hourlyTemp, dailyTime, dailyWeatherCode, dailyTempMax,  dailyTempMin, dailyRain, dailyShowers, dailySnowfall, dailyPrecipHours, dailyPrecipSum, dailyPrecipProb, dailyWindSpeedMax, dailyWindGustsMax, dailyWindDirection,  currTimeUnit = "", currIntervalUnit, currTempUnit = "", currHumidityUnit = "", currApparentTempUnit = "",  currPrecipUnit = "", currRainUnit = "", currShowersUnit = "", currSnowfallUnit = "", currWxCodeUnit = "", currCloudCoverUnit = "", currWindSpeedUnit, currWindGustsUnit, currWindDirectionUnit, currTime = "", date = "", Date1 = "", Date2 = "", Date3 = "", Date4 = "", Date5 = "", Date6 = "", Date7 = "";
        int currInterval, currHumidity = 0, currWeatherCode = 0, currCloudCover = 0, wxCode = 0, wxCode0 = 0, wxCode1 = 0, wxCode2 = 0, wxCode3 = 0, wxCode4 = 0, wxCode5 = 0, wxCode6 = 0;
        double maxTempDaily = 0.00, minTempDaily = 0.00, currTemp = 0, currApparentTemp = 0, currPrecip = 0, currRain = 0, currShowers = 0, currSnowfall = 0, currWindGusts, currWindDirection, currWindSpeed;

        String weatherURL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,snowfall_sum,precipitation_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,wind_direction_10m_dominant&hourly=,temperature_2m,weather_code&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,rain,showers,snowfall,weather_code,cloud_cover,wind_gusts_10m,wind_direction_10m,wind_speed_10m&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch";
        System.out.println(weatherURL);

        try{
            ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            WeatherData wxResponse = restTemplate.getForObject(weatherURL, WeatherData.class); //GET API Data call
            wxResponse = objectMapper.convertValue(wxResponse, WeatherData.class);
            String json = objectMapper.writeValueAsString(wxResponse);

            //print JSON data to console
            //System.out.println(json);

            //Get Hourly Weather Data
            hourlyTimes = wxResponse.getHourly().getTime();
            hourlyTemps = wxResponse.getHourly().getTemperature_2m();
            hourlyWeatherCodes = wxResponse.getHourly().getWeather_code();
            //Hourly units
            hourlyTime = wxResponse.getHourly_units().getTime();
            hourlyWeatherCode = wxResponse.getHourly_units().getWeather_code();
            hourlyTemp = wxResponse.getHourly_units().getTemperature_2m();


            //Get Current Weather Data
            currTemp = wxResponse.getCurrent().getTemperature_2m();
            currHumidity = wxResponse.getCurrent().getRelative_humidity_2m();
            currApparentTemp = wxResponse.getCurrent().getApparent_temperature();
            currPrecip = wxResponse.getCurrent().getPrecipitation();
            currRain = wxResponse.getCurrent().getRain();
            currShowers = wxResponse.getCurrent().getShowers();
            currSnowfall = wxResponse.getCurrent().getSnowfall();
            currWeatherCode = wxResponse.getCurrent().getWeather_code();
            currentWxCodeString = service.convertWXCode(currWeatherCode); //translate wx code to string
            currCloudCover = wxResponse.getCurrent().getCloud_cover();

            //current units
            currTempUnit = wxResponse.getCurrent_units().getTemperature_2m();
            currHumidityUnit = wxResponse.getCurrent_units().getRelative_humidity_2m();
            currApparentTempUnit = wxResponse.getCurrent_units().getApparent_temperature();
            currPrecipUnit = wxResponse.getCurrent_units().getPrecipitation();
            currRainUnit = wxResponse.getCurrent_units().getRain();
            currShowersUnit = wxResponse.getCurrent_units().getShowers();
            currSnowfallUnit = wxResponse.getCurrent_units().getSnowfall();
            currCloudCoverUnit = wxResponse.getCurrent_units().getCloud_cover();

            //Loop through daily data for the 7-Day forecast to output data to html file
            for (int listValue = 0; listValue < 7; listValue++) {

                date = wxResponse.getDaily().getTime().get(listValue);
                wxCode = wxResponse.getDaily().getWeather_code().get(listValue);
                maxTempDaily = wxResponse.getDaily().getTemperature_2m_max().get(listValue);
                minTempDaily = wxResponse.getDaily().getTemperature_2m_min().get(listValue);

                convertedDate = service.convertDateFormat(date);
                wxCodeString = service.convertWXCode(wxCode);

                model.addAttribute("date" + listValue, convertedDate + " - ");
                model.addAttribute("wxCode" + listValue, wxCodeString + " - High Temp: ");
                model.addAttribute("maxTempDaily" + listValue, maxTempDaily + " - Low Temp: ");
                model.addAttribute("minTempDaily" + listValue, minTempDaily);

            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Display current weather data
        model.addAttribute("CurrentTemp", currTemp);
        model.addAttribute("CurrentTempUnit", currTempUnit);
        model.addAttribute("CurrentApparentTemp", currApparentTemp);
        model.addAttribute("CurrentApparentTempUnit", currApparentTempUnit);
        model.addAttribute("CurrWeatherCode", currentWxCodeString);
        model.addAttribute("CurrentPrecipitation", currPrecip);
        model.addAttribute("CurrentPrecipUnit", currPrecipUnit);
        model.addAttribute("CurrentHumidity", currHumidity);
        model.addAttribute("CurrentHumidityUnit", currHumidityUnit);
        model.addAttribute("CurrentRain", currRain);
        model.addAttribute("CurrRainUnit", currRainUnit);
        model.addAttribute("CurrentShowers", currShowers);
        model.addAttribute("CurrShowersUnit", currShowersUnit);
        model.addAttribute("CurrentSnowfall", currSnowfall);
        model.addAttribute("CurrSnowfallUnit", currSnowfallUnit);
        model.addAttribute("CurrentCloudCover", currCloudCover);
        model.addAttribute("CurrentCloudCoverUnit", currCloudCoverUnit);

        return "home";
    }
}
