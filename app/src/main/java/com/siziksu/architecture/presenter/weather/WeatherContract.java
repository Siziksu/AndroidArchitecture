package com.siziksu.architecture.presenter.weather;

import com.siziksu.architecture.common.model.weather.response.WeatherResponse;
import com.siziksu.architecture.presenter.Presenter;
import com.siziksu.architecture.presenter.View;

/**
 * WeatherContract class. Contract between the view and its presenter.
 */
public interface WeatherContract {

    /**
     * View.
     */
    interface WeatherView extends View {

        /**
         * When we have the weather response.
         *
         * @param weatherResponse the response
         */
        void onWeather(WeatherResponse weatherResponse);

        /**
         * On weather error.
         */
        void onWeatherError();
    }

    /**
     * Presenter.
     */
    abstract class WeatherPresenter<T extends View> extends Presenter<T> {

        /**
         * Gets weather for a city.
         *
         * @param city the city
         */
        public abstract void getWeather(String city);
    }

}
