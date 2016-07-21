package com.siziksu.architecture.data;

import com.google.gson.Gson;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.client.weather.WeatherClient;

/**
 * WeatherData class.
 */
public final class WeatherData {

    /**
     * Weather class.
     */
    public static class Weather extends Data {

        private static final String KEY_WEATHER_CACHE = "weather_cache";
        private static final long EXPIRY_TIME_WEATHER = 10000;

        private String city;
        private boolean useCache;

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
         * Uses cache.
         *
         * @return {@link Weather}
         */
        public Weather useCache() {
            this.useCache = true;
            return this;
        }

        /**
         * Runs the request.
         *
         * @return {@link OpenWeather}
         */
        public OpenWeather run() {
            OpenWeather openWeather;
            if (!useCache) {
                return new WeatherClient().getWeather(city);
            }
            String cache = getCache(KEY_WEATHER_CACHE);
            if (isCacheValid(cache, KEY_WEATHER_CACHE, EXPIRY_TIME_WEATHER)) {
                openWeather = new Gson().fromJson(cache, OpenWeather.class);
            } else {
                openWeather = new WeatherClient().getWeather(city);
                if (openWeather != null) {
                    cache = new Gson().toJson(openWeather);
                    setCache(KEY_WEATHER_CACHE, cache);
                }
            }
            return openWeather;
        }
    }
}
