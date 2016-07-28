package com.siziksu.architecture.injection.weather;

import com.siziksu.architecture.data.client.weather.WeatherClient;
import com.siziksu.architecture.presenter.weather.WeatherPresenter;

/**
 * GetWeather class.
 */
public class Weather {

    private static Weather instance;

    private Weather() {
        // Constructor
    }

    /**
     * Gets an instance of the class.
     *
     * @return the injector
     */
    public static Weather get() {
        if (instance == null) {
            instance = new Weather();
        }
        return instance;
    }

    public WeatherPresenter presenter() {
        return new WeatherPresenter();
    }

    public WeatherDomain domain() {
        return new WeatherDomain();
    }

    public WeatherData data() {
        return new WeatherData();
    }

    public WeatherClient client() {
        return new WeatherClient();
    }
}
