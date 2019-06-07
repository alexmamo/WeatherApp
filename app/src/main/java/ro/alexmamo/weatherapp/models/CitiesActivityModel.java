package ro.alexmamo.weatherapp.models;

import java.util.ArrayList;
import java.util.List;

import ro.alexmamo.weatherapp.contracts.CitiesActivityContract;
import ro.alexmamo.weatherapp.pojos.City;

public class CitiesActivityModel implements CitiesActivityContract.Model {
    @Override
    public List<City> getCityList() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City("Brasov", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Brasov.jpg"));
        cityList.add(new City("Bucharest", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Bucharest.jpg"));
        cityList.add(new City("Constanta", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Constanta.jpg"));
        return cityList;
    }
}