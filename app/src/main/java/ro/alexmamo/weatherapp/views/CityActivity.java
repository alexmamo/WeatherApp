package ro.alexmamo.weatherapp.views;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.contracts.CityActivityContract;
import ro.alexmamo.weatherapp.pojos.City;
import ro.alexmamo.weatherapp.presenters.CityActivityPresenter;

public class CityActivity extends AppCompatActivity implements CityActivityContract.View {
    private City city;
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
        CityActivityPresenter presenter = new CityActivityPresenter(city, this);
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
        dateTextView.setText(dayOfTheWeekAndCurrentDate);
        timeTextView.setText(time);
    }

    @Override
    public void setTemperatureTextView(String temp) {
        temperatureTextView.setText(temp);
    }

    @Override
    public void setMinAndMaxTextViews(String min, String max) {
        minTextView.setText(min);
        maxTextView.setText(max);
    }

    @Override
    public void setWeatherTextView(String weather) {
        weatherTextView.setText(weather);
    }

    @Override
    public void setWindTextView(String wind) {
        windTextView.setText(wind);
    }

    @Override
    public void setPressureTextView(String pressure) {
        pressureTextView.setText(pressure);
    }

    @Override
    public void setHumidityTextView(String humidity) {
        humidityTextView.setText(humidity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}