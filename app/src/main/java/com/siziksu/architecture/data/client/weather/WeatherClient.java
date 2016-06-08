package com.siziksu.architecture.data.client.weather;

import android.util.Log;

import com.siziksu.architecture.common.Constants;
import com.siziksu.architecture.common.model.weather.OpenWeather;

import java.io.IOException;

import retrofit2.Call;

/**
 * Client class.
 */
public class WeatherClient {

    private WeatherClientService service = WeatherClientAdapter.getInstance().getService();

    /**
     * Gets the {@link OpenWeather}.
     *
     * @return a {@link OpenWeather} object
     */
    public OpenWeather getWeather(String city) {
        try {
            Call<OpenWeather> call = service.getWeather(city, WeatherClientAdapter.WEATHER_API_KEY, WeatherClientAdapter.UNITS);
            OpenWeather openWeather = call.execute().body();
            if (openWeather != null) {
                return openWeather;
            } else {
                throw new RuntimeException("Retrofit call returned a null value");
            }
        } catch (IOException e) {
            Log.d(Constants.TAG, e.getMessage(), e);
        }
        throw new RuntimeException("Unknown error for the retrofit call");
    }
}
