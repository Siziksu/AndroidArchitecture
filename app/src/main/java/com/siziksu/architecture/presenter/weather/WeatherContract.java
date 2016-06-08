package com.siziksu.architecture.presenter.weather;

import android.app.Activity;

/**
 * Contract between the view and its presenter.
 */
public interface WeatherContract {

    /**
     * View holding the login process
     */
    interface WeatherView {

        /**
         * Returns the activity in which the view is hold.
         *
         * @return the activity
         */
        Activity getActivity();

        /**
         * Launched when login has finished.
         *
         * @param place       the place
         * @param temperature the temperature
         * @param currentTime the current time
         */
        void onWeather(String place, String temperature, String currentTime);

        /**
         * Show progress.
         *
         * @param value the value
         */
        void showProgress(boolean value);

        /**
         * On weather error.
         */
        void onWeatherError();
    }

    /**
     * Presenter which manages the login view
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
