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

    private TextView textViewPlace;
    private TextView textViewTemperature;
    private TextView textViewUpdate;
    private TextView TextViewError;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new WeatherPresenter();
        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.getInstance().applyToolBarStyleWithHome(this, defaultToolbar);
        textViewPlace = (TextView) findViewById(R.id.textViewPlace);
        textViewTemperature = (TextView) findViewById(R.id.textViewTemperature);
        textViewUpdate = (TextView) findViewById(R.id.textViewUpdate);
        TextViewError = (TextView) findViewById(R.id.TextViewError);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register(this);
        presenter.getWeather(CITY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onWeather(String place, String temperature, String currentTime) {
        this.textViewPlace.setText(place);
        this.textViewTemperature.setText(temperature);
        textViewUpdate.setText(currentTime);
        TextViewError.setVisibility(View.GONE);
    }

    @Override
    public void onWeatherError() {
        TextViewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean value) {
        progressBar.setVisibility(value ? View.VISIBLE : View.GONE);
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

