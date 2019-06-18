package ro.alexmamo.weatherapp.presentation.cities.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.alexmamo.weatherapp.R;

import ro.alexmamo.weatherapp.presentation.cities.CitiesActivityContract;
import ro.alexmamo.weatherapp.presentation.cities.presenter.CitiesActivityPresenter;
import ro.alexmamo.weatherapp.presentation.city.pojo.City;
import ro.alexmamo.weatherapp.presentation.city.view.CityActivity;

public class CitiesActivity extends AppCompatActivity implements CitiesActivityContract.View, CitiesAdapter.OnCityClickListener {
    private List<City> cityList = new ArrayList<>();
    private CitiesAdapter citiesAdapter;
    private RecyclerView citiesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        initCitiesActivityPresenter();
    }

    private void initCitiesActivityPresenter() {
        CitiesActivityPresenter presenter = new CitiesActivityPresenter(this);
        presenter.getCityList();
    }

    @Override
    public void initCitiesRecyclerView() {
        citiesRecyclerView = findViewById(R.id.cities_recycler_view);
    }

    @Override
    public void initCityAdapter() {
        citiesAdapter = new CitiesAdapter(this, cityList, this);
        citiesRecyclerView.setAdapter(citiesAdapter);
    }

    @Override
    public void addCitiesToList(List<City> cities) {
        cityList.addAll(cities);
        citiesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCityClick(City clickedCity) {
        goToCityActivity(clickedCity);
    }

    private void goToCityActivity(City city) {
        Intent cityActivityIntent = new Intent(this, CityActivity.class);
        cityActivityIntent.putExtra("city", city);
        startActivity(cityActivityIntent);
    }
}