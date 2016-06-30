package com.siziksu.architecture.data.client.weather;

import com.siziksu.architecture.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WeatherClientAdapter class.
 */
public final class WeatherClientAdapter {

    public static final String SERVER = "http://api.openweathermap.org/";
    public static final String URI = "data/2.5/weather";

    public static final String WEATHER_API_KEY = "3db09a5921e4199f2cc3c80cc5e07b36";
    public static final String UNITS = "metric";

    private static WeatherClientAdapter instance;
    private WeatherClientService service;

    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private WeatherClientAdapter() {
        // Private constructor
    }

    /**
     * Gets and instance of the object.
     *
     * @return {@link WeatherClientAdapter} object
     */
    public static WeatherClientAdapter getInstance() {
        if (instance == null) {
            instance = new WeatherClientAdapter();
        }
        return instance;
    }

    /**
     * Gets and instance of the {@link WeatherClientService}.
     *
     * @return {@link WeatherClientService} object
     */
    public WeatherClientService getService() {
        if (service == null) {
            service = getBuilder().addConverterFactory(GsonConverterFactory.create()).build().create(WeatherClientService.class);
        }
        return service;
    }

    private Retrofit.Builder getBuilder() {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit.Builder builder;
        if (BuildConfig.DEBUG) {
            builder = new Retrofit.Builder().baseUrl(SERVER).client(client);
        } else {
            builder = new Retrofit.Builder().baseUrl(SERVER);
        }

        return builder;
    }
}
