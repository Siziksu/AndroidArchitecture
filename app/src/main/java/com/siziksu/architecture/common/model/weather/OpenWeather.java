
package com.siziksu.architecture.common.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenWeather class.
 */
public class OpenWeather {

    @SerializedName("coord")
    @Expose
    private Coordinates coordinates;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Long dataTime;
    @SerializedName("sys")
    @Expose
    private System system;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets weather.
     *
     * @return the weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * Sets weather.
     *
     * @param weather the weather
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * Gets base.
     *
     * @return the base
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets base.
     *
     * @param base the base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Gets main.
     *
     * @return the main
     */
    public Main getMain() {
        return main;
    }

    /**
     * Sets main.
     *
     * @param main the main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Gets wind.
     *
     * @return the wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets wind.
     *
     * @param wind the wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * Gets clouds.
     *
     * @return the clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * Sets clouds.
     *
     * @param clouds the clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * Gets dt.
     *
     * @return the data time
     */
    public Long getDataTime() {
        return dataTime;
    }

    /**
     * Sets dt.
     *
     * @param dataTime the data time
     */
    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * Gets system.
     *
     * @return the system
     */
    public System getSystem() {
        return system;
    }

    /**
     * Sets system.
     *
     * @param system the system
     */
    public void setSystem(System system) {
        this.system = system;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets cod.
     *
     * @return the cod
     */
    public Integer getCod() {
        return cod;
    }

    /**
     * Sets cod.
     *
     * @param cod the cod
     */
    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
