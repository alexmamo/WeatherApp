package ro.alexmamo.weatherapp.presenters;

import java.util.List;

import ro.alexmamo.weatherapp.contracts.CitiesActivityContract;
import ro.alexmamo.weatherapp.models.CitiesActivityModel;
import ro.alexmamo.weatherapp.pojos.City;

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