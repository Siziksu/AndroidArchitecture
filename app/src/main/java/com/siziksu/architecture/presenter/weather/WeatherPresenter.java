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
public class WeatherPresenter extends WeatherContract.WeatherPresenter<WeatherContract.WeatherView> {

    private boolean getWeatherRequestActive;

    /**
     * Constructor.
     */
    public WeatherPresenter() {
        // Constructor
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
                                        processResponse(response);
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

    private void processResponse(OpenWeather response) {
        String place = response.getName();
        String temperature = String.format(view.getActivity().getString(R.string.temperature), response.getMain().getTemperature());
        String currentTime = String.format(view.getActivity().getString(R.string.temperature_update_time), SystemUtils.getCurrentTime());
        view.onWeather(place, temperature, currentTime);
    }
}
