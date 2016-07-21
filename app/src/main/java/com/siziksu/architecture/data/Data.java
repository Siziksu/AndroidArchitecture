package com.siziksu.architecture.data;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.siziksu.architecture.common.Preferences;

/**
 * Data class.
 */
abstract class Data {

    private static final String CACHE_TIME_SUFFIX = "_time";

    /**
     * Gets the cache value of a key.
     *
     * @param cacheKey the cache key
     *
     * @return the cache
     */
    protected String getCache(String cacheKey) {
        return Preferences.getInstance().getString(cacheKey, null);
    }

    /**
     * Sets the cache value of a key.
     *
     * @param cacheKey the cache key
     * @param cache    the cache
     */
    protected void setCache(String cacheKey, String cache) {
        Preferences.getInstance().setString(cacheKey, cache);
        Preferences.getInstance().setLong(cacheKey + CACHE_TIME_SUFFIX, System.currentTimeMillis());
    }

    /**
     * Returns if a cache key exists.
     *
     * @param cache the cache
     *
     * @return the boolean
     */
    protected boolean cacheExists(@NonNull String cache) {
        return !TextUtils.isEmpty(cache);
    }

    /**
     * Returns if the cache is not expired.
     *
     * @param cacheKey        the cache key
     * @param cacheExpiryTime the cache expiry time
     *
     * @return the boolean
     */
    protected boolean cacheIsNotExpired(String cacheKey, long cacheExpiryTime) {
        long cacheTime = Preferences.getInstance().getLong(cacheKey + CACHE_TIME_SUFFIX, System.currentTimeMillis());
        return cacheTime + cacheExpiryTime >= System.currentTimeMillis();
    }

    /**
     * Returns if the cache is valid.
     *
     * @param cache           the cache
     * @param cacheKey        the cache key
     * @param cacheExpiryTime the cache expiry time
     *
     * @return the boolean
     */
    protected boolean isCacheValid(String cache, String cacheKey, long cacheExpiryTime) {
        return cacheExists(cache) && cacheIsNotExpired(cacheKey, cacheExpiryTime);
    }
}
