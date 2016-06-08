package com.siziksu.architecture.common;

import android.os.Handler;

import java.security.SecureRandom;

/**
 * Object used to easily create async calls.
 * <br>It uses a {@link Request} object.
 *
 * @param <O> the result type of the object
 */
public final class AsyncObject<O> {

    private final long id;
    private Runnable runnable;
    private Request<O> request;
    private Handler handler;
    private boolean executing;
    private boolean silent;

    /**
     * Instantiates an {@code AsyncObject} and sets the {@link Request}
     * used to create the {@link Runnable} that will be executed in a new {@link Thread}
     * when the method {@link #execute()} is called.
     *
     * @param request the Request that will be used
     */
    public AsyncObject(final Request<O> request) {
        SecureRandom random = new SecureRandom();
        this.request = request;
        this.id = random.nextInt();
    }

    /**
     * Creates a {@link Runnable} using a {@link Request}.
     */
    private Runnable obtainRunnable() {
        if (runnable == null) {
            runnable = new Runnable() {

                @Override
                public void run() {
                    executing = true;
                    try {
                        onSuccess(request.request());
                    } catch (Exception e) {
                        onError(e);
                    }
                    executing = false;
                }
            };
        }
        return runnable;
    }

    private void onSuccess(final O response) {
        if (!silent) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    request.onSuccess(id, response);
                }
            });
        }
    }

    private void onError(final Exception e) {
        if (!silent) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    request.onError(id, e);
                }
            });
        }
    }

    /**
     * Executes the {@link Request} into a new {@link Thread}.
     */
    public void execute() {
        handler = new Handler();
        new Thread(obtainRunnable()).start();
    }

    /**
     * Gets the identifier of the call
     *
     * @return the id, should be unique
     */
    public long getId() {
        return id;
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
     * Creates an object that won't use the callback.
     *
     * @return an {@code AsyncObject}
     */
    public AsyncObject<O> silent() {
        silent = true;
        return this;
    }

    /**
     * A task that returns a result and may throw an exception.
     * <br>It is designed for classes whose instances are potentially executed by another thread.
     * <br>It adds two methods used to return the result of an async request:
     * {@code onSuccess(O)} and {@code onError(Exception)}.
     *
     * @param <O> the result type of method {@code request}
     */
    public interface Request<O> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        O request() throws Exception;

        /**
         * This method will be executed if the request ends successfully.
         *
         * @param id       the id of the request
         * @param response the response of the request
         */
        void onSuccess(long id, O response);

        /**
         * This method will be executed if the request fails.
         *
         * @param id the id of the request
         * @param e  the exception returned
         */
        void onError(long id, Exception e);
    }
}
