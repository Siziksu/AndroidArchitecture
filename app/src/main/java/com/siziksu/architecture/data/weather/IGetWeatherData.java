package com.siziksu.architecture.data.weather;

import com.siziksu.architecture.common.model.weather.OpenWeather;

/**
 * IGetWeatherData class.
 *
 * @param <T> the type parameter
 */
public interface IGetWeatherData<T extends IGetWeatherData<T>> {

    /**
     * Sets the city
     *
     * @param city the city
     *
     * @return the t
     */
    T city(String city);

    /**
     * Uses cache.
     *
     * @return {@link GetWeatherData}
     */
    T useCache();

    /**
     * Sets the expiry time for the cache. Default expiry time is 30 seconds.
     *
     * @param millis the milliseconds
     *
     * @return {@link GetWeatherData}
     */
    T cacheExpiryTime(long millis);

    /**
     * Runs the request.
     *
     * @return {@link OpenWeather}
     */
    OpenWeather run();
}
