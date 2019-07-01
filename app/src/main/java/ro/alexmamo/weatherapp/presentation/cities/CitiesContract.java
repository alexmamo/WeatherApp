package ro.alexmamo.weatherapp.presentation.cities;

import java.util.List;

import ro.alexmamo.weatherapp.presentation.city.model.City;

public interface CitiesContract {
    interface View {
        void initCitiesRecyclerView();
        void initCityAdapter();
        void addCitiesToList(List<City> cityList);
    }

    interface Presenter {
        void passCityListFromModelToView();
    }

    interface Model {
        List<City> getCityList();
    }
}