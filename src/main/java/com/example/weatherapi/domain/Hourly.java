package com.example.weatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hourly {

    private List<String> time;
    private List<Double> temperature_2m;
    private List<Integer> weather_code;

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Double> getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(List<Double> temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public List<Integer> getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(List<Integer> weather_code) {
        this.weather_code = weather_code;
    }
}
