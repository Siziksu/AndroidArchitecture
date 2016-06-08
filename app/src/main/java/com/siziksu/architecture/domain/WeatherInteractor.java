package com.siziksu.architecture.domain;

import com.siziksu.architecture.common.AsyncObject;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.domain.weather.GetWeatherRequest;

/**
 * Interactor class.
 */
public class WeatherInteractor {

    private static WeatherInteractor instance;

    private WeatherInteractor() {
        // Private constructor
    }

    /**
     * This class must be initialized before asking for an instance.
     */
    public static void init() {
        if (instance == null) {
            instance = new WeatherInteractor();
        }
    }

    /**
     * This method provides an instance of this class. First needs to be initialized.
     *
     * @return the instance
     */
    public static WeatherInteractor getInstance() {
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
    public void getWeather(String city, final OnGetWeatherListener listener) {
        new AsyncObject<>(new GetWeatherRequest(city, new GetWeatherRequest.OnGetWeatherListener() {

            @Override
            public void onWeather(final OpenWeather dashboard) {
                listener.onWeather(dashboard);
            }

            @Override
            public void onError(Exception e) {
                listener.onError(e);
            }
        })).execute();
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
        void onWeather(OpenWeather response);

        /**
         * On error.
         *
         * @param e the exception
         */
        void onError(Exception e);
    }
}
