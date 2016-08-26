package com.siziksu.architecture.presenter.weather;

import com.siziksu.architecture.presenter.Presenter;
import com.siziksu.architecture.presenter.View;

/**
 * IWeatherPresenter class.
 *
 * @param <T> the type parameter
 * @param <V> the type parameter
 */
public abstract class IWeatherPresenter<T extends IWeatherPresenter<T, V>, V extends View> extends Presenter<V> {

    /**
     * Gets weather for a city.
     *
     * @param city the city
     */
    public abstract void getWeather(String city);
}
