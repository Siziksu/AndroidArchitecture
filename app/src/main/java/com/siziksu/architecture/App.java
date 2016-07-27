package com.siziksu.architecture;

import android.app.Application;

import com.siziksu.architecture.common.ConnectionUtils;
import com.siziksu.architecture.common.FileUtils;
import com.siziksu.architecture.common.Injector;
import com.siziksu.architecture.common.Preferences;
import com.siziksu.architecture.ui.common.ActivityCommon;

/**
 * Application class.
 */
public class App extends Application {

    /**
     * Called when the application is starting.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Injector.init(getApplicationContext());
        ConnectionUtils.init(getApplicationContext());
        Preferences.init(getApplicationContext());
        FileUtils.init(getApplicationContext());
        ActivityCommon.init();
    }
}

