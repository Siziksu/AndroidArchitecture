package com.siziksu.architecture.domain;

import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Error;
import com.siziksu.architecture.common.functions.Success;
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
     * Gets Weather
     *
     * @param city    the city
     * @param success the success function
     * @param error   the error function
     * @param done    the done function
     */
    public void getWeather(final String city, final Success<OpenWeather> success, final Error error, final Done done) {
        new GetWeatherRequest(city).subscribe(success, error, done).run();
    }
}
