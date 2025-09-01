package com.example.weatherapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodingResponse {

    private List<Locations> results;

    public List<Locations> getResults() {
        return results;
    }
    public void setResults(List<Locations> results) {
        this.results = results;
    }
}
