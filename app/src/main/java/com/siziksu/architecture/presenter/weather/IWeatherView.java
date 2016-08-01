package com.siziksu.architecture.presenter.weather;

import com.siziksu.architecture.common.model.weather.response.WeatherResponse;
import com.siziksu.architecture.presenter.View;

/**
 * IWeatherView class.
 */
public interface IWeatherView extends View {

    /**
     * When we have the weather response.
     *
     * @param weatherResponse the response
     */
    void onWeather(WeatherResponse weatherResponse);

    /**
     * On weather error.
     */
    void onWeatherError();
}
