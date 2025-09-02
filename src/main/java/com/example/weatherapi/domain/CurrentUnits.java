package com.example.weatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentUnits {

    private String time;
    private String interval;
    private String temperature_2m;
    private String relative_humidity_2m;
    private String apparent_temperature;
    private String precipitation;
    private String rain;
    private String showers;
    private String snowfall;
    private String weather_code;
    private String cloud_cover;
    private String wind_gusts_10m;
    private String wind_direction_10m;
    private String wind_speed_10m;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(String temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public String getRelative_humidity_2m() {
        return relative_humidity_2m;
    }

    public void setRelative_humidity_2m(String relative_humidity_2m) {
        this.relative_humidity_2m = relative_humidity_2m;
    }

    public String getApparent_temperature() {
        return apparent_temperature;
    }

    public void setApparent_temperature(String apparent_temperature) {
        this.apparent_temperature = apparent_temperature;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getShowers() {
        return showers;
    }

    public void setShowers(String showers) {
        this.showers = showers;
    }

    public String getSnowfall() {
        return snowfall;
    }

    public void setSnowfall(String snowfall) {
        this.snowfall = snowfall;
    }

    public String getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(String weather_code) {
        this.weather_code = weather_code;
    }

    public String getCloud_cover() {
        return cloud_cover;
    }

    public void setCloud_cover(String cloud_cover) {
        this.cloud_cover = cloud_cover;
    }

    public String getWind_gusts_10m() {
        return wind_gusts_10m;
    }

    public void setWind_gusts_10m(String wind_gusts_10m) {
        this.wind_gusts_10m = wind_gusts_10m;
    }

    public String getWind_direction_10m() {
        return wind_direction_10m;
    }

    public void setWind_direction_10m(String wind_direction_10m) {
        this.wind_direction_10m = wind_direction_10m;
    }

    public String getWind_speed_10m() {
        return wind_speed_10m;
    }

    public void setWind_speed_10m(String wind_speed_10m) {
        this.wind_speed_10m = wind_speed_10m;
    }
}
