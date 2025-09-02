package com.example.weatherapi.Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MainService {

    public String convertDateFormat(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd");
        return localDate.format(outputFormatter);
    }

    public String convertWXCode(int wxCode) {

        Map<Integer, String> wxCodeMeanings = new HashMap<>() {{
            put(0, "Clear Skies");
            put(1, "Mainly Clear Skies");
            put(2, "Partly Cloudy");
            put(3, "Overcast");
            put(45, "Fog");
            put(48, "Depositing Rime Fog");
            put(51, "Light Drizzle");
            put(53, "Moderate Drizzle");
            put(55, "Dense Drizzle");
            put(56, "Light Freezing Drizzle");
            put(57, "Dense Freezing Drizzle");
            put(61, "Slight Rain");
            put(63, "Moderate Rain");
            put(65, "Heavy Rain");
            put(66, "Light Freezing Rain");
            put(67, "Heavy Freezing Rain");
            put(71, "Slight Snow Fall");
            put(73, "Moderate Snow Fall");
            put(75, "Heavy Snow Fall");
            put(77, "Snow Grains");
            put(80, "Slight Rain Showers");
            put(81, "Moderate Rain Showers");
            put(82," Violent Rain Showers");
            put(85, "Slight Snow Showers");
            put(86, "Heavy Snow Showers");
            put(95, "Thunderstorms");
            put(96, "Thunderstorm and Slight Hail");
            put(99, "Thunderstorm and Heavy Hail");
        }};

        boolean checkValue = wxCodeMeanings.containsKey(wxCode);
        return wxCodeMeanings.getOrDefault(wxCode, "Could not locate Weather Code");



        //return wxDescription;
    }
}
