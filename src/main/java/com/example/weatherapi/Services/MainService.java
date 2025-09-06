package com.example.weatherapi.Services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MainService {

    public String convertDateFormat(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd");
        return localDate.format(outputFormatter);
    }

    public String trimDateFormat() {
        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH");
        return now.format(formatter);
    }

    public String getCurrentTimeForZone(String zoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH");
        return java.time.ZonedDateTime.now(java.time.ZoneId.of(zoneId)).format(formatter);
    }

    public String getLocalTimeForZone(String zoneId) {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return java.time.ZonedDateTime.now(java.time.ZoneId.of(zoneId)).format(displayFormat);
    }

    public String extractTime(@org.jetbrains.annotations.NotNull String date) {
        return date.substring(date.indexOf("T") + 1);
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
    }

    public String getWeatherIcon(int wxCode) {
        return switch (wxCode) {
            case 0 -> "wi wi-day-sunny";
            case 1 -> "wi wi-day-cloudy-high";
            case 2 -> "wi wi-day-cloudy";
            case 3 -> "wi wi-cloudy";
            case 45, 48 -> "wi wi-fog";
            case 51, 53, 55 -> "wi wi-sprinkle";
            case 56, 57, 66, 67, 77 -> "wi wi-sleet";
            case 61, 63, 65 -> "wi wi-rain";
            case 71, 73, 75, 85 -> "wi wi-snow";
            case 80, 81 -> "wi wi-showers";
            case 82 -> "wi wi-rain-wind";
            case 86 -> "wi wi-snow-wind";
            case 95 -> "wi wi-storm-showers";
            case 96, 99 -> "wi wi-thunderstorm";
            default -> "wi wi-refresh";
        };
    }
}
