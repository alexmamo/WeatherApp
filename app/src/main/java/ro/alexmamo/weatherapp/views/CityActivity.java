package ro.alexmamo.weatherapp.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import ro.alexmamo.weatherapp.factories.CurrentWeatherViewModelFactory;
import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.models.City;
import ro.alexmamo.weatherapp.models.CurrentWeather;
import ro.alexmamo.weatherapp.viewmodels.CurrentWeatherViewModel;

public class CityActivity extends AppCompatActivity {
    private TextView dateTextView, timeTextView, temperatureTextView, minTextView, maxTextView, weatherTextView, windTextView, pressureTextView, humidityTextView;
    private City city;
    private CurrentWeatherViewModel currentWeatherViewModel;

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
        CurrentWeatherViewModelFactory factory = new CurrentWeatherViewModelFactory(this.getApplication(), city);
        currentWeatherViewModel = ViewModelProviders.of(this, factory).get(CurrentWeatherViewModel.class);
        LiveData<CurrentWeather> currentWeatherLiveData = currentWeatherViewModel.getCurrentWeatherLiveData();
        currentWeatherLiveData.observe(this, currentWeather -> {
            if (currentWeather != null) {
                setDataToViews(currentWeather);
            }
        });
    }

    private void setDataToViews(CurrentWeather currentWeather) {
        String[] dateAndTime = currentWeatherViewModel.getDateAndTime();
        String dayOfTheWeekAndCurrentDate = dateAndTime[0];
        setDateTextViews(dayOfTheWeekAndCurrentDate);
        String time = dateAndTime[1];
        setTimeTextViews(time);

        String temp = currentWeatherViewModel.getTemperature(currentWeather);
        setTemperatureTextView(temp);

        String min = currentWeatherViewModel.getMin(currentWeather);
        setMinTextViews(min);

        String max = currentWeatherViewModel.getMax(currentWeather);
        setMaxTextViews(max);

        String weather = currentWeatherViewModel.getWeather(currentWeather);
        setWeatherTextView(weather);

        String wind = currentWeatherViewModel.getWind(currentWeather);
        setWindTextView(wind);

        String pressure = currentWeatherViewModel.getPressure(currentWeather);
        setPressureTextView(pressure);

        String humidity = currentWeatherViewModel.getHumidity(currentWeather);
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