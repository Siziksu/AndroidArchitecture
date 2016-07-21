package com.siziksu.architecture.data;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.siziksu.architecture.common.Preferences;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.client.weather.WeatherClient;

/**
 * WeatherData class.
 */
public final class WeatherData {

    private static final String WEATHER_CACHE = "weather_cache";
    private static final String WEATHER_CACHE_TIME = "weather_cache_time";
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
        String cache = Preferences.getInstance().getString(WEATHER_CACHE, null);
        long cacheTime = Preferences.getInstance().getLong(WEATHER_CACHE_TIME, System.currentTimeMillis());
        if (cacheExists(cache) && cacheIsNotExpired(WEATHER_EXPIRY_TIME, cacheTime)) {
            openWeather = new Gson().fromJson(cache, OpenWeather.class);
        } else {
            openWeather = new WeatherClient().getWeather(city);
            if (openWeather != null) {
                cache = new Gson().toJson(openWeather);
                Preferences.getInstance().setString(WEATHER_CACHE, cache);
                Preferences.getInstance().setLong(WEATHER_CACHE_TIME, System.currentTimeMillis());
            }
        }
        return openWeather;
    }

    private boolean cacheExists(@NonNull String cache) {
        return !TextUtils.isEmpty(cache);
    }

    private boolean cacheIsNotExpired(long expireTime, long cacheTime) {
        return cacheTime + expireTime >= System.currentTimeMillis();
    }
}
