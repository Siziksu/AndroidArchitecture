package com.siziksu.architecture.data.client.weather;

import com.siziksu.architecture.common.model.weather.OpenWeather;

public interface IWeatherClient {

    /**
     * Gets the {@link OpenWeather}.
     *
     * @return a {@link OpenWeather} object
     */
    OpenWeather getWeather(String city);
}
