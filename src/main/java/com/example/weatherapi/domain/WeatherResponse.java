package com.example.weatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private List<WeatherData> results;

    public List<WeatherData> getResults() {
        return results;
    }
    public void setResults(List<WeatherData> results) {
        this.results = results;
    }
}
