package com.siziksu.architecture.provider;

import com.google.gson.Gson;
import com.siziksu.architecture.provider.weather.Weather;

/**
 * Provider class.
 */
public class Provider {

    private static Provider instance;
    private static Gson gson;

    private Provider() {
        // Constructor
    }

    /**
     * Gets an instance of the class.
     *
     * @return the injector
     */
    public static Provider get() {
        if (instance == null) {
            instance = new Provider();
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
     * Provides the Weather object.
     *
     * @return Weather object
     */
    public Weather weather() {
        return Weather.get();
    }
}
