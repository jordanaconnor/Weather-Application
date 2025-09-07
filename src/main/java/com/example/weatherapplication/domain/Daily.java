package com.example.weatherapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Daily {

    private List<String> time = new ArrayList<>(Collections.nCopies(7, "9999-01-01"));
    private List<Integer> weather_code = new ArrayList<>(Collections.nCopies(7, 0));
    private List<Double> temperature_2m_max = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Double> temperature_2m_min = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Double> rain_sum = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Double> showers_sum = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Double> snowfall_sum = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Double> precipitation_sum = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Double> precipitation_hours = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Integer> precipitation_probability_max = new ArrayList<>(Collections.nCopies(7, 0));
    private List<Double> wind_speed_10m_max = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Double> wind_gusts_10m_max = new ArrayList<>(Collections.nCopies(7, 0.00));
    private List<Integer> wind_direction_10m_dominant = new ArrayList<>(Collections.nCopies(7, 0));

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<Integer> getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(List<Integer> weather_code) {
        this.weather_code = weather_code;
    }

    public List<Double> getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(List<Double> temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public List<Double> getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(List<Double> temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public List<Double> getRain_sum() {
        return rain_sum;
    }

    public void setRain_sum(List<Double> rain_sum) {
        this.rain_sum = rain_sum;
    }

    public List<Double> getShowers_sum() {
        return showers_sum;
    }

    public void setShowers_sum(List<Double> showers_sum) {
        this.showers_sum = showers_sum;
    }

    public List<Double> getSnowfall_sum() {
        return snowfall_sum;
    }

    public void setSnowfall_sum(List<Double> snowfall_sum) {
        this.snowfall_sum = snowfall_sum;
    }

    public List<Double> getPrecipitation_sum() {
        return precipitation_sum;
    }

    public void setPrecipitation_sum(List<Double> precipitation_sum) {
        this.precipitation_sum = precipitation_sum;
    }

    public List<Double> getPrecipitation_hours() {
        return precipitation_hours;
    }

    public void setPrecipitation_hours(List<Double> precipitation_hours) {
        this.precipitation_hours = precipitation_hours;
    }

    public List<Integer> getPrecipitation_probability_max() {
        return precipitation_probability_max;
    }

    public void setPrecipitation_probability_max(List<Integer> precipitation_probability_max) {
        this.precipitation_probability_max = precipitation_probability_max;
    }

    public List<Double> getWind_speed_10m_max() {
        return wind_speed_10m_max;
    }

    public void setWind_speed_10m_max(List<Double> wind_speed_10m_max) {
        this.wind_speed_10m_max = wind_speed_10m_max;
    }

    public List<Double> getWind_gusts_10m_max() {
        return wind_gusts_10m_max;
    }

    public void setWind_gusts_10m_max(List<Double> wind_gusts_10m_max) {
        this.wind_gusts_10m_max = wind_gusts_10m_max;
    }

    public List<Integer> getWind_direction_10m_dominant() {
        return wind_direction_10m_dominant;
    }

    public void setWind_direction_10m_dominant(List<Integer> wind_direction_10m_dominant) {
        this.wind_direction_10m_dominant = wind_direction_10m_dominant;
    }
}
