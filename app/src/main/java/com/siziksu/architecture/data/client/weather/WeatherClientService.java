package com.siziksu.architecture.data.client.weather;

import com.siziksu.architecture.common.model.weather.OpenWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * WeatherClientService class.
 */
public interface WeatherClientService {

    /**
     * Gets weather.
     *
     * @param city   the city
     * @param apiKey the api key
     * @param units  the units
     *
     * @return a {@link OpenWeather} object
     */
    @GET(WeatherClientAdapter.URI)
    Call<OpenWeather> getWeather(@Query("q") String city, @Query("apikey") String apiKey, @Query("units") String units);
}
