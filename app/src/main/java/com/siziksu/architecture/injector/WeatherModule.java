package com.siziksu.architecture.injector;

import com.siziksu.architecture.data.client.weather.WeatherClient;
import com.siziksu.architecture.data.client.weather.WeatherClientAdapter;
import com.siziksu.architecture.data.weather.GetWeatherData;
import com.siziksu.architecture.domain.weather.GetWeatherRequest;
import com.siziksu.architecture.presenter.weather.WeatherPresenter;

/**
 * Weather class.
 */
public class WeatherModule {

    private final WeatherClient getWeatherClient;
    private final WeatherPresenter weatherPresenter;

    private static WeatherModule instance;

    private WeatherModule() {
        weatherPresenter = new WeatherPresenter();
        getWeatherClient = new WeatherClient(WeatherClientAdapter.get().getService());
    }

    /**
     * Gets an instance of the class.
     *
     * @return the Weather injector
     */
    public static WeatherModule get() {
        if (instance == null) {
            instance = new WeatherModule();
        }
        return instance;
    }

    /**
     * Presenter for GetWeatherRequest.
     *
     * @return WeatherPresenter
     */
    public WeatherPresenter getWeather() {
        return weatherPresenter.setGetWeatherRequest(getWeatherRequest());
    }

    private GetWeatherRequest getWeatherRequest() {
        return new GetWeatherRequest(getWeatherData());
    }

    private GetWeatherData getWeatherData() {
        return new GetWeatherData().setGetWeatherClient(getWeatherClient);
    }
}
