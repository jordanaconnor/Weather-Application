package com.example.weatherapplication.Services;

import com.example.weatherapplication.domain.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherData getByCoords(double lat, double lon, String unit) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + lat
                + "&longitude=" + lon
                + "&daily=weather_code,temperature_2m_max,temperature_2m_min"
                + "&hourly=temperature_2m,weather_code"
                + "&current=temperature_2m,apparent_temperature,precipitation,rain,showers,snowfall,cloud_cover,weather_code,relative_humidity_2m"
                + "&timezone=auto"
                + "&temperature_unit=" + ("F".equalsIgnoreCase(unit) ? "fahrenheit" : "celsius");
        return restTemplate.getForObject(url, WeatherData.class);
    }
}
