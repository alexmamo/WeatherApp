package ro.alexmamo.weatherapp.presentation.cities.presenter;

import java.util.List;

import ro.alexmamo.weatherapp.domain.model.cities.CitiesActivityModel;
import ro.alexmamo.weatherapp.presentation.cities.CitiesActivityContract;
import ro.alexmamo.weatherapp.presentation.city.model.City;

public class CitiesActivityPresenter implements CitiesActivityContract.Presenter {
    private CitiesActivityContract.View view;
    private CitiesActivityModel model;

    public CitiesActivityPresenter(CitiesActivityContract.View view) {
        this.view = view;
        initCitiesActivityModel();
        initCityRecyclerView();
        initCityAdapter();
    }

    private void initCitiesActivityModel() {
        model = new CitiesActivityModel();
    }

    private void initCityRecyclerView() {
        view.initCitiesRecyclerView();
    }

    private void initCityAdapter() {
        view.initCityAdapter();
    }

    @Override
    public void getCityList() {
        List<City> cityList = model.getCityList();
        view.addCitiesToList(cityList);
    }
}