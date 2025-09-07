package com.example.weatherapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//JSON Data Storage for API fetching. Empty getters/setters are for future reference if I want to add more data to the site.

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;

    //Nested JSON data to Java Classes
    private Current current =  new Current();
    private CurrentUnits current_units = new CurrentUnits();
    private Daily daily = new Daily();
    private DailyUnits daily_units = new DailyUnits();
    private Hourly hourly = new Hourly();
    private HourlyUnits hourly_units =  new HourlyUnits();

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getGenerationtime_ms() {
        return generationtime_ms;
    }

    public void setGenerationtime_ms(double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public void setUtc_offset_seconds(int utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public CurrentUnits getCurrent_units() {
        return current_units;
    }

    public void setCurrent_units(CurrentUnits current_units) {
        this.current_units = current_units;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public DailyUnits getDaily_units() {
        return daily_units;
    }

    public void setDaily_units(DailyUnits daily_units) {
        this.daily_units = daily_units;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public HourlyUnits getHourly_units() {
        return hourly_units;
    }

    public void setHourly_units(HourlyUnits hourly_units) {
        this.hourly_units = hourly_units;
    }
}

