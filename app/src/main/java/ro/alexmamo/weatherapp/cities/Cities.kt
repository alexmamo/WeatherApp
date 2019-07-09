package ro.alexmamo.weatherapp.cities

import java.util.ArrayList

import ro.alexmamo.weatherapp.cities.models.City

internal object Cities {
    val cityList: List<City> get() {
        val cityList = ArrayList<City>()
        cityList.add(City("Brasov", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Brasov.jpg"))
        cityList.add(City("Bucharest", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Bucharest.jpg"))
        cityList.add(City("Constanta", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Constanta.jpg"))
        return cityList
    }
}