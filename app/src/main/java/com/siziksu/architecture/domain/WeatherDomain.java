package com.siziksu.architecture.domain;

import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.domain.weather.GetWeatherRequest;

/**
 * WeatherDomain class.
 */
public final class WeatherDomain {

    private static WeatherDomain instance;

    private WeatherDomain() {
        // Private constructor
    }

    /**
     * This class must be initialized before asking for an instance.
     */
    public static void init() {
        if (instance == null) {
            instance = new WeatherDomain();
        }
    }

    /**
     * This method provides an instance of this class. First needs to be initialized.
     *
     * @return the instance
     */
    public static WeatherDomain getInstance() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    /**
     * Gets weather.
     *
     * @param city     the city
     * @param listener the listener
     */
    public void getWeather(final String city, final OnGetWeatherListener listener) {
        new GetWeatherRequest(city)
                .success(listener::success)
                .error(listener::error)
                .done(listener::done)
                .run();
    }

    /**
     * Callback.
     */
    public interface OnGetWeatherListener {

        /**
         * On weather.
         *
         * @param response the response
         */
        void success(OpenWeather response);

        /**
         * On error.
         *
         * @param e the exception
         */
        void error(Exception e);

        /**
         * Emits when the request is done.
         */
        void done();
    }
}
