package com.siziksu.architecture.common;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.siziksu.architecture.common.functions.Action;
import com.siziksu.architecture.common.functions.Done;
import com.siziksu.architecture.common.functions.Error;
import com.siziksu.architecture.common.functions.Success;

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
    private boolean subscribeOnMainThread;

    /**
     * Instantiates an {@code AsyncObject}
     */
    public AsyncObject() {
        // Constructor
    }

    /**
     * Subscribes the feedback functions on the main thread.
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> subscribeOnMainThread() {
        subscribeOnMainThread = true;
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
     * be executed in a new {@link Thread} when the method {@link #run()}
     * is called.
     *
     * @param action the Action function that will be used
     *
     * @return {@code AsyncObject}
     */
    public AsyncObject<O> action(final Action<O> action) {
        this.action = action;
        return this;
    }

    /**
     * Sets the {@link Success} used to return the response of the
     * {@link Action} if ends successfully.
     * <br />
     * And finally executes the {@link Action}.
     *
     * @param success the Success function that will be used
     */
    public void subscribe(final Success<O> success) {
        this.success = success;
        run();
    }

    /**
     * Sets the {@link Success} used to return the response of the
     * {@link Action} if ends successfully.
     * <br />
     * Sets the {@link Error} used to return {@link Exception} that will be
     * thrown if the {@link Action} fails.
     * <br />
     * And finally executes the {@link Action}.
     *
     * @param success the Success function that will be used
     * @param error   the Error function that will be used
     */
    public void subscribe(final Success<O> success, final Error error) {
        this.success = success;
        this.error = error;
        run();
    }

    /**
     * Sets the {@link Done} used to emit when the response of the
     * {@link Action} if ends.
     *
     * @param done the Done function that will be used
     */
    public AsyncObject<O> done(final Done done) {
        this.done = done;
        return this;
    }

    /**
     * Executes the {@link Action} into a new {@link Thread} and gives feedback
     * if any subscription is implemented.
     */
    public void run() {
        if (action != null) {
            if (subscribeOnMainThread) {
                handler = new Handler(Looper.getMainLooper());
            } else {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    handler = new Handler();
                }
            }
            Thread thread = new Thread(obtainRunnable());
            thread.setName("async-object-thread-" + thread.getId());
            thread.start();
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
                        success(response);
                    } else {
                        Log.d("AsyncObject", "Action successfully completed");
                    }
                } catch (Exception e) {
                    if (error != null) {
                        error(e);
                    } else {
                        Log.d("AsyncObject", e.getMessage(), e);
                    }
                }
                executing = false;
                if (done != null) {
                    done();
                }
            };
        }
        return runnable;
    }

    private void success(final O response) {
        if (handler != null) {
            handler.post(() -> success.success(response));
        } else {
            success.success(response);
        }
    }

    private void error(final Exception e) {
        if (handler != null) {
            handler.post(() -> error.error(e));
        } else {
            error.error(e);
        }
    }

    private void done() {
        if (handler != null) {
            handler.post(() -> done.done());
        } else {
            done.done();
        }
    }
}
