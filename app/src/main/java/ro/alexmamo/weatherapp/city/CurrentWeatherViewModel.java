package ro.alexmamo.weatherapp.city;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ro.alexmamo.weatherapp.cities.models.City;
import ro.alexmamo.weatherapp.city.models.CurrentWeather;

public class CurrentWeatherViewModel extends ViewModel {
    private LiveData<CurrentWeather> currentWeatherLiveData;
    private CurrentWeatherRepository repository;

    @Inject
    CurrentWeatherViewModel(CurrentWeatherRepository repository) {
        this.repository = repository;
    }

    LiveData<CurrentWeather> getCurrentWeatherLiveData() {
        return currentWeatherLiveData;
    }

    CurrentWeatherRepository getRepository() {
        return repository;
    }

    public void setCity(City city) {
        currentWeatherLiveData = repository.addCurrentWeatherToLiveData(city);
    }
}