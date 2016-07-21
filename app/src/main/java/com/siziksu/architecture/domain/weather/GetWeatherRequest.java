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
     * Sets the {@link Success} used to return the response if the
     * {@link Action} ends successfully.
     * <br />
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the {@link Action} fails.
     * <br />
     * Sets the {@link Done} used to emit when the response of the
     * {@link Action} ends.
     *
     * @param success the Success that will be used
     * @param error   the Error that will be used
     * @param done    the Done that will be used
     *
     * @return {@code GetWeatherRequest}
     */
    public GetWeatherRequest subscribe(final Success<OpenWeather> success, final Error error, final Done done) {
        this.success = success;
        this.error = error;
        this.done = done;
        return this;
    }

    /**
     * Runs the request.
     */
    public void run() {
        new AsyncObject<OpenWeather>()
                .runOnMainThread()
                .action(() -> new WeatherData.Weather().setCity(city).useCache().run())
                .subscribe(success, error, done)
                .run();
    }
}
