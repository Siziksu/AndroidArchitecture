
package com.siziksu.architecture.common.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Main class.
 */
public class Main {

    @SerializedName("temp")
    @Expose
    private Double temperature;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;

    /**
     * Gets temp.
     *
     * @return the temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * Sets temp.
     *
     * @param temperature the temperature
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets pressure.
     *
     * @return the pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * Sets pressure.
     *
     * @param pressure the pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets humidity.
     *
     * @return the humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     * Sets humidity.
     *
     * @param humidity the humidity
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets temp min.
     *
     * @return the temp min
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * Sets temp min.
     *
     * @param tempMin the temp min
     */
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * Gets temp max.
     *
     * @return the temp max
     */
    public Double getTempMax() {
        return tempMax;
    }

    /**
     * Sets temp max.
     *
     * @param tempMax the temp max
     */
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

}
