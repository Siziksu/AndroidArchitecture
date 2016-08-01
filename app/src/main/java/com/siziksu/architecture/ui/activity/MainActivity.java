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

import com.siziksu.architecture.App;
import com.siziksu.architecture.R;
import com.siziksu.architecture.common.model.weather.response.WeatherResponse;
import com.siziksu.architecture.presenter.weather.WeatherContract;
import com.siziksu.architecture.ui.common.ActivityCommon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity class.
 */
public class MainActivity extends AppCompatActivity implements WeatherContract.WeatherView {

    private static final String CITY = "Barcelona,Spain";

    private WeatherContract.WeatherPresenter<WeatherContract.WeatherView> presenter;

    @BindView(R.id.textViewPlace)
    TextView textViewPlace;
    @BindView(R.id.textViewTemperature)
    TextView textViewTemperature;
    @BindView(R.id.textViewUpdate)
    TextView textViewUpdate;
    @BindView(R.id.textViewError)
    TextView textViewError;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.defaultToolbar)
    Toolbar defaultToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = App.weatherModule().getWeather();
        ActivityCommon.get().applyToolBarStyleWithHome(this, defaultToolbar);
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
    public void onWeather(WeatherResponse weatherResponse) {
        textViewPlace.setText(weatherResponse.getPlace());
        textViewTemperature.setText(weatherResponse.getTemperature());
        textViewUpdate.setText(weatherResponse.getTime());
        textViewError.setVisibility(View.GONE);
    }

    @Override
    public void onWeatherError() {
        textViewError.setVisibility(View.VISIBLE);
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

