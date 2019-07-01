package ro.alexmamo.weatherapp.cities.models;

import java.io.Serializable;

public class City implements Serializable {
    public String cityName, imageUrl;

    public City(String cityName, String imageUrl) {
        this.cityName = cityName;
        this.imageUrl = imageUrl;
    }
}