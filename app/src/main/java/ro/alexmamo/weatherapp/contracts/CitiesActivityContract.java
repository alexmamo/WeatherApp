package ro.alexmamo.weatherapp.contracts;

import java.util.List;

import ro.alexmamo.weatherapp.pojos.City;

public interface CitiesActivityContract {
    interface View {
        void initCitiesRecyclerView();
        void initCityAdapter();
        void addCitiesToList(List<City> cityList);
    }

    interface Presenter {
        void getCityList();
    }

    interface Model {
        List<City> getCityList();
    }
}