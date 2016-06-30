package com.siziksu.architecture.data;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.siziksu.architecture.common.Preferences;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.client.weather.WeatherClient;

/**
 * WeatherData class.
 */
public final class WeatherData {

    private static final String WEATHER = "weather";
    private static final String WEATHER_CACHE = "weather_cache";
    private static final long WEATHER_EXPIRY_TIME = 10000;

    /**
     * Gets the weather.
     *
     * @param city the city
     *
     * @return a {@link OpenWeather} object
     */
    public OpenWeather getWeather(String city) {
        OpenWeather openWeather;
        String cache = Preferences.getInstance().getString(WEATHER, null);
        long cache_time = Preferences.getInstance().getLong(WEATHER_CACHE, System.currentTimeMillis());
        if (cacheExists(cache) && cacheIsNotExpired(cache_time)) {
            openWeather = new Gson().fromJson(cache, OpenWeather.class);
        } else {
            openWeather = new WeatherClient().getWeather(city);
            if (openWeather != null) {
                cache = new Gson().toJson(openWeather);
                Preferences.getInstance().setString(WEATHER, cache);
                Preferences.getInstance().setLong(WEATHER_CACHE, System.currentTimeMillis());
            }
        }
        return openWeather;
    }

    private boolean cacheExists(String cache) {
        return !TextUtils.isEmpty(cache);
    }

    private boolean cacheIsNotExpired(long cache_time) {
        return cache_time + WEATHER_EXPIRY_TIME >= System.currentTimeMillis();
    }
}
