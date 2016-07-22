package com.siziksu.architecture.domain.weather;

import com.siziksu.architecture.common.AsyncObject;
import com.siziksu.architecture.common.functions.Action;
import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Error;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.WeatherData;

/**
 * Request class.
 */
public final class GetWeatherRequest {

    private static final long EXPIRY_TIME = 5000;

    private String city;

    /**
     * Constructor.
     */
    public GetWeatherRequest() {
        // Constructor
    }

    /**
     * Sets the city for the request.
     *
     * @param city the city
     *
     * @return {@link GetWeatherRequest}
     */
    public GetWeatherRequest city(String city) {
        this.city = city;
        return this;
    }

    /**
     * Sets the {@link Success} used to return the response if the
     * {@link Action} ends successfully.
     * <br />
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the {@link Action} fails.
     * <br />
     * Sets the {@link Done} used to emit when the response of the
     * {@link Action} ends.
     * <br />
     * And runs the request.
     *
     * @param success the Success that will be used
     * @param error   the Error that will be used
     * @param done    the Done that will be used
     */
    public void subscribe(final Success<OpenWeather> success, final Error error, final Done done) {
        new AsyncObject<OpenWeather>()
                .subscribeOnMainThread()
                .action(() -> new WeatherData.Weather()
                        .city(city)
                        .useCache()
                        .cacheExpiryTime(EXPIRY_TIME)
                        .run())
                .subscribe(success, error, done);
    }
}
