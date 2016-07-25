package com.siziksu.architecture.presenter.weather;

import android.util.Log;

import com.siziksu.architecture.R;
import com.siziksu.architecture.common.Constants;
import com.siziksu.architecture.common.SystemUtils;
import com.siziksu.architecture.domain.WeatherDomain;

/**
 * WeatherPresenter class.
 */
public class WeatherPresenter implements WeatherContract.WeatherPresenter {

    private WeatherContract.WeatherView view;

    private boolean getWeatherRequestActive;

    /**
     * Constructor.
     */
    public WeatherPresenter() {
        // Constructor
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
                getWeatherRequestActive = true;
                view.showProgress(true);
                new WeatherDomain.Weather()
                        .city(city)
                        .subscribe(
                                response -> {
                                    if (view != null && response != null) {
                                        view.onWeather(
                                                response.getName(),
                                                String.format(view.getActivity().getString(R.string.temperature), response.getMain().getTemperature()),
                                                String.format(view.getActivity().getString(R.string.temperature_update_time), SystemUtils.getCurrentTime())
                                        );
                                    }
                                },
                                throwable -> {
                                    if (view != null) {
                                        Log.d(Constants.TAG, throwable.getMessage(), throwable);
                                        view.onWeatherError();
                                    }
                                },
                                () -> {
                                    if (view != null) {
                                        view.showProgress(false);
                                    }
                                    getWeatherRequestActive = false;
                                });
            }
        }
    }
}
