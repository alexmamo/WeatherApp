package ro.alexmamo.weatherapp.cities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import ro.alexmamo.weatherapp.domain.model.cities.CitiesModel;
import ro.alexmamo.weatherapp.presentation.cities.CitiesContract;
import ro.alexmamo.weatherapp.presentation.cities.presenter.CitiesPresenter;
import ro.alexmamo.weatherapp.presentation.city.model.City;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CitiesPresenterTest {
    private CitiesContract.Presenter citiesPresenter;
    @Mock
    private CitiesContract.View citiesView;
    @Mock
    private CitiesModel citiesModel;

    @Before
    public void setUp() {
        citiesPresenter = new CitiesPresenter(citiesView, citiesModel);
    }

    @Test
    public void cityListValidatorIfNull() {
        when(citiesModel.getCityList()).thenReturn(null);
        citiesPresenter.passCityListFromModelToView();
        verify(citiesView).addCitiesToList(null);
    }

    @Test
    public void cityListValidatorIfEmpty() {
        List<City> citiesList = new ArrayList<>();
        when(citiesModel.getCityList()).thenReturn(citiesList);
        citiesPresenter.passCityListFromModelToView();
        verify(citiesView).addCitiesToList(citiesList);
    }

    @Test
    public void cityListValidatorIfFakeData() {
        List<City> citiesList = new ArrayList<>();
        City fakeCity = new City("FakeCityName", "FakeImageUrl");
        citiesList.add(fakeCity);
        when(citiesModel.getCityList()).thenReturn(citiesList);
        citiesPresenter.passCityListFromModelToView();
        verify(citiesView).addCitiesToList(citiesList);
    }
}