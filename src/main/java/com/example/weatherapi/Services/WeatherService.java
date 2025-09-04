package com.example.weatherapi.Services;

import com.example.weatherapi.domain.LocationData;
import com.example.weatherapi.domain.LocationResponse;
import com.example.weatherapi.domain.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    public LocationData geocodeCity(String city) {
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        LocationResponse response = restTemplate.getForObject(url, LocationResponse.class);
        return response.getResults().getFirst();
    }

    public WeatherData getByCoords(double lat, double lon) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + lat
                + "&longitude=" + lon
                + "&daily=weather_code,temperature_2m_max,temperature_2m_min"
                + "&hourly=temperature_2m,weather_code"
                + "&current=temperature_2m,apparent_temperature,precipitation,rain,showers,snowfall,cloud_cover,weather_code,relative_humidity_2m"
                + "&timezone=auto";
        return restTemplate.getForObject(url, WeatherData.class);
    }
}
