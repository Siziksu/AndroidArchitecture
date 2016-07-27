package com.siziksu.architecture.presenter;

/**
 * Presenter class.
 *
 * @param <T> view
 */
public abstract class Presenter<T extends View> {

    protected T view;

    /**
     * Registers the view.
     *
     * @param view The view to register
     */
    public void register(final T view) {
        this.view = view;
    }

    /**
     * Unregisters the view.
     */
    public void unregister() {
        this.view = null;
    }
}
