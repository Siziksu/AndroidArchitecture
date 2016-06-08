
package com.siziksu.architecture.common.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Wind.
 */
public class Wind {

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Double directionDegrees;

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * Gets deg.
     *
     * @return the direction degrees
     */
    public Double getDirectionDegrees() {
        return directionDegrees;
    }

    /**
     * Sets deg.
     *
     * @param directionDegrees the direction degrees
     */
    public void setDirectionDegrees(Double directionDegrees) {
        this.directionDegrees = directionDegrees;
    }

}
