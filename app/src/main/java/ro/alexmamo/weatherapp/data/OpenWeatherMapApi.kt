package ro.alexmamo.weatherapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ro.alexmamo.weatherapp.city.models.CurrentWeather

interface OpenWeatherMapApi {
    @GET("weather")
    fun getCurrentWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): Call<CurrentWeather>
}