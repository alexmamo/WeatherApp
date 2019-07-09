package ro.alexmamo.weatherapp.city

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import ro.alexmamo.weatherapp.city.models.CurrentWeather
import ro.alexmamo.weatherapp.city.models.Main
import ro.alexmamo.weatherapp.city.models.Weather
import ro.alexmamo.weatherapp.city.models.Wind

import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class CityViewModelTest {
    private val mockLiveData = MutableLiveData<CurrentWeather>()
    private var currentWeather: CurrentWeather? = null
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private val currentWeatherViewModel: CurrentWeatherViewModel? = null

    @Before
    fun setUp() {
        currentWeather = CurrentWeather()
        currentWeather!!.weather = arrayOf(Weather())
        currentWeather!!.main = Main()
        currentWeather!!.main!!.temp = 23.3
        currentWeather!!.wind = Wind()
        mockLiveData.value = currentWeather
    }
}