package com.siziksu.architecture.domain.weather;

import com.siziksu.architecture.common.AsyncObject;
import com.siziksu.architecture.common.model.weather.OpenWeather;

/**
 * Use case class (Interactor).
 */
public class GetWeatherRequest extends WeatherRequest implements AsyncObject.Request<OpenWeather> {

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

    @Override
    public OpenWeather request() throws Exception {
        return data.getWeather(city);
    }

    @Override
    public void onSuccess(long id, OpenWeather response) {
        listener.onWeather(response);
    }

    @Override
    public void onError(long id, Exception e) {
        listener.onError(e);
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
