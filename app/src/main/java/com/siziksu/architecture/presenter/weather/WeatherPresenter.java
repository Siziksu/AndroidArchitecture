package com.siziksu.architecture.presenter.weather;

import android.util.Log;

import com.siziksu.architecture.R;
import com.siziksu.architecture.common.Constants;
import com.siziksu.architecture.common.SystemUtils;
import com.siziksu.architecture.common.model.weather.OpenWeather;
import com.siziksu.architecture.domain.WeatherDomain;

/**
 * WeatherPresenter class.
 */
public class WeatherPresenter implements WeatherContract.WeatherPresenter {

    private WeatherContract.WeatherView view;
    private WeatherDomain weatherDomain;

    private boolean getWeatherRequestActive;

    /**
     * Constructor.
     */
    public WeatherPresenter() {
        this.weatherDomain = WeatherDomain.getInstance();
    }

    @Override
    public void register(final WeatherContract.WeatherView view) {
        this.view = view;
    }

    @Override
    public void unregister() {
        this.view = null;
    }

    @Override
    public void getWeather(String city) {
        if (!getWeatherRequestActive) {
            if (view != null) {
                view.showProgress(true);
                weatherDomain.getWeather(city, new WeatherDomain.OnGetWeatherListener() {

                    @Override
                    public void onWeather(OpenWeather response) {
                        if (view != null) {
                            if (response != null) {
                                view.onWeather(
                                        response.getName(),
                                        String.format(view.getActivity().getString(R.string.temperature), response.getMain().getTemperature()),
                                        String.format(view.getActivity().getString(R.string.temperature_update_time), SystemUtils.getCurrentTime())
                                );
                            }
                            view.showProgress(false);
                        }
                        getWeatherRequestActive = false;
                    }

                    @Override
                    public void onError(Exception e) {
                        if (view != null) {
                            Log.d(Constants.TAG, e.getMessage(), e);
                            view.onWeatherError();
                            view.showProgress(false);
                        }
                        getWeatherRequestActive = false;
                    }
                });
            }
        }
        getWeatherRequestActive = true;
    }
}
