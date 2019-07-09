package ro.alexmamo.weatherapp.city

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ro.alexmamo.weatherapp.cities.models.City
import ro.alexmamo.weatherapp.city.models.CurrentWeather
import ro.alexmamo.weatherapp.data.RetrofitClient
import ro.alexmamo.weatherapp.utils.Constants
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

internal class CurrentWeatherRepository {
    fun addCurrentWeatherToLiveData(city: City): LiveData<CurrentWeather> {
        val currentWeatherMutableLiveData = MutableLiveData<CurrentWeather>()
        val openWeatherMapApi = RetrofitClient.instance.openWeatherMapApi
        val callback = object : Callback<CurrentWeather> {
            override fun onResponse(call: Call<CurrentWeather>, response: Response<CurrentWeather>) {
                val currentWeather = response.body()
                currentWeatherMutableLiveData.value = currentWeather
            }

            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                Log.d(Constants.TAG, t.message)
            }
        }
        val call = openWeatherMapApi.getCurrentWeather(city.cityName, Constants.APY_KEY)
        call.enqueue(callback)
        return currentWeatherMutableLiveData
    }

    val dateAndTime: Array<String>
        get() {
            val date = Calendar.getInstance().time
            val dayOfTheWeek = SimpleDateFormat("E", Locale.US).format(date)
            val currentDate = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(date)
            val dayOfTheWeekAndCurrentDate = "$dayOfTheWeek $currentDate"

            val currentTime = SimpleDateFormat("HH:mm", Locale.US)
            val time = currentTime.format(date)

            return arrayOf(dayOfTheWeekAndCurrentDate, time)
        }

    fun getTemperature(currentWeather: CurrentWeather): String {
        return getCelsiusTemperature(currentWeather.main!!.temp).toString() + "°C"
    }

    fun getMin(currentWeather: CurrentWeather): String {
        return "Min: " + getCelsiusTemperature(currentWeather.main!!.temp_min) + "°C"
    }

    fun getMax(currentWeather: CurrentWeather): String {
        return "Max: " + getCelsiusTemperature(currentWeather.main!!.temp_max) + "°C"
    }

    fun getWeather(currentWeather: CurrentWeather): String {
        return currentWeather.weather!![0].main + ", " + currentWeather.weather!![0].description
    }

    fun getWind(currentWeather: CurrentWeather): String {
        return "Wind: " + currentWeather.wind!!.speed + " Km/h"
    }

    fun getPressure(currentWeather: CurrentWeather): String {
        return "Pressure: " + currentWeather.main!!.pressure
    }

    fun getHumidity(currentWeather: CurrentWeather): String {
        return "Humidity: " + currentWeather.main!!.humidity + "%"
    }

    private fun getCelsiusTemperature(temp: Double?): Int {
        return (temp!! - 273.0).roundToInt()
    }
}