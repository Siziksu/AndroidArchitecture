package com.siziksu.architecture.domain.weather;

import android.util.Log;

import com.google.gson.Gson;
import com.siziksu.architecture.common.Constants;
import com.siziksu.architecture.common.FileUtils;
import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Fail;
import com.siziksu.architecture.common.functions.Success;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.data.weather.GetWeatherData;

/**
 * Request class.
 */
public final class GetWeatherRequest {

    private final GetWeatherData getWeatherData;

    /**
     * Constructor.
     */
    public GetWeatherRequest(GetWeatherData getWeatherData) {
        this.getWeatherData = getWeatherData;
    }

    public GetWeatherRequest city(String city) {
        // No need for the mock
        return this;
    }

    /**
     * Mocked subscribe.
     *
     * @param success the success function
     * @param fail    the fail function
     * @param done    the done function
     */
    public void subscribe(final Success<OpenWeather> success, final Fail fail, final Done done) {
        Log.d(Constants.TAG, "GetWeather mocked response");
        String response = FileUtils.get().getStringFromFile("get_weather/response.json");
        OpenWeather openWeather = new Gson().fromJson(response, OpenWeather.class);
        success.success(openWeather);
//        fail.fail(new Throwable("GetWeather mocked error"));
        done.done();
    }
}
