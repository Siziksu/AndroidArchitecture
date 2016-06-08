package com.siziksu.architecture.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.siziksu.architecture.R;
import com.siziksu.architecture.presenter.weather.WeatherContract;
import com.siziksu.architecture.presenter.weather.WeatherPresenter;
import com.siziksu.architecture.ui.common.ActivityCommon;

/**
 * MainActivity class.
 */
public class MainActivity extends AppCompatActivity implements WeatherContract.WeatherView {

    private static final String CITY = "Barcelona,Spain";

    private WeatherPresenter presenter;

    private TextView place;
    private TextView temperature;
    private TextView update;
    private TextView error;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new WeatherPresenter();
        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.getInstance().applyToolBarStyleWithHome(this, defaultToolbar);
        place = (TextView) findViewById(R.id.place);
        temperature = (TextView) findViewById(R.id.temperature);
        update = (TextView) findViewById(R.id.update);
        error = (TextView) findViewById(R.id.error);
        progress = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register(this);
        presenter.getWeather(CITY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onWeather(String place, String temperature, String currentTime) {
        this.place.setText(place);
        this.temperature.setText(temperature);
        update.setText(currentTime);
        error.setVisibility(View.GONE);
    }

    @Override
    public void onWeatherError() {
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean value) {
        progress.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                presenter.getWeather(CITY);
                break;
            default:
                break;
        }
        return false;
    }
}

