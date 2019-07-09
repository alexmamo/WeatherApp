package ro.alexmamo.weatherapp.city

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import ro.alexmamo.weatherapp.cities.models.City

@Suppress("UNCHECKED_CAST")
class CurrentWeatherViewModelFactory internal constructor(
        private val application: Application,
        private val city: City
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(application, city) as T
    }
}