package com.siziksu.architecture.data.weather;

import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.Data;
import com.siziksu.architecture.provider.Provider;

/**
 * Weather class.
 */
public final class GetWeatherData extends Data {

    private static final String KEY_WEATHER_CACHE = "weather_cache";
    private static final long EXPIRY_TIME = 30000;

    private String city;
    private boolean useCache;
    private long expiry;

    /**
     * Constructor.
     */
    public GetWeatherData() {
        // Constructor
    }

    /**
     * Sets the city
     *
     * @param city the city
     */
    public GetWeatherData city(String city) {
        this.city = city;
        return this;
    }

    /**
     * Uses cache.
     *
     * @return {@link GetWeatherData}
     */
    public GetWeatherData useCache() {
        this.useCache = true;
        return this;
    }

    /**
     * Sets the expiry time for the cache. Default expiry time is 30 seconds.
     *
     * @param millis the milliseconds
     *
     * @return {@link GetWeatherData}
     */
    public GetWeatherData cacheExpiryTime(long millis) {
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
            return Provider.get().weather().client().getWeather(city);
        }
        String cache = getCache(KEY_WEATHER_CACHE);
        long expiryTime = expiry > 0 ? expiry : EXPIRY_TIME;
        if (isCacheValid(cache, KEY_WEATHER_CACHE, expiryTime)) {
            openWeather = Provider.get().gson().fromJson(cache, OpenWeather.class);
        } else {
            openWeather = Provider.get().weather().client().getWeather(city);
            if (openWeather != null) {
                cache = Provider.get().gson().toJson(openWeather);
                setCache(KEY_WEATHER_CACHE, cache);
            }
        }
        return openWeather;
    }
}
