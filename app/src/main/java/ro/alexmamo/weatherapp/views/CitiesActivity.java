package ro.alexmamo.weatherapp.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.models.City;
import ro.alexmamo.weatherapp.viewmodels.CitiesViewModel;

public class CitiesActivity extends AppCompatActivity implements CityAdapter.OnCityClickListener {
    private RecyclerView citiesRecyclerView;
    private List<City> cityList = new ArrayList<>();
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        initCitiesRecyclerView();
        initCityAdapter();
        addCitiesToList();
    }

    public void initCitiesRecyclerView() {
        citiesRecyclerView = findViewById(R.id.cities_recycler_view);
    }

    public void initCityAdapter() {
        cityAdapter = new CityAdapter(this, cityList, this);
        citiesRecyclerView.setAdapter(cityAdapter);
    }

    public void addCitiesToList() {
        CitiesViewModel citiesViewModel = ViewModelProviders.of(this).get(CitiesViewModel.class);
        LiveData<List<City>> cityLiveData = citiesViewModel.getCitiesLiveData();
        cityLiveData.observe(this, cities -> {
            if (cities != null) {
                cityList.addAll(cities);
                cityAdapter.notifyDataSetChanged();
            }
        });
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