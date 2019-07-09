package ro.alexmamo.weatherapp.city;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import ro.alexmamo.weatherapp.cities.models.City;
import ro.alexmamo.weatherapp.city.models.CurrentWeather;

class CurrentWeatherViewModel extends AndroidViewModel {
    CurrentWeatherRepository currentWeatherRepository;
    private LiveData<CurrentWeather> currentWeatherLiveData;

    CurrentWeatherViewModel(Application application, City city) {
        super(application);
        currentWeatherRepository = new CurrentWeatherRepository();
        currentWeatherLiveData = currentWeatherRepository.addCurrentWeatherToLiveData(city);
    }

    LiveData<CurrentWeather> getCurrentWeatherLiveData() {
        return currentWeatherLiveData;
    }
}