package ro.alexmamo.weatherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.adapters.CityAdapter;
import ro.alexmamo.weatherapp.models.City;

public class CitiesActivity extends AppCompatActivity implements CityAdapter.OnCityClickListener {
    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        cityList = getCityList();
        initViews(cityList);
    }

    private List<City> getCityList() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City("Brasov", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Brasov.jpg"));
        cityList.add(new City("Bucharest", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Bucharest.jpg"));
        cityList.add(new City("Constanta", "https://www.alexmamo.ro/apps/WeatherApp/CityImages/Constanta.jpg"));
        return cityList;
    }

    private void initViews(List<City> cityList) {
        RecyclerView citiesRecyclerView = findViewById(R.id.cities_recycler_view);
        CityAdapter cityAdapter = new CityAdapter(this, cityList, this);
        citiesRecyclerView.setAdapter(cityAdapter);
    }

    @Override
    public void onCityClick(int position) {
        City clickedCity = cityList.get(position);
        goToCityActivity(clickedCity);
    }

    private void goToCityActivity(City city) {
        Intent cityActivityIntent = new Intent(this, CityActivity.class);
        cityActivityIntent.putExtra("city", city);
        startActivity(cityActivityIntent);
    }
}