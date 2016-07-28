package com.siziksu.architecture.common.model.weather.response;

/**
 * WeatherResponse class.
 */
public class WeatherResponse {

    private String place;
    private String temperature;
    private String time;

    /**
     * Constructor.
     */
    public WeatherResponse() {
        // Constructor
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
