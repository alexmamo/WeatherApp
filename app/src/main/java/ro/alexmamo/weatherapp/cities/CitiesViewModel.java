package ro.alexmamo.weatherapp.cities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import ro.alexmamo.weatherapp.cities.models.City;

public class CitiesViewModel extends AndroidViewModel {
    private MutableLiveData<List<City>> citiesLiveData;

    public CitiesViewModel(Application application) {
        super(application);
        citiesLiveData = new MutableLiveData<>();
        addCitiesToCitiesLiveData();
    }

    private void addCitiesToCitiesLiveData() {
        List<City> cityList = Cities.getCityList();
        citiesLiveData.setValue(cityList);
    }

    LiveData<List<City>> getCitiesLiveData() {
        return citiesLiveData;
    }
}