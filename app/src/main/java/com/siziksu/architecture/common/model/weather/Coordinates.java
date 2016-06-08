
package com.siziksu.architecture.common.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Coordinates.
 */
public class Coordinates {

    @SerializedName("lon")
    @Expose
    private Double longitude;
    @SerializedName("lat")
    @Expose
    private Double latitude;

    /**
     * Gets lon.
     *
     * @return the lon
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets lon.
     *
     * @param longitude the lon
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets lat.
     *
     * @return the lat
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets lat.
     *
     * @param latitude the lat
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
