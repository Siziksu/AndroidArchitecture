package com.siziksu.architecture.domain.weather;

import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Fail;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;

/**
 * IGetWeatherRequest class.
 */
public interface IGetWeatherRequest {

    /**
     * Sets the city
     *
     * @param city the city
     *
     * @return the get weather request
     */
    IGetWeatherRequest city(String city);

    /**
     * Sets the {@link Success} used to return the response if the
     * request ends successfully.
     * <br />
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the request fails.
     * <br />
     * Sets the {@link Done} used to emit when the response of the
     * request ends.
     * <br />
     * And runs the request.
     *
     * @param success the success function
     * @param fail    the fail function
     * @param done    the done function
     */
    void subscribe(Success<OpenWeather> success, Fail fail, Done done);
}
