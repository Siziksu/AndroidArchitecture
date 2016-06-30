
package com.siziksu.architecture.common.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Coordinates class.
 */
public class Coordinates {

    @SerializedName("lon")
    @Expose
    private Double longitude;
    @SerializedName("lat")
    @Expose
    private Double latitude;

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
