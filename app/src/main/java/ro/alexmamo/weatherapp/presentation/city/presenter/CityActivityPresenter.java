package ro.alexmamo.weatherapp.presentation.city.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import ro.alexmamo.weatherapp.domain.model.city.CityActivityModel;
import ro.alexmamo.weatherapp.presentation.cities.model.CurrentWeather;
import ro.alexmamo.weatherapp.presentation.city.CityActivityContract;
import ro.alexmamo.weatherapp.presentation.city.model.City;

public class CityActivityPresenter implements CityActivityContract.Presenter {
    private City city;
    private CityActivityContract.View view;
    private CityActivityModel model;

    public CityActivityPresenter(City city) {
        this.city = city;
        initCityActivityModel();
    }

    private void initCityActivityModel() {
        model = new CityActivityModel();
    }

    @Override
    public void attachView(CityActivityContract.View view) {
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
    public void setWeatherDataToViews() {
        model.getResponse(city, response -> {
            if (response.isSuccessful()) {
                CurrentWeather currentWeather = response.body();
                if (currentWeather != null) {
                    setDateAndTime();
                    setTemperature(currentWeather);
                    setMinAndMax(currentWeather);
                    setWeather(currentWeather);
                    setWind(currentWeather);
                    setPressure(currentWeather);
                    setHumidity(currentWeather);
                }
            } else {
                ResponseBody responseBody = response.errorBody();
                if (responseBody != null) {
                    view.toastError(responseBody.toString());
                }
            }
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