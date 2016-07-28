package com.siziksu.architecture.provider.weather;

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
     * Data.
     *
     * @return the weather
     */
    public GetWeatherData getWeather() {
        return new GetWeatherData();
    }
}
