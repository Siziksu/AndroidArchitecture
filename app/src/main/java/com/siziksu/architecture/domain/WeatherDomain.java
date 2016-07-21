package com.siziksu.architecture.domain;

import com.siziksu.architecture.common.functions.Action;
import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Error;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.domain.weather.GetWeatherRequest;

/**
 * WeatherDomain class.
 */
public final class WeatherDomain {

    /**
     * Weather class.
     */
    public static class Weather {

        private String city;

        /**
         * Constructor.
         */
        public Weather() {
            // Constructor
        }

        /**
         * Sets the city.
         *
         * @param city the city
         *
         * @return {@link Weather}
         */
        public Weather setCity(String city) {
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
         * @param success the success function
         * @param error   the error function
         * @param done    the done function
         */
        public void subscribe(final Success<OpenWeather> success, final Error error, final Done done) {
            new GetWeatherRequest()
                    .setCity(city)
                    .subscribe(success, error, done);
        }
    }
}
