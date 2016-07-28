package com.siziksu.architecture.injection.weather;

import com.siziksu.architecture.data.weather.GetWeatherData;

/**
 * WeatherData class.
 */
public class WeatherData {

    /**
     * Constructor.
     */
    public WeatherData() {
        // Constructor
    }

    /**
     * Gets weather data.
     *
     * @return the weather
     */
    public GetWeatherData getWeather() {
        return new GetWeatherData();
    }
}
