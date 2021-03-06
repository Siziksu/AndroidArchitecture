package com.siziksu.architecture.presenter.weather;

import com.siziksu.architecture.common.model.weather.response.WeatherResponse;
import com.siziksu.architecture.presenter.IView;

/**
 * IWeatherView class.
 */
public interface IWeatherView extends IView {

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
