package com.example.weatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyUnits {

    private String time;
    private String weather_code;
    private String temperature_2m_max;
    private String temperature_2m_min;
    private String rain_sum;
    private String showers_sum;
    private String snowfall_sum;
    private String precipitation_sum;
    private String precipitation_hours;
    private String precipitation_probability_max;
    private String wind_speed_10m_max;
    private String wind_gusts_10m_max;
    private String wind_direction_10m_dominant;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(String weather_code) {
        this.weather_code = weather_code;
    }

    public String getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(String temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public String getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(String temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }

    public String getRain_sum() {
        return rain_sum;
    }

    public void setRain_sum(String rain_sum) {
        this.rain_sum = rain_sum;
    }

    public String getShowers_sum() {
        return showers_sum;
    }

    public void setShowers_sum(String showers_sum) {
        this.showers_sum = showers_sum;
    }

    public String getSnowfall_sum() {
        return snowfall_sum;
    }

    public void setSnowfall_sum(String snowfall_sum) {
        this.snowfall_sum = snowfall_sum;
    }

    public String getPrecipitation_sum() {
        return precipitation_sum;
    }

    public void setPrecipitation_sum(String precipitation_sum) {
        this.precipitation_sum = precipitation_sum;
    }

    public String getPrecipitation_hours() {
        return precipitation_hours;
    }

    public void setPrecipitation_hours(String precipitation_hours) {
        this.precipitation_hours = precipitation_hours;
    }

    public String getPrecipitation_probability_max() {
        return precipitation_probability_max;
    }

    public void setPrecipitation_probability_max(String precipitation_probability_max) {
        this.precipitation_probability_max = precipitation_probability_max;
    }

    public String getWind_speed_10m_max() {
        return wind_speed_10m_max;
    }

    public void setWind_speed_10m_max(String wind_speed_10m_max) {
        this.wind_speed_10m_max = wind_speed_10m_max;
    }

    public String getWind_gusts_10m_max() {
        return wind_gusts_10m_max;
    }

    public void setWind_gusts_10m_max(String wind_gusts_10m_max) {
        this.wind_gusts_10m_max = wind_gusts_10m_max;
    }

    public String getWind_direction_10m_dominant() {
        return wind_direction_10m_dominant;
    }

    public void setWind_direction_10m_dominant(String wind_direction_10m_dominant) {
        this.wind_direction_10m_dominant = wind_direction_10m_dominant;
    }
}
