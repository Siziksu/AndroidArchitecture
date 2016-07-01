package com.siziksu.architecture.common;

import android.os.Handler;
import android.util.Log;

/**
 * Object used to easily create async calls.
 *
 * @param <O> the result type of the object
 */
public final class AsyncObject<O> {

    private Runnable runnable;
    private Handler handler;
    private boolean executing;
    private Action<O> action;
    private Success<O> success;
    private Error error;

    /**
     * Instantiates an {@code AsyncObject}
     */
    public AsyncObject() {
        // Constructor
    }

    /**
     * Sets the {@link Action} used to create the {@link Runnable} that will
     * be executed in a new {@link Thread} when the method {@link #execute()}
     * is called.
     *
     * @param action the Request that will be used
     */
    public AsyncObject<O> action(final Action<O> action) {
        this.action = action;
        return this;
    }

    /**
     * Sets the {@link Success} used to return the response of the
     * {@link Action} if ends successfully.
     *
     * @param success the Success that will be used
     */
    public AsyncObject<O> success(final Success<O> success) {
        this.success = success;
        return this;
    }

    /**
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the {@link Action} fails.
     *
     * @param error the Error that will be used
     */
    public AsyncObject<O> error(final Error error) {
        this.error = error;
        return this;
    }

    /**
     * Creates a {@link Runnable} using an {@link Action}.
     */
    private Runnable obtainRunnable() {
        if (runnable == null) {
            runnable = new Runnable() {

                @Override
                public void run() {
                    executing = true;
                    try {
                        O response = action.action();
                        if (success != null) {
                            onSuccess(response);
                        } else {
                            Log.d("AsyncObject", "Call success");
                        }
                    } catch (Exception e) {
                        if (error != null) {
                            onError(e);
                        } else {
                            Log.d("AsyncObject", e.getMessage(), e);
                        }
                    }
                    executing = false;
                    onDone();
                }
            };
        }
        return runnable;
    }

    private void onSuccess(final O response) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                success.success(response);
            }
        });
    }

    private void onError(final Exception e) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                error.error(e);
            }
        });
    }

    private void onDone() {
        handler.post(new Runnable() {

            @Override
            public void run() {
                action.done();
            }
        });
    }

    /**
     * Executes the {@link Action} into a new {@link Thread}.
     */
    public void execute() {
        if (action != null) {
            handler = new Handler();
            new Thread(obtainRunnable()).start();
        } else {
            throw new RuntimeException("There is no action to be executed");
        }
    }

    /**
     * Gets if the object is running.
     *
     * @return true if is running or false if it is not
     */
    public boolean isExecuting() {
        return executing;
    }

    /**
     * A task that returns a result and may throw an exception.
     * <br>It is designed ot be executed by another thread.
     * <br>It adds two methods used to return the result of the async request:
     * {@code action(O)} and {@code done()}.
     *
     * @param <O> the result type of method {@code request}
     */
    public interface Action<O> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         *
         * @throws Exception if unable to compute a result
         */
        O action() throws Exception;

        /**
         * Emits when the action is done.
         */
        void done();
    }

    /**
     * This task returns a response.
     */
    public interface Success<O> {

        /**
         * This method will be executed if the action ends successfully.
         *
         * @param response the response of the action
         */
        void success(O response);
    }

    /**
     * This task returns an {@link Exception}.
     */
    public interface Error {

        /**
         * This method will be executed if the action fails.
         *
         * @param e the exception returned
         */
        void error(Exception e);
    }
}
