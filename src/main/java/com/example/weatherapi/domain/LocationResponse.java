package com.example.weatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {

    private List<LocationData> results;

    public List<LocationData> getResults() {
        return results;
    }
    public void setResults(List<LocationData> results) {
        this.results = results;
    }
}
