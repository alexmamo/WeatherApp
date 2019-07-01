package ro.alexmamo.weatherapp.city;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ro.alexmamo.weatherapp.domain.model.city.CityModel;
import ro.alexmamo.weatherapp.presentation.cities.model.CurrentWeather;
import ro.alexmamo.weatherapp.presentation.cities.model.Main;
import ro.alexmamo.weatherapp.presentation.cities.model.Weather;
import ro.alexmamo.weatherapp.presentation.cities.model.Wind;
import ro.alexmamo.weatherapp.presentation.city.presenter.CityPresenter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CityPresenterTest {
    private CurrentWeather currentWeather;
    private CityPresenter cityPresenter;
    @Mock
    private CityModel cityModel;

    @Before
    public void setUp() {
        currentWeather = new CurrentWeather();
        currentWeather.weather = new Weather[]{new Weather()};
        currentWeather.main = new Main();
        currentWeather.wind = new Wind();

        cityPresenter = new CityPresenter(cityModel);
    }

    @Captor
    private ArgumentCaptor<CityModel.CurrentWeatherCallback> argumentCaptor;

    @Test
    public void currentWeatherAsyncValidator() {
        doAnswer(invocation -> {
            ((CityModel.CurrentWeatherCallback) invocation.getArguments()[0]).onCurrentWeatherCallback(currentWeather);
            return null;
        }).when(cityModel).getCurrentWeather(any(CityModel.CurrentWeatherCallback.class));

        cityPresenter.onCurrentWeatherCallback(currentWeather);

        verify(cityModel, times(1)).getCurrentWeather(
                any(CityModel.CurrentWeatherCallback.class)
        );
        assertThat(cityPresenter.getCurrentWeather(), is(equalTo(currentWeather)));
    }

    @Test
    public void currentWeatherAsyncValidatorWithArgumentCaptor() {
        cityPresenter.onCurrentWeatherCallback(currentWeather);
        verify(cityModel, times(1)).getCurrentWeather(argumentCaptor.capture());
        assertThat(cityPresenter.getCurrentWeather().weather.length, is(1));
        argumentCaptor.getValue().onCurrentWeatherCallback(currentWeather);
        assertThat(cityPresenter.getCurrentWeather(), is(equalTo(currentWeather)));
    }
}