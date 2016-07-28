package com.siziksu.architecture.injection.weather;

import com.siziksu.architecture.data.client.weather.WeatherClient;
import com.siziksu.architecture.presenter.weather.WeatherPresenter;

/**
 * Weather class.
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

    /**
     * Presenter.
     *
     * @return the weather presenter
     */
    public WeatherPresenter presenter() {
        return new WeatherPresenter();
    }

    /**
     * Domain.
     *
     * @return the weather domain
     */
    public WeatherDomain domain() {
        return new WeatherDomain();
    }

    /**
     * Data.
     *
     * @return the weather data
     */
    public WeatherData data() {
        return new WeatherData();
    }

    /**
     * Client.
     *
     * @return the weather client
     */
    public WeatherClient client() {
        return new WeatherClient();
    }
}
