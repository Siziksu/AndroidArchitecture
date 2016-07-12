package com.siziksu.architecture.common;

import android.os.Handler;
import android.os.Looper;
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
    private Done done;
    private boolean runOnMainThread;

    /**
     * Instantiates an {@code AsyncObject}
     */
    public AsyncObject() {
        // Constructor
    }

    /**
     * Runs the specified action on the Main thread.
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> runOnMainThread() {
        runOnMainThread = true;
        return this;
    }

    /**
     * Gets if the object is running.
     *
     * @return true if it is running or false if it is not
     */
    public boolean isExecuting() {
        return executing;
    }

    /**
     * Sets the {@link Action} used to create the {@link Runnable} that will
     * be executed in a new {@link Thread} when the method {@link #execute()}
     * is called.
     *
     * @param action the Request that will be used
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> action(final Action<O> action) {
        this.action = action;
        return this;
    }

    /**
     * Sets the {@link Done} used to emit when the response of the
     * {@link Action} if ends.
     *
     * @param done the Done that will be used
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> done(final Done done) {
        this.done = done;
        return this;
    }

    /**
     * Sets the {@link Success} used to return the response of the
     * {@link Action} if ends successfully.
     *
     * @param success the Success that will be used
     *
     * @return {@code AsyncObject}
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
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> error(final Error error) {
        this.error = error;
        return this;
    }

    /**
     * Executes the {@link Action} into a new {@link Thread}.
     */
    public void execute() {
        if (action != null) {
            if (runOnMainThread) {
                handler = new Handler(Looper.getMainLooper());
            } else {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    handler = new Handler();
                }
            }
            new Thread(obtainRunnable()).start();
        } else {
            throw new RuntimeException("There is no action to be executed");
        }
    }

    /**
     * Creates a {@link Runnable} using an {@link Action}.
     */
    private Runnable obtainRunnable() {
        if (runnable == null) {
            runnable = () -> {
                executing = true;
                try {
                    O response = action.action();
                    if (success != null) {
                        onSuccess(response);
                    } else {
                        Log.d("AsyncObject", "Action successfully completed");
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
            };
        }
        return runnable;
    }

    private void onSuccess(final O response) {
        if (handler != null) {
            handler.post(() -> success.success(response));
        } else {
            success.success(response);
        }
    }

    private void onError(final Exception e) {
        if (handler != null) {
            handler.post(() -> error.error(e));
        } else {
            error.error(e);
        }
    }

    private void onDone() {
        if (handler != null) {
            handler.post(() -> done.done());
        } else {
            done.done();
        }
    }

    /**
     * A task that returns a result and may throw an exception.
     * <br>It is designed ot be executed by another thread.
     * <br>It returns the result of the async request through: {@code action(O)}.
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
    }

    /**
     * Task that emits when an action is done.
     */
    public interface Done {

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
