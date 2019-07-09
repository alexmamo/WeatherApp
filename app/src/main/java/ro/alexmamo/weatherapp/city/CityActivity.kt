package ro.alexmamo.weatherapp.city

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_city.*
import ro.alexmamo.weatherapp.R
import ro.alexmamo.weatherapp.cities.models.City
import ro.alexmamo.weatherapp.city.models.CurrentWeather

class CityActivity : AppCompatActivity() {
    private var currentWeatherViewModel: CurrentWeatherViewModel? = null
    private val cityFromIntent: City get() = intent.getSerializableExtra("city") as City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        enableHomeButton()
        setActivityTitle()
        addCitiesToList()
    }

    private fun enableHomeButton() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setActivityTitle() {
        title = cityFromIntent.cityName
    }

    private fun addCitiesToList() {
        val factory = CurrentWeatherViewModelFactory(this.application, cityFromIntent)
        currentWeatherViewModel = ViewModelProviders.of(this, factory).get(CurrentWeatherViewModel::class.java)
        val currentWeatherLiveData = currentWeatherViewModel!!.currentWeatherLiveData
        currentWeatherLiveData.observe(this, Observer {currentWeather -> setDataToViews(currentWeather!!)})
    }

    private fun setDataToViews(currentWeather: CurrentWeather) {
        val currentWeatherRepository: CurrentWeatherRepository = currentWeatherViewModel!!.currentWeatherRepository
        dateTextView!!.text = currentWeatherRepository.dateAndTime[0]
        timeTextView!!.text = currentWeatherRepository.dateAndTime[1]
        temperatureTextView!!.text = currentWeatherRepository.getTemperature(currentWeather)
        minTextView!!.text = currentWeatherRepository.getMin(currentWeather)
        maxTextView!!.text = currentWeatherRepository.getMax(currentWeather)
        weatherTextView!!.text = currentWeatherRepository.getWeather(currentWeather)
        windTextView!!.text = currentWeatherRepository.getWind(currentWeather)
        pressureTextView!!.text = currentWeatherRepository.getPressure(currentWeather)
        humidityTextView!!.text = currentWeatherRepository.getHumidity(currentWeather)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }
}