package com.siziksu.architecture.injector;

import com.siziksu.architecture.data.client.weather.WeatherClient;
import com.siziksu.architecture.data.client.weather.WeatherClientAdapter;
import com.siziksu.architecture.data.client.weather.WeatherClientService;
import com.siziksu.architecture.data.weather.GetWeatherData;
import com.siziksu.architecture.domain.weather.GetWeatherRequest;
import com.siziksu.architecture.presenter.weather.WeatherPresenter;

/**
 * Weather class.
 */
public class Weather {

    private final WeatherClient getWeatherClient;
    private final WeatherPresenter weatherPresenter;

    private static Weather instance;

    private Weather() {
        weatherPresenter = new WeatherPresenter();
        WeatherClientService service = WeatherClientAdapter.get().getService();
        getWeatherClient = new WeatherClient(service);
    }

    /**
     * Gets an instance of the class.
     *
     * @return the Weather injector
     */
    public static Weather get() {
        if (instance == null) {
            instance = new Weather();
        }
        return instance;
    }

    /**
     * Presenter.
     *
     * @return WeatherPresenter
     */
    public WeatherPresenter getWeather() {
        GetWeatherData getWeatherData = new GetWeatherData().setGetWeatherClient(getWeatherClient);
        GetWeatherRequest getWeatherRequest = new GetWeatherRequest(getWeatherData);
        weatherPresenter.setGetWeatherRequest(getWeatherRequest);
        return weatherPresenter;
    }
}
