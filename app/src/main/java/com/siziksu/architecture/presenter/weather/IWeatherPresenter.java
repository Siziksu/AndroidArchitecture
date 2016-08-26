package com.siziksu.architecture.presenter.weather;

import com.siziksu.architecture.presenter.IView;

/**
 * IWeatherPresenter class.
 *
 * @param <V> the type parameter
 */
public abstract class IWeatherPresenter<V extends IView> {

    protected V view;

    public void register(final V view) {
        this.view = view;
    }

    public void unregister() {
        this.view = null;
    }

    /**
     * Gets weather for a city.
     *
     * @param city the city
     */
    public abstract void getWeather(String city);
}
