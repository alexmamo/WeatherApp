package ro.alexmamo.weatherapp.city;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.junit.MockitoJUnitRunner;

import ro.alexmamo.weatherapp.domain.model.city.CityModel;
import ro.alexmamo.weatherapp.presentation.cities.model.CurrentWeather;
import ro.alexmamo.weatherapp.presentation.cities.model.Main;
import ro.alexmamo.weatherapp.presentation.cities.model.Weather;
import ro.alexmamo.weatherapp.presentation.cities.model.Wind;
import ro.alexmamo.weatherapp.presentation.city.CityContract;
import ro.alexmamo.weatherapp.presentation.city.presenter.CityPresenter;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CityPresenterTest {
    private CurrentWeather currentWeather;
    private CityPresenter cityPresenter;
    @Mock
    private CityModel cityModel;
    @Mock
    private CityContract.View view;

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

    @Test
    public void shouldAttachViewInActivity() {
        cityPresenter.attachView(view);
        assertEquals(view, cityPresenter.view);
    }

    @Test
    public void shouldAttachViewInActivityNoViewInteraction() {
        cityPresenter.attachView(view);
        try {
            verifyZeroInteractions(view);
            fail();
        } catch (NoInteractionsWanted e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldDetachViewInActivity() {
        cityPresenter.detachView();
        assertNull(cityPresenter.view);
    }
}