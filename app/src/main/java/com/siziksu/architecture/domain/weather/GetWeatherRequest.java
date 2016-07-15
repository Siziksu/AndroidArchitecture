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

    private final String city;
    private Success<OpenWeather> success;
    private Error error;
    private Done done;

    /**
     * Constructor.
     *
     * @param city the city
     */
    public GetWeatherRequest(String city) {
        this.city = city;
    }

    /**
     * Runs the request.
     */
    public void run() {
        new AsyncObject<OpenWeather>()
                .runOnMainThread()
                .action(() -> new WeatherData().getWeather(city))
                .done(done)
                .success(success)
                .error(error)
                .execute();
    }

    /**
     * Sets the {@link Done} used to emit when the response of the
     * {@link Action} ends.
     *
     * @param done the Done that will be used
     *
     * @return {@code GetWeatherRequest}
     */
    public GetWeatherRequest done(final Done done) {
        this.done = done;
        return this;
    }

    /**
     * Sets the {@link Success} used to return the response if the
     * {@link Action} ends successfully.
     *
     * @param success the Success that will be used
     *
     * @return {@code GetWeatherRequest}
     */
    public GetWeatherRequest success(final Success<OpenWeather> success) {
        this.success = success;
        return this;
    }

    /**
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the {@link Action} fails.
     *
     * @param error the Error that will be used
     *
     * @return {@code GetWeatherRequest}
     */
    public GetWeatherRequest error(final Error error) {
        this.error = error;
        return this;
    }
}
