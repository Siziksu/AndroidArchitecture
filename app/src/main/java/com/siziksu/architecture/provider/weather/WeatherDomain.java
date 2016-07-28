package com.siziksu.architecture.provider.weather;

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
     * Request.
     *
     * @return the weather
     */
    public GetWeatherRequest getWeather() {
        return new GetWeatherRequest();
    }
}
