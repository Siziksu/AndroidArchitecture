package com.siziksu.architecture.presenter.weather;

import android.app.Activity;

/**
 * WeatherContract class. Contract between the view and its presenter.
 */
public interface WeatherContract {

    /**
     * View.
     */
    interface WeatherView {

        /**
         * Returns the activity in which the view is hold.
         *
         * @return the activity
         */
        Activity getActivity();

        /**
         * Show progress.
         *
         * @param value the value
         */
        void showProgress(boolean value);

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
    interface WeatherPresenter {

        /**
         * Registers the view.
         *
         * @param view The view to register
         */
        void register(WeatherView view);

        /**
         * Unregisters the view.
         */
        void unregister();

        /**
         * Gets weather for a city.
         *
         * @param city the city
         */
        void getWeather(String city);
    }

}
