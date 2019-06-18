package ro.alexmamo.weatherapp.presentation.city.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.presentation.city.CityActivityContract;
import ro.alexmamo.weatherapp.presentation.city.pojo.City;
import ro.alexmamo.weatherapp.presentation.city.presenter.CityActivityPresenter;

public class CityActivity extends AppCompatActivity implements CityActivityContract.View {
    private City city;
    private CityActivityPresenter presenter;
    private TextView dateTextView, timeTextView, temperatureTextView, minTextView, maxTextView, weatherTextView, windTextView, pressureTextView, humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        enableHomeButton();
        city = getCityFromIntent();
        setTitle(city.cityName);
        initCityActivityPresenter();
    }

    private void enableHomeButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private City getCityFromIntent() {
        return (City) getIntent().getSerializableExtra("city");
    }

    private void initCityActivityPresenter() {
        presenter = new CityActivityPresenter(city);
        presenter.attachView(this);
        presenter.setWeatherDataToViews();
    }

    @Override
    public void initViews() {
        dateTextView = findViewById(R.id.date_text_view);
        timeTextView = findViewById(R.id.time_text_view);
        temperatureTextView = findViewById(R.id.temperature_text_view);
        minTextView = findViewById(R.id.min_text_view);
        maxTextView = findViewById(R.id.max_text_view);
        weatherTextView = findViewById(R.id.weather_text_view);
        windTextView = findViewById(R.id.wind_text_view);
        pressureTextView = findViewById(R.id.pressure_text_view);
        humidityTextView = findViewById(R.id.humidity_text_view);
    }

    @Override
    public void setDateAndTimeTextViews(String dayOfTheWeekAndCurrentDate, String time) {
        if (presenter.isViewAttached()) {
            dateTextView.setText(dayOfTheWeekAndCurrentDate);
            timeTextView.setText(time);
        }
    }

    @Override
    public void setTemperatureTextView(String temp) {
        if (presenter.isViewAttached()) {
            temperatureTextView.setText(temp);
        }
    }

    @Override
    public void setMinAndMaxTextViews(String min, String max) {
        if (presenter.isViewAttached()) {
            minTextView.setText(min);
            maxTextView.setText(max);
        }
    }

    @Override
    public void setWeatherTextView(String weather) {
        if (presenter.isViewAttached()) {
            weatherTextView.setText(weather);
        }
    }

    @Override
    public void setWindTextView(String wind) {
        if (presenter.isViewAttached()) {
            windTextView.setText(wind);
        }
    }

    @Override
    public void setPressureTextView(String pressure) {
        if (presenter.isViewAttached()) {
            pressureTextView.setText(pressure);
        }
    }

    @Override
    public void setHumidityTextView(String humidity) {
        if (presenter.isViewAttached()) {
            humidityTextView.setText(humidity);
        }
    }

    @Override
    public void toastError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}