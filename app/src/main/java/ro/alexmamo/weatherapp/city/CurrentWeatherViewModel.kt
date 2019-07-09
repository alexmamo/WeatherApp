package ro.alexmamo.weatherapp.city

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import ro.alexmamo.weatherapp.cities.models.City
import ro.alexmamo.weatherapp.city.models.CurrentWeather

internal class CurrentWeatherViewModel(application: Application, city: City) : AndroidViewModel(application) {
    var currentWeatherRepository: CurrentWeatherRepository = CurrentWeatherRepository()
    val currentWeatherLiveData: LiveData<CurrentWeather>

    init {
        currentWeatherLiveData = currentWeatherRepository.addCurrentWeatherToLiveData(city)
    }
}