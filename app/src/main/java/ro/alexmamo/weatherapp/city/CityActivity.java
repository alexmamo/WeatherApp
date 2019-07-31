package ro.alexmamo.weatherapp.city;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.Module;
import dagger.android.support.DaggerAppCompatActivity;
import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.cities.models.City;
import ro.alexmamo.weatherapp.city.models.CurrentWeather;

@Module
public class CityActivity extends DaggerAppCompatActivity implements Observer<CurrentWeather> {
    @Inject CurrentWeatherViewModelFactory factory;
    private TextView dateTextView, timeTextView, temperatureTextView, minTextView, maxTextView,
            weatherTextView, windTextView, pressureTextView, humidityTextView;
    private City city;
    private CurrentWeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        enableHomeButton();
        city = getCityFromIntent();
        setTitle(city.cityName);
        initViews();
        addCitiesToList();
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

    public void addCitiesToList() {
        viewModel = ViewModelProviders.of(this, factory).get(CurrentWeatherViewModel.class);
        viewModel.setCity(city);
        LiveData<CurrentWeather> currentWeatherLiveData = viewModel.getCurrentWeatherLiveData();
        currentWeatherLiveData.observe(this, this);
    }

    @Override
    public void onChanged(@Nullable CurrentWeather currentWeather) {
        if (currentWeather != null) {
            setDataToViews(currentWeather);
        }
    }

    private void setDataToViews(CurrentWeather currentWeather) {
        CurrentWeatherRepository repository = viewModel.getRepository();

        String[] dateAndTime = repository.getDateAndTime();
        String dayOfTheWeekAndCurrentDate = dateAndTime[0];
        setDateTextViews(dayOfTheWeekAndCurrentDate);
        String time = dateAndTime[1];
        setTimeTextViews(time);

        String temp = repository.getTemperature(currentWeather);
        setTemperatureTextView(temp);

        String min = repository.getMin(currentWeather);
        setMinTextViews(min);

        String max = repository.getMax(currentWeather);
        setMaxTextViews(max);

        String weather = repository.getWeather(currentWeather);
        setWeatherTextView(weather);

        String wind = repository.getWind(currentWeather);
        setWindTextView(wind);

        String pressure = repository.getPressure(currentWeather);
        setPressureTextView(pressure);

        String humidity = repository.getHumidity(currentWeather);
        setHumidityTextView(humidity);
    }

    public void setDateTextViews(String dayOfTheWeekAndCurrentDate) {
        dateTextView.setText(dayOfTheWeekAndCurrentDate);
    }

    public void setTimeTextViews(String time) {
        timeTextView.setText(time);
    }

    public void setTemperatureTextView(String temp) {
        temperatureTextView.setText(temp);
    }

    public void setMinTextViews(String min) {
        minTextView.setText(min);
    }

    public void setMaxTextViews(String max) {
        maxTextView.setText(max);
    }

    public void setWeatherTextView(String weather) {
        weatherTextView.setText(weather);
    }

    public void setWindTextView(String wind) {
        windTextView.setText(wind);
    }

    public void setPressureTextView(String pressure) {
        pressureTextView.setText(pressure);
    }

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