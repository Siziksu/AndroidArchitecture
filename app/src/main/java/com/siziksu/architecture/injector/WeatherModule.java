package com.siziksu.architecture.injector;

import com.siziksu.architecture.data.client.weather.IWeatherClient;
import com.siziksu.architecture.data.client.weather.WeatherClient;
import com.siziksu.architecture.data.client.weather.WeatherClientAdapter;
import com.siziksu.architecture.data.weather.GetWeatherData;
import com.siziksu.architecture.data.weather.IGetWeatherData;
import com.siziksu.architecture.domain.weather.GetWeatherRequest;
import com.siziksu.architecture.domain.weather.IGetWeatherRequest;
import com.siziksu.architecture.presenter.weather.IWeatherPresenter;
import com.siziksu.architecture.presenter.weather.IWeatherView;
import com.siziksu.architecture.presenter.weather.WeatherPresenter;

/**
 * WeatherModule class.
 */
public class WeatherModule {

    private final IWeatherClient getIWeatherClient;

    private static WeatherModule instance;

    private WeatherModule() {
        getIWeatherClient = new WeatherClient(WeatherClientAdapter.get().getService());
    }

    /**
     * Gets an instance of the class.
     *
     * @return the WeatherModule injector
     */
    public static WeatherModule get() {
        if (instance == null) {
            instance = new WeatherModule();
        }
        return instance;
    }

    /**
     * Presenter for IGetWeatherRequest.
     *
     * @return IWeatherPresenter
     */
    public IWeatherPresenter<WeatherPresenter, IWeatherView> getWeather() {
        return new WeatherPresenter(getWeatherRequest());
    }

    private IGetWeatherRequest getWeatherRequest() {
        return new GetWeatherRequest(getWeatherData());
    }

    private IGetWeatherData getWeatherData() {
        return new GetWeatherData(getIWeatherClient);
    }
}
