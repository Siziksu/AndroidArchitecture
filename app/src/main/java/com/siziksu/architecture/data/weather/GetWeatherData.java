package com.siziksu.architecture.data.weather;

import com.google.gson.Gson;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.Data;
import com.siziksu.architecture.data.client.weather.WeatherClient;

/**
 * Weather class.
 */
public final class GetWeatherData extends Data {

    private static final String KEY_WEATHER_CACHE = "weather_cache";
    private static final long EXPIRY_TIME = 30000;
    private WeatherClient weatherClient;

    private String city;
    private boolean useCache;
    private long expiry;

    /**
     * Constructor.
     */
    public GetWeatherData() {
        // Constructor
    }

    public GetWeatherData setGetWeatherClient(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
        return this;
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
            return weatherClient.getWeather(city);
        }
        String cache = getCache(KEY_WEATHER_CACHE);
        long expiryTime = expiry > 0 ? expiry : EXPIRY_TIME;
        if (isCacheValid(cache, KEY_WEATHER_CACHE, expiryTime)) {
            openWeather = new Gson().fromJson(cache, OpenWeather.class);
        } else {
            openWeather = weatherClient.getWeather(city);
            if (openWeather != null) {
                cache = new Gson().toJson(openWeather);
                setCache(KEY_WEATHER_CACHE, cache);
            }
        }
        return openWeather;
    }
}
