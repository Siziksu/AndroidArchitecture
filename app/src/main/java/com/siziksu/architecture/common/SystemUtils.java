package com.siziksu.architecture.common;

import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

/**
 * System utils class.
 */
public class SystemUtils {

    /**
     * Gets current time.
     *
     * @return the current time
     */
    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        return (hour < 10 ? "0" + hour : hour)
               + ":" + (minutes < 10 ? "0" + minutes : minutes)
               + ":" + (seconds < 10 ? "0" + seconds : seconds);
    }

    /**
     * Print bundle.
     *
     * @param bundle the bundle
     */
    public static void printBundle(Bundle bundle) {
        for (String key : bundle.keySet()) {
            Log.d(Constants.TAG, key + ": " + bundle.get(key));
        }
    }

    /**
     * Pause.
     *
     * @param milliseconds the milliseconds
     */
    public static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Log.d(Constants.TAG, e.getMessage(), e);
        }
    }
}
