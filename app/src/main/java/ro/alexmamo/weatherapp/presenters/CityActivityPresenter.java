package ro.alexmamo.weatherapp.presenters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ro.alexmamo.weatherapp.contracts.CityActivityContract;
import ro.alexmamo.weatherapp.models.CityActivityModel;
import ro.alexmamo.weatherapp.pojos.City;
import ro.alexmamo.weatherapp.pojos.CurrentWeather;

public class CityActivityPresenter implements CityActivityContract.Presenter {
    private City city;
    private CityActivityContract.View view;
    private CityActivityModel model;

    public CityActivityPresenter(City city, CityActivityContract.View view) {
        this.city = city;
        this.view = view;
        initViews();
        initCityActivityModel();
    }

    private void initViews() {
        view.initViews();
    }

    private void initCityActivityModel() {
        model = new CityActivityModel();
    }

    @Override
    public void setWeatherDataToViews() {
        model.getCurrentWeather(city, currentWeather -> {
            setDateAndTime();
            setTemperature(currentWeather);
            setMinAndMax(currentWeather);
            setWeather(currentWeather);
            setWind(currentWeather);
            setPressure(currentWeather);
            setHumidity(currentWeather);
        });
    }

    private void setDateAndTime() {
        Date date = Calendar.getInstance().getTime();
        String dayOfTheWeek = new SimpleDateFormat("E", Locale.US).format(date);
        String currentDate = new SimpleDateFormat("MMM dd, yyyy", Locale.US).format(date);
        String dayOfTheWeekAndCurrentDate = dayOfTheWeek + " " + currentDate;

        DateFormat currentTime = new SimpleDateFormat("HH:mm", Locale.US);
        String time = currentTime.format(date);

        view.setDateAndTimeTextViews(dayOfTheWeekAndCurrentDate, time);
    }

    private void setTemperature(CurrentWeather currentWeather) {
        String temp = getCelsiusTemperature(currentWeather.main.temp) + "°C";
        view.setTemperatureTextView(temp);
    }

    private void setMinAndMax(CurrentWeather currentWeather) {
        String min = "Min: " + getCelsiusTemperature(currentWeather.main.temp_min) + "°C";
        String max = "Max: " + getCelsiusTemperature(currentWeather.main.temp_max) + "°C";
        view.setMinAndMaxTextViews(min, max);
    }

    private void setWeather(CurrentWeather currentWeather) {
        String weather = currentWeather.weather[0].main + ", " + currentWeather.weather[0].description;
        view.setWeatherTextView(weather);
    }

    private void setWind(CurrentWeather currentWeather) {
        String wind = "Wind: " + currentWeather.wind.speed + " Km/h";
        view.setWindTextView(wind);
    }

    private void setPressure(CurrentWeather currentWeather) {
        String pressure = "Pressure: " + currentWeather.main.pressure;
        view.setPressureTextView(pressure);
    }

    private void setHumidity(CurrentWeather currentWeather) {
        String humidity = "Humidity: " + currentWeather.main.humidity + "%";
        view.setHumidityTextView(humidity);
    }

    private long getCelsiusTemperature(Double temp) {
        return Math.round(temp - 273.0);
    }
}