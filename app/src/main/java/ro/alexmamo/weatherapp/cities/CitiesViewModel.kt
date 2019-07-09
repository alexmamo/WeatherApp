package ro.alexmamo.weatherapp.cities

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import ro.alexmamo.weatherapp.cities.models.City

class CitiesViewModel(application: Application) : AndroidViewModel(application) {
    val citiesLiveData: MutableLiveData<List<City>> = MutableLiveData()

    init {
        addCitiesToCitiesLiveData()
    }

    private fun addCitiesToCitiesLiveData() {
        val cityList = Cities.cityList
        citiesLiveData.value = cityList
    }
}