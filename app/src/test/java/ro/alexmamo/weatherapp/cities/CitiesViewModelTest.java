package ro.alexmamo.weatherapp.cities;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import ro.alexmamo.weatherapp.cities.models.City;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CitiesViewModelTest {
    private MutableLiveData<List<City>> mockLiveData = new MutableLiveData<>();
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private CitiesViewModel citiesViewModel;

    @Before
    public void setUp() {
        List<City> cities = new ArrayList<>();
        City fakeCity = new City("FakeCityName", "FakeImageUrl");
        cities.add(fakeCity);
        mockLiveData.setValue(cities);
    }

    @Test
    public void cityListLiveDataValidatorIfMockData() {
        when(citiesViewModel.getCitiesLiveData()).thenReturn(mockLiveData);
        List<City> cities = mockLiveData.getValue();
        if (cities != null) {
            City firstCity = cities.get(0);
            String firstCityName = firstCity.getCityName();
            System.out.println(firstCityName);
        }
        assertEquals(citiesViewModel.getCitiesLiveData(), mockLiveData);
    }
}