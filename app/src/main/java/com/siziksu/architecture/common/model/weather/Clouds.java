
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
     * Gets cloudiness.
     *
     * @return the cloudiness
     */
    public Integer getCloudiness() {
        return cloudiness;
    }

    /**
     * Sets cloudiness.
     *
     * @param cloudiness the cloudiness
     */
    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }

}
