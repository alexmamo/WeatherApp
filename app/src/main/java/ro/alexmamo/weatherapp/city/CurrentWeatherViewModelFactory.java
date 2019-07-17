package ro.alexmamo.weatherapp.city;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import ro.alexmamo.weatherapp.cities.models.City;

@SuppressWarnings("unchecked")
public class CurrentWeatherViewModelFactory implements ViewModelProvider.Factory {
    private Application application;
    private City city;
    private CurrentWeatherRepository currentWeatherRepository;

    CurrentWeatherViewModelFactory(Application application, City city, CurrentWeatherRepository currentWeatherRepository) {
        this.application = application;
        this.city = city;
        this.currentWeatherRepository = currentWeatherRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CurrentWeatherViewModel(application, city, currentWeatherRepository);
    }
}