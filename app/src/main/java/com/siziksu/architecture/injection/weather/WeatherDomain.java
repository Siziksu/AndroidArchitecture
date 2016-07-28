package com.siziksu.architecture.injection.weather;

import com.siziksu.architecture.domain.weather.GetWeatherRequest;

/**
 * Weather class.
 */
public final class WeatherDomain {

    /**
     * Constructor.
     */
    public WeatherDomain() {
        // Constructor
    }

    public GetWeatherRequest getWeather() {
        return new GetWeatherRequest();
    }
}
