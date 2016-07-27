package com.siziksu.architecture.domain;

import com.siziksu.architecture.common.Injector;
import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Fail;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;

/**
 * WeatherDomain class.
 */
public final class WeatherDomain {

    /**
     * Weather class.
     */
    public static class GetWeather {

        private String city;

        /**
         * Constructor.
         */
        public GetWeather() {
            // Constructor
        }

        /**
         * Sets the city.
         *
         * @param city the city
         *
         * @return {@link GetWeather}
         */
        public GetWeather city(String city) {
            this.city = city;
            return this;
        }

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
        public void subscribe(final Success<OpenWeather> success, final Fail fail, final Done done) {
            Injector.provideGetWeatherRequest()
                    .city(city)
                    .subscribe(success, fail, done);
        }
    }
}
