package com.siziksu.architecture.common.functions;

/**
 * Function that returns a {@link Throwable}.
 */
public interface Fail {

    /**
     * This method will be executed if the action fails.
     *
     * @param e the throwable returned
     */
    void fail(Throwable e);
}
