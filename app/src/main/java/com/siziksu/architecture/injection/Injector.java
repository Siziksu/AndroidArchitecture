package com.siziksu.architecture.injection;

import com.google.gson.Gson;
import com.siziksu.architecture.injection.weather.Weather;

/**
 * Injector class.
 */
public class Injector {

    private static Injector instance;
    private static Gson gson;

    private Injector() {
        // Constructor
    }

    /**
     * Gets an instance of the class.
     *
     * @return the injector
     */
    public static Injector get() {
        if (instance == null) {
            instance = new Injector();
        }
        return instance;
    }

    /**
     * Provides and instance of Gson.
     *
     * @return Gson gson
     */
    public Gson gson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    /**
     * Provides the GetWeather injector.
     *
     * @return GetWeather injector
     */
    public Weather Weather() {
        return Weather.get();
    }
}
