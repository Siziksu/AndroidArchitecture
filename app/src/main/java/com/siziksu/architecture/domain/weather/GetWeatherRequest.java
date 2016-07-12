package com.siziksu.architecture.domain.weather;

import com.siziksu.architecture.common.AsyncObject;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.WeatherData;

/**
 * Request class.
 */
public final class GetWeatherRequest {

    private final OnGetWeatherListener listener;
    private final String city;

    /**
     * Constructor.
     *
     * @param city     the city
     * @param listener the callback for the request
     */
    public GetWeatherRequest(String city, OnGetWeatherListener listener) {
        this.city = city;
        this.listener = listener;
    }

    /**
     * Runs the request.
     */
    public void run() {
        new AsyncObject<OpenWeather>()
                .runOnMainThread()
                .action(() -> new WeatherData().getWeather(city))
                .done(listener::done)
                .success(listener::success)
                .error(listener::error)
                .execute();
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
