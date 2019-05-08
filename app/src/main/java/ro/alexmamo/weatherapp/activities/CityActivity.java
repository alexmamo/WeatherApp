package ro.alexmamo.weatherapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.alexmamo.weatherapp.OpenWeatherMapApi;
import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.RetrofitClient;
import ro.alexmamo.weatherapp.models.City;
import ro.alexmamo.weatherapp.models.CurrentWeather;

import static ro.alexmamo.weatherapp.Constants.APY_KEY;
import static ro.alexmamo.weatherapp.Constants.TAG;

public class CityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        enableHomeButton();
        City city = getCityFromIntent();
        getCurrentWeather(city);
        setTitle(city.cityName);
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

    private void getCurrentWeather(City city){
        OpenWeatherMapApi openWeatherMapApi = RetrofitClient.getInstance().getOpenWeatherMapApi();
        Callback<CurrentWeather> callback = new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                if (currentWeather != null) {
                    initViews(currentWeather);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        };
        Call<CurrentWeather> call = openWeatherMapApi.getCurrentWeather(city.cityName, APY_KEY);
        call.enqueue(callback);
    }

    private void initViews(CurrentWeather currentWeather) {
        setDateAndTimeTextViews();
        setTemperatureTextView(currentWeather);
        setMinAndMaxTextViews(currentWeather);
        setWeatherTextView(currentWeather);
        setWindTextView(currentWeather);
        setPressureTextView(currentWeather);
        setHumidityTextView(currentWeather);
    }

    private void setDateAndTimeTextViews() {
        Date date = Calendar.getInstance().getTime();
        String dayOfTheWeek = new SimpleDateFormat("E", Locale.US).format(date);
        String currentDate = new SimpleDateFormat("MMM dd, yyyy", Locale.US).format(date);
        String dayOfTheWeekAndCurrentDate = dayOfTheWeek + " " + currentDate;
        TextView dateTextView = findViewById(R.id.date_text_view);
        dateTextView.setText(dayOfTheWeekAndCurrentDate);

        DateFormat currentTime = new SimpleDateFormat("HH:mm", Locale.US);
        String time = currentTime.format(date);
        TextView timeTextView = findViewById(R.id.time_text_view);
        timeTextView.setText(time);
    }

    private void setTemperatureTextView(CurrentWeather currentWeather) {
        TextView temperatureTextView = findViewById(R.id.temperature_text_view);
        String temp = getCelsiusTemperature(currentWeather.main.temp) + "°C";
        temperatureTextView.setText(temp);
    }

    private void setMinAndMaxTextViews(CurrentWeather currentWeather) {
        TextView minTextView = findViewById(R.id.min_text_view);
        String min = "Min: " + getCelsiusTemperature(currentWeather.main.temp_min) + "°C";
        minTextView.setText(min);

        TextView maxTextView = findViewById(R.id.max_text_view);
        String max = "Max: " + getCelsiusTemperature(currentWeather.main.temp_max) + "°C";
        maxTextView.setText(max);
    }

    private void setWeatherTextView(CurrentWeather currentWeather) {
        TextView weatherTextView = findViewById(R.id.weather_text_view);
        String weather = currentWeather.weather[0].main + ", " + currentWeather.weather[0].description;
        weatherTextView.setText(weather);
    }

    private void setWindTextView(CurrentWeather currentWeather) {
        TextView windTextView = findViewById(R.id.wind_text_view);
        String wind = "Wind: " + currentWeather.wind.speed + " Km/h";
        windTextView.setText(wind);
    }

    private void setPressureTextView(CurrentWeather currentWeather) {
        TextView pressureTextView = findViewById(R.id.pressure_text_view);
        String pressure = "Pressure: " + currentWeather.main.pressure;
        pressureTextView.setText(pressure);
    }

    private void setHumidityTextView(CurrentWeather currentWeather) {
        TextView humidityTextView = findViewById(R.id.humidity_text_view);
        String humidity = "Humidity: " + currentWeather.main.humidity + "%";
        humidityTextView.setText(humidity);
    }

    private long getCelsiusTemperature(Double temp) {
        return Math.round(temp - 273.0);
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