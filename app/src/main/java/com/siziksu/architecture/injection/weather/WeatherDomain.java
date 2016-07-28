package com.siziksu.architecture.injection.weather;

import com.siziksu.architecture.domain.weather.GetWeatherRequest;

/**
 * WeatherDomain class.
 */
public final class WeatherDomain {

    /**
     * Constructor.
     */
    public WeatherDomain() {
        // Constructor
    }

    /**
     * Gets weather request.
     *
     * @return the weather
     */
    public GetWeatherRequest getWeather() {
        return new GetWeatherRequest();
    }
}
