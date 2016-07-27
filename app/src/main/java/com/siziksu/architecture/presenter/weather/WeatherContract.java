package com.siziksu.architecture.presenter.weather;

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
         * @param place       the place
         * @param temperature the temperature
         * @param currentTime the current time
         */
        void onWeather(String place, String temperature, String currentTime);

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
