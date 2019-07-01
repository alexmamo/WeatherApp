package ro.alexmamo.weatherapp.presentation.cities.presenter;

import java.util.List;

import ro.alexmamo.weatherapp.domain.model.cities.CitiesModel;
import ro.alexmamo.weatherapp.presentation.cities.CitiesContract;
import ro.alexmamo.weatherapp.presentation.city.model.City;

public class CitiesPresenter implements CitiesContract.Presenter {
    private CitiesContract.View citiesView;
    private CitiesModel citiesModel;

    public CitiesPresenter(CitiesContract.View citiesView, CitiesModel citiesModel) {
        this.citiesView = citiesView;
        this.citiesModel = citiesModel;
        initCityRecyclerView();
        initCityAdapter();
    }

    private void initCityRecyclerView() {
        citiesView.initCitiesRecyclerView();
    }

    private void initCityAdapter() {
        citiesView.initCityAdapter();
    }

    @Override
    public void passCityListFromModelToView() {
        List<City> cityList = citiesModel.getCityList();
        citiesView.addCitiesToList(cityList);
    }
}