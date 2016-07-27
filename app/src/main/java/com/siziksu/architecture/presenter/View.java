package com.siziksu.architecture.presenter;

import android.app.Activity;

public interface View {

    /**
     * Returns the activity in which the view is hold.
     *
     * @return the activity
     */
    Activity getActivity();

    /**
     * Show progress.
     *
     * @param value show or not (true or false)
     */
    void showProgress(boolean value);
}
