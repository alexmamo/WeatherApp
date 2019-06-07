package ro.alexmamo.weatherapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.adapters.CityAdapter;
import ro.alexmamo.weatherapp.contracts.CitiesActivityContract;
import ro.alexmamo.weatherapp.pojos.City;
import ro.alexmamo.weatherapp.presenters.CitiesActivityPresenter;

public class CitiesActivity extends AppCompatActivity implements CitiesActivityContract.View, CityAdapter.OnCityClickListener {
    private List<City> cityList = new ArrayList<>();
    private CityAdapter cityAdapter;
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
        cityAdapter = new CityAdapter(this, cityList, this);
        citiesRecyclerView.setAdapter(cityAdapter);
    }

    @Override
    public void addCitiesToList(List<City> cities) {
        cityList.addAll(cities);
        cityAdapter.notifyDataSetChanged();
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