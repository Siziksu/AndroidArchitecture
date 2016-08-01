package com.siziksu.architecture.data.weather;

import com.google.gson.Gson;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.Data;
import com.siziksu.architecture.data.client.weather.IWeatherClient;

/**
 * Weather class.
 */
public final class GetWeatherData extends Data implements IGetWeatherData<GetWeatherData> {

    private static final String KEY_WEATHER_CACHE = "weather_cache";
    private static final long EXPIRY_TIME = 30000;
    private IWeatherClient IWeatherClient;

    private String city;
    private boolean useCache;
    private long expiry;

    /**
     * Constructor.
     */
    public GetWeatherData() {
        // Constructor
    }

    @Override
    public GetWeatherData setGetWeatherClient(IWeatherClient client) {
        this.IWeatherClient = client;
        return this;
    }

    @Override
    public GetWeatherData city(String city) {
        this.city = city;
        return this;
    }

    @Override
    public GetWeatherData useCache() {
        this.useCache = true;
        return this;
    }

    @Override
    public GetWeatherData cacheExpiryTime(long millis) {
        this.expiry = millis;
        return this;
    }

    @Override
    public OpenWeather run() {
        OpenWeather openWeather;
        if (!useCache) {
            return IWeatherClient.getWeather(city);
        }
        String cache = getCache(KEY_WEATHER_CACHE);
        long expiryTime = expiry > 0 ? expiry : EXPIRY_TIME;
        if (isCacheValid(cache, KEY_WEATHER_CACHE, expiryTime)) {
            openWeather = new Gson().fromJson(cache, OpenWeather.class);
        } else {
            openWeather = IWeatherClient.getWeather(city);
            if (openWeather != null) {
                cache = new Gson().toJson(openWeather);
                setCache(KEY_WEATHER_CACHE, cache);
            }
        }
        return openWeather;
    }
}
