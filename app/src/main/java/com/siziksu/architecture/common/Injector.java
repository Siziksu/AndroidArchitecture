package com.siziksu.architecture.common;

import android.content.Context;

import com.google.gson.Gson;
import com.siziksu.architecture.data.WeatherData;
import com.siziksu.architecture.data.client.weather.WeatherClient;
import com.siziksu.architecture.domain.WeatherDomain;
import com.siziksu.architecture.domain.weather.GetWeatherRequest;
import com.siziksu.architecture.presenter.weather.WeatherPresenter;

/**
 * Injector class.
 */
public class Injector {

    /**
     * The constant context.
     */
    public static Context context;

    /**
     * This class must be initialized before asking for an instance.
     *
     * @param context the context
     */
    public static void init(Context context) {
        Injector.context = context;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public static Context getContext() {
        return Injector.context;
    }

    /**
     * Provides and instance of Gson.
     *
     * @return Gson gson
     */
    public static Gson provideGson() {
        return new Gson();
    }

    /**
     * Provides the junction between the Activity and his Presenter.
     *
     * @return WeatherPresenter weather presenter
     */
    public static WeatherPresenter provideWeatherPresenter() {
        return new WeatherPresenter();
    }

    /**
     * Provides the junction between the Domain and the Presenter.
     *
     * @return WeatherDomain.GetWeather weather domain . get weather
     */
    public static WeatherDomain.GetWeather provideGetWeatherDomain() {
        return new WeatherDomain.GetWeather();
    }

    /**
     * Provides the junction between the Request and his Domain.
     *
     * @return GetWeatherRequest get weather request
     */
    public static GetWeatherRequest provideGetWeatherRequest() {
        return new GetWeatherRequest();
    }

    /**
     * Provides the junction between the Data and his Request.
     *
     * @return WeatherData.GetWeather weather data . get weather
     */
    public static WeatherData.GetWeather provideGetWeatherData() {
        return new WeatherData.GetWeather();
    }

    /**
     * Provides the junction between the Client and his Data.
     *
     * @return WeatherClient weather client
     */
    public static WeatherClient provideWeatherClient() {
        return new WeatherClient();
    }
}
