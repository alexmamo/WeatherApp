package ro.alexmamo.weatherapp.presentation.city.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ro.alexmamo.weatherapp.domain.model.city.CityModel;
import ro.alexmamo.weatherapp.presentation.cities.model.CurrentWeather;
import ro.alexmamo.weatherapp.presentation.city.CityContract;

public class CityPresenter implements CityContract.Presenter, CityModel.CurrentWeatherCallback {
    private CityModel cityModel;
    private CityContract.View view;
    private CurrentWeather currentWeather;

    public CityPresenter(CityModel cityModel) {
        this.cityModel = cityModel;
        getCurrentWeatherFromCityModel();
    }

    private void getCurrentWeatherFromCityModel() {
        cityModel.getCurrentWeather(this);
    }

    @Override
    public void attachView(CityContract.View view) {
        this.view = view;
        initViews();
    }

    private void initViews() {
        view.initViews();
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void onCurrentWeatherCallback(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
        setDateAndTime();
        setTemperature(currentWeather.main.temp);
        setMinAndMax(currentWeather.main.temp_min, currentWeather.main.temp_max);
        setWeather(currentWeather.weather[0].main, currentWeather.weather[0].description);
        setWind(currentWeather.wind.speed);
        setPressure(currentWeather.main.pressure);
        setHumidity(currentWeather.main.humidity);
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    @Override
    public void onFailure(String errorMessage) {
        view.toastError(errorMessage);
    }

    private void setDateAndTime() {
        Date date = Calendar.getInstance().getTime();
        String dayOfTheWeek = new SimpleDateFormat("E", Locale.US).format(date);
        String currentDate = new SimpleDateFormat("MMM dd, yyyy", Locale.US).format(date);
        String dayOfTheWeekAndCurrentDate = dayOfTheWeek + " " + currentDate;

        DateFormat currentTime = new SimpleDateFormat("HH:mm", Locale.US);
        String time = currentTime.format(date);

        if (isViewAttached()) {
            view.setDateAndTimeTextViews(dayOfTheWeekAndCurrentDate, time);
        }
    }

    private void setTemperature(double temperature) {
        String temp = getCelsiusTemperature(temperature) + "°C";
        if (isViewAttached()) {
            view.setTemperatureTextView(temp);
        }
    }

    private void setMinAndMax(double minimum, double maximum) {
        String min = "Min: " + getCelsiusTemperature(minimum) + "°C";
        String max = "Max: " + getCelsiusTemperature(maximum) + "°C";
        if (isViewAttached()) {
            view.setMinAndMaxTextViews(min, max);
        }
    }

    private void setWeather(String main, String description) {
        String weather = main + ", " + description;
        if (isViewAttached()) {
            view.setWeatherTextView(weather);
        }
    }

    private void setWind(String windSpeed) {
        String wind = "Wind: " + windSpeed + " Km/h";
        if (isViewAttached()) {
            view.setWindTextView(wind);
        }
    }

    private void setPressure(int currentPressure) {
        String pressure = "Pressure: " + currentPressure;
        if (isViewAttached()) {
            view.setPressureTextView(pressure);
        }
    }

    private void setHumidity(int currentHumidity) {
        String humidity = "Humidity: " + currentHumidity + "%";
        if (isViewAttached()) {
            view.setHumidityTextView(humidity);
        }
    }

    private long getCelsiusTemperature(Double temp) {
        return Math.round(temp - 273.0);
    }
}