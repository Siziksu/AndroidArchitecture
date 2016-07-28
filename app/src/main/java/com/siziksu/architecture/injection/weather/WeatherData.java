package com.siziksu.architecture.injection.weather;

import com.siziksu.architecture.data.weather.GetWeatherData;

public class WeatherData {

    public WeatherData() {
        // Constructor
    }

    public GetWeatherData getWeather() {
        return new GetWeatherData();
    }
}
