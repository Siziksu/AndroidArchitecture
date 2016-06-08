
package com.siziksu.architecture.common.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Clouds class.
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer cloudiness;

    /**
     * Gets all.
     *
     * @return the cloudiness
     */
    public Integer getCloudiness() {
        return cloudiness;
    }

    /**
     * Sets all.
     *
     * @param cloudiness the cloudiness
     */
    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }

}
