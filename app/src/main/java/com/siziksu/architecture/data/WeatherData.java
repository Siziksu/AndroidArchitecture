package com.siziksu.architecture.data;

import com.siziksu.architecture.common.Injector;
import com.siziksu.architecture.common.model.weather.OpenWeather;

/**
 * WeatherData class.
 */
public final class WeatherData {

    /**
     * Weather class.
     */
    public static class GetWeather extends Data {

        private static final String KEY_WEATHER_CACHE = "weather_cache";
        private static final long EXPIRY_TIME = 30000;

        private String city;
        private boolean useCache;
        private long expiry;

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
         * Uses cache.
         *
         * @return {@link GetWeather}
         */
        public GetWeather useCache() {
            this.useCache = true;
            return this;
        }

        /**
         * Sets the expiry time for the cache. Default expiry time is 30 seconds.
         *
         * @param millis the milliseconds
         *
         * @return {@link GetWeather}
         */
        public GetWeather cacheExpiryTime(long millis) {
            this.expiry = millis;
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
                return Injector.provideWeatherClient().getWeather(city);
            }
            String cache = getCache(KEY_WEATHER_CACHE);
            long expiryTime = expiry > 0 ? expiry : EXPIRY_TIME;
            if (isCacheValid(cache, KEY_WEATHER_CACHE, expiryTime)) {
                openWeather = Injector.provideGson().fromJson(cache, OpenWeather.class);
            } else {
                openWeather = Injector.provideWeatherClient().getWeather(city);
                if (openWeather != null) {
                    cache = Injector.provideGson().toJson(openWeather);
                    setCache(KEY_WEATHER_CACHE, cache);
                }
            }
            return openWeather;
        }
    }
}
