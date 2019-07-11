package ro.alexmamo.weatherapp.city;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ro.alexmamo.weatherapp.city.models.CurrentWeather;
import ro.alexmamo.weatherapp.city.models.Main;
import ro.alexmamo.weatherapp.city.models.Weather;
import ro.alexmamo.weatherapp.city.models.Wind;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityViewModelTest {
    private MutableLiveData<CurrentWeather> mockLiveData = new MutableLiveData<>();
    private CurrentWeather currentWeather;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private CurrentWeatherViewModel currentWeatherViewModel;
    @Mock
    private CurrentWeatherRepository currentWeatherRepository;

    @Before
    public void setUp() {
        currentWeather = new CurrentWeather();
        currentWeather.weather = new Weather[]{new Weather()};
        currentWeather.main = new Main();
        currentWeather.main.temp = 23.3;
        currentWeather.wind = new Wind();
        mockLiveData.setValue(currentWeather);
    }

    @Test
    public void currentWeatherLiveDataValidatorIfMockData() {
        when(currentWeatherViewModel.getCurrentWeatherLiveData()).thenReturn(mockLiveData);
        CurrentWeather currentWeather = mockLiveData.getValue();
        if(currentWeather != null) {
            System.out.println(mockLiveData.getValue().main.temp);
        }
    }

    @Test
    public void celsiusTemperatureValidator() {
        when(currentWeatherRepository.getTemperature(currentWeather)).thenReturn(23.3 + "°C");
    }
}